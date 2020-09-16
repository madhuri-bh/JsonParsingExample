package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movies> moviesList = new ArrayList<>();
    private RecyclerView recyclerView;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movies_rv);
        new GetMovies().execute();
    }


    private class GetMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Json data is downloading...",Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();
            String url = "https://velmm.com/apis/volley_array.json";
            String JsonString = httpHandler.makeServiceCall(url);
            //Log.e(TAG,"Response from url : " + JsonString);
            if(JsonString != null) {
                try{
                    //JSONObject jsonObject = new JSONObject(JsonString);
                    JSONArray moviesJsonArray = new JSONArray(JsonString);
                    for(int i =0; i<moviesJsonArray.length(); i++) {
                        JSONObject moviesJsonObject = moviesJsonArray.getJSONObject(i);
                        String id = moviesJsonObject.getString("id");
                        String image = moviesJsonObject.getString("image");
                        String title = moviesJsonObject.getString("title");

                        Movies movies = new Movies(id,title,image);
                        moviesList.add(movies);


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error "+e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "JsonParsingError : "+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG,"Couldn't get Json From server");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get Json From server", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MoviesAdapter moviesAdapter = new MoviesAdapter(getApplicationContext(), moviesList);
            recyclerView.setAdapter(moviesAdapter);
            moviesAdapter.notifyDataSetChanged();
recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            super.onPostExecute(result);
        }
    }
}