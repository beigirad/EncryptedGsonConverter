package ir.beigirad.sample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by farhad-mbp on 3/30/18.
 */

public interface APIService {
    @GET("/beigirad/EncryptedGsonConverter/master/files/person-encrypted.json")
    Call<Person> getPerson();
}
