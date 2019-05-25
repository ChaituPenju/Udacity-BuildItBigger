package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import com.chaitupenjudcoder.joketelljavalib.TellJoke;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> aTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testAsyncTask() throws ExecutionException, InterruptedException {
        EndPointAsyncTask.TaskJokeDisplay tJoke = new EndPointAsyncTask.TaskJokeDisplay() {
            @Override
            public void onJokeSet(String joke) {
                TellJoke tellJoke = new TellJoke();
                tellJoke.getJoke();
            }
        };
        EndPointAsyncTask task = new EndPointAsyncTask(tJoke, null);
        Context context = aTestRule.getActivity();
        task.execute(new Pair<>(context, "A STRING"));

        String randomJoke = task.get();

        assertNotNull(randomJoke);
    }
}