package ir.beigirad.sample;


import android.util.Log;

import ir.beigirad.encryptedgsonconverter.Encryption;
import ir.beigirad.encryptedgsonconverter.GsonEncryptConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by farhad-mbp on 3/30/18.
 */

public class APIProvider {
    private String BASE_URL = "https://raw.githubusercontent.com";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private APIService apiService;

    public APIProvider() {

        // used this thread for encryption https://stackoverflow.com/questions/15554296
        Encryption encryption = Encryption.getDefault("MyKey","InitVector");

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
