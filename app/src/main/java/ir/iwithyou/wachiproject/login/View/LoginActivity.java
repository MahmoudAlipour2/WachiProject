package ir.iwithyou.wachiproject.login.View;


import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import ir.iwithyou.wachiproject.R;
import ir.iwithyou.wachiproject.login.Model.ClientLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    ImageView image_CloseBS;
    ImageView image_ShareBS;
    EditText userName;
    EditText passWord;
    String username;
    String password;
    Button login_btn;

    TextView tv_ResponseBS;
    TextView tv_MessageBS;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


        image_CloseBS.setOnClickListener(v ->
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));
        image_ShareBS.setOnClickListener(v -> {
            //TODO: SHARE TEXT WITH SOCIAL APPLICATION.
            Toast.makeText(this, "SHARE TEXT WITH SOCIAL APPLICATION.", Toast.LENGTH_SHORT).show();
        });

        login_btn.setOnClickListener(v -> {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            pb.setVisibility(View.VISIBLE);
            if (username != null && password != null) {
                username = "";
                password = "";
            }
            username = userName.getText().toString().toLowerCase();
            password = passWord.getText().toString().toLowerCase();

            if (username.isEmpty() || password.isEmpty()) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Enter Username and Password ", Toast.LENGTH_SHORT).show();
            } else {
                String base = username + ":" + password;
                String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("Http://149.28.203.228:2026/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                ClientLogin client = retrofit.create(ClientLogin.class);
                Call<String> call = client.getStingResponse(authHeader);


                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        switch (response.code()) {
                            case 402:
                                tv_ResponseBS.setText("Bad Request");
                                tv_MessageBS.setText("please try again!");
                                break;
                            case 202:
                                tv_ResponseBS.setText("Successful");
                                tv_MessageBS.setText("User is Premium");
                                break;
                            case 200:
                                tv_ResponseBS.setText("Result of request");
                                tv_MessageBS.setText("Every thing is OK");
                                break;
                            case 400:
                                tv_ResponseBS.setText("Wrong Password");
                                tv_MessageBS.setText("Your password is incorrect!");
                                break;
                            case 404:
                                tv_ResponseBS.setText("Connection Failure");
                                tv_MessageBS.setText("Try again, later ");
                                break;
                            default:
                                tv_ResponseBS.setText("Log in failed");
                                tv_MessageBS.setText("Try again!");
                        }
                        pb.setVisibility(View.INVISIBLE);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        pb.setVisibility(View.INVISIBLE);
                        tv_ResponseBS.setText("Request failed.");
                        tv_MessageBS.setText("Check your connection or proxy!");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });

            }
        });
    }


    private void init() {
        pb = findViewById(R.id.pb);
        userName = findViewById(R.id.log_UserName);
        passWord = findViewById(R.id.log_Password);
        login_btn = findViewById(R.id.login_btn);
        tv_ResponseBS = findViewById(R.id.tv_ResponseBS);
        tv_MessageBS = findViewById(R.id.tv_MessageBS);
        image_CloseBS = findViewById(R.id.img_CloseBS);
        image_ShareBS = findViewById(R.id.img_ShareBS);
        bottomSheet = findViewById(R.id.layout_bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.isHideable();
    }
}
