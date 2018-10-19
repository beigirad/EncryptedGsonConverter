package ir.beigirad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private APIService apiService;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = new APIProvider().getApiService();

        findViewById(R.id.person_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPerson();
            }
        });
        findViewById(R.id.movies_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMovies();
            }
        });

    }


    private void requestPerson() {
        apiService.getPerson().enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Response: " + response.body().toString());
                    Toast.makeText(MainActivity.this, "Response: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Unsuccessful: " + response.message());
                    Toast.makeText(MainActivity.this, "Unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e(TAG, "Failure" + t.getMessage());
                Toast.makeText(MainActivity.this, "Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestMovies() {
        apiService.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> list = response.body();
                    Log.i(TAG, "Response: number of movies " + list.size());
                    Toast.makeText(MainActivity.this, "number of movies: " + list.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Unsuccessful: " + response.message());
                    Toast.makeText(MainActivity.this, "Unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e(TAG, "Failure" + t.getMessage());
                Toast.makeText(MainActivity.this, "Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
