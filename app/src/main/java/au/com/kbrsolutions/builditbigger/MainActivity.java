package au.com.kbrsolutions.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import au.com.kbrsolutions.jokeandroidlib.JokeViewActivity;
import au.com.kbrsolutions.jokes.JokesJavaLibWizard;

public class MainActivity extends ActionBarActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

//    public void tellJoke(View view) {
//        System.out.println("tellJoke called");
//        JokesJavaLibWizard jokesJavaLibWizard = new JokesJavaLibWizard();
//        Toast.makeText(this, jokesJavaLibWizard.tellJavaLibAWizardJoke(), Toast.LENGTH_SHORT).show();
//    }

    public void launchJokeActivity(View view){
        Intent intent = new Intent(this, JokeViewActivity.class);
        JokesJavaLibWizard jokeSource = new JokesJavaLibWizard();
        String joke = jokeSource.tellJavaLibAWizardJoke();
        Log.v(LOG_TAG, "launchJokeActivity - joke: " + joke);
        intent.putExtra(JokeViewActivity.JOKE_KEY, joke);
        startActivity(intent);
        Log.v(LOG_TAG, "launchJokeActivity - after startActivity");
    }

    /*

    There we go! Now we can launch the activity from our library, and it's
    easy to reuse that activity between different apps!

     */

    public void launchLibraryActivity(View view){
        Intent myIntent = new Intent(this, JokeViewActivity.class);
        startActivity(myIntent);
    }


}
