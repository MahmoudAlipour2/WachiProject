package ir.iwithyou.wachiproject.login.Model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGeneratorLogin {

    public static final String API_BASE_URL = "Http://149.28.203.228:2026/";
    private static OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
