package ir.iwithyou.wachiproject.login.Model;


import ir.iwithyou.wachiproject.pojo.WachiLogin;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ClientLogin {

    @GET(".")
    Call<WachiLogin> login(@Query("username") String  UserName,@Query("password") String  Password);

}
