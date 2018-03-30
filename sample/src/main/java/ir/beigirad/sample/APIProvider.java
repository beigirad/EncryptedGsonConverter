package ir.beigirad.sample;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import ir.beigirad.encryptedgsonconverter.GsonEncryptConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import se.simbio.encryption.Encryption;

/**
 * Created by farhad-mbp on 3/30/18.
 */

public class APIProvider {
    private String BASE_URL = "https://raw.githubusercontent.com";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private APIService apiService;

    public APIProvider() {

        // for more detail visit https://github.com/simbiose/Encryption
        Encryption encryption = Encryption.getDefault("MyKey", "MySalt", new byte[16]);


        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new StethoInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonEncryptConverterFactory.create(encryption))
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }
}
