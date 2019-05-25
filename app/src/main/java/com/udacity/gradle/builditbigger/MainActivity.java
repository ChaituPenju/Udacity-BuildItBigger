package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.chaitupenjudcoder.jokesdisplaydroidlib.JokeActivity;
import com.chaitupenjudcoder.joketelljavalib.TellJoke;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pb_joke_load);
        pb.setVisibility(View.INVISIBLE);
        counter = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayJoke() {
        new EndPointAsyncTask(new EndPointAsyncTask.TaskJokeDisplay() {
            @Override
            public void onJokeSet(String joke) {
                Intent in = new Intent(MainActivity.this, JokeActivity.class);
                in.putExtra("joke", joke);
                startActivity(in);
            }
        }, pb).execute(new Pair<Context, String>(this, "I finally not did it!!"));

    }

    public void tellJoke(View view) {
        displayJoke();
    }


}
