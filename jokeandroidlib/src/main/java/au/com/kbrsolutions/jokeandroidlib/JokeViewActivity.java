package au.com.kbrsolutions.jokeandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class JokeViewActivity extends ActionBarActivity {

    private final static String LOG_TAG = JokeViewActivity.class.getSimpleName();

    public static String JOKE_KEY = "Joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_view);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY);
        TextView jokeTextView = (TextView) findViewById(R.id.joke_textview);
        if (joke != null && joke.length() != 0) {
            Log.v(LOG_TAG, "onCreate - joke: " + joke);
            jokeTextView.setText(joke + " - we are now in the JokeViewActivity (JokeAndroid Lib)");
        }
    }
}
