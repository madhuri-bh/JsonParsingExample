package com.example.movieapp;

import android.content.SearchRecentSuggestionsProvider;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {

    }

    public String makeServiceCall (String requestUrl) {
        String response = null;
        try{
            URL url = new URL(requestUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            response = convertJsonToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String convertJsonToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
    } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return stringBuilder.toString();
        }


    }
