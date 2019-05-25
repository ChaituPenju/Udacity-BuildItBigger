package com.chaitupenjudcoder.jokesdisplaydroidlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    TextView tv;
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        tv = findViewById(R.id.tv_joke);
        Intent in = getIntent();
        joke = "This is the default Joke";
        if (in.hasExtra("joke")) {
            joke = in.getStringExtra("joke");
        }
        tv.setText(joke);
    }
}
