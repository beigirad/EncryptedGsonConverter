package ir.beigirad.sample;


import android.util.Log;

import ir.beigirad.encryptedgsonconverter.GsonEncryptConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Network log", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
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
