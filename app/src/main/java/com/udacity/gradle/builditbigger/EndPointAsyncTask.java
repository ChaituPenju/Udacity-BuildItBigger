package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;

    private TaskJokeDisplay mTaskJokeDisplay;

    private Context context;
    ProgressBar pb;

    public EndPointAsyncTask(TaskJokeDisplay taskJokeDisplay, ProgressBar pb) {
        mTaskJokeDisplay = taskJokeDisplay;
        this.pb = pb;
    }

    @Override
    protected void onPreExecute() {
        if (pb != null) {
            pb.setVisibility(View.VISIBLE);//Toast.makeText(context, "In onPreExecute", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    //.setApplicationName("com.udacity.gradle.builditbigger.free")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            Thread.sleep(1000);
            return myApiService.getAJoke().execute().getData();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
            return "";
        } catch (InterruptedException e) {
            Log.e("TAG", e.getMessage());
            return "";
        }
    }

    public interface TaskJokeDisplay {
        void onJokeSet(String joke);
    }


    @Override
    protected void onPostExecute(String result) {
        if (pb != null) {
            pb.setVisibility(View.INVISIBLE);
        }
        mTaskJokeDisplay.onJokeSet(result);
    }
}
