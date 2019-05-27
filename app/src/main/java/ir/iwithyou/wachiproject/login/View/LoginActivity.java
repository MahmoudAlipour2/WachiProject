package ir.iwithyou.wachiproject.login.View;


import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.iwithyou.wachiproject.R;

public class LoginActivity extends AppCompatActivity {

    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    ImageView image_CloseBS;
    ImageView image_ShareBS;
    EditText userName;
    EditText password;
    Button login_btn;
    String userN;
    String passW;
    TextView tv_ResponseBS;
    TextView tv_MessageBS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        login_btn.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));
        image_CloseBS.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));
        image_ShareBS.setOnClickListener(v -> {
            //TODO: SHARE TEXT WITH SOCIAL APPLICATION.
            Toast.makeText(this, "SHARE TEXT WITH SOCIAL APPLICATION.", Toast.LENGTH_SHORT).show();
        });


        //TODO: SET TEXT TV_MESSAGEBS AND TV_RESPONSEBS BY SERVER RESPONSES.

/*        login_btn.setOnClickListener(v -> {
            userN = userName.getText().toString();
            passW = password.getText().toString();

            ClientLogin client = RetrofitGeneratorLogin.createService(ClientLogin.class);

            final Call<WachiLogin> login = client.login(userN, passW);
            login.enqueue(new Callback<WachiLogin>() {
                @Override
                public void onResponse(Call<WachiLogin> call, Response<WachiLogin> response) {
                    Toast.makeText(LoginActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    alertBuilder = new AlertDialog.Builder(LoginActivity.this); 


                }

                @Override
                public void onFailure(Call<WachiLogin> call, Throwable t) {

                    Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });


        });*/


    }


    private void init() {
        userName = findViewById(R.id.log_UserName);
        password = findViewById(R.id.log_Password);
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
