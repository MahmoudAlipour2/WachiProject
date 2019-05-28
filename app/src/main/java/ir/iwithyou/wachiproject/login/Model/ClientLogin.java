package ir.iwithyou.wachiproject.login.Model;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;



public interface ClientLogin {

    @GET(".")
    Call<String>  getStingResponse(@Header("Authorization") String authHeader );

}
