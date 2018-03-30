package ir.beigirad.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = new APIProvider().getApiService();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        apiService.getPerson().enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                Toast.makeText(MainActivity.this, "firstName : " + person.firstName + "\n lastName : " + person.lastName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
