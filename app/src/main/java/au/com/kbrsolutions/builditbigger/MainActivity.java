package au.com.kbrsolutions.builditbigger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity
        extends ActionBarActivity {
//        implements JokesEndpointsAsyncTask.JokesEndpointsCallbacks {

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

//    public void launchJokeActivity(View view) {
//        sendJokeAsyncRequestToGce();
//    }
//
//    private String sendJokeAsyncRequestToGce() {
//        new JokesEndpointsAsyncTask(this).execute(this);
//        return null;
//    }

//    @Override
//    public void processResponseFromGce(String reponse) {
//        Intent intent = new Intent(this, JokeViewActivity.class);
//        intent.putExtra(JokeViewActivity.JOKE_KEY, "From MainActivity: " + reponse);
//        startActivity(intent);
//    }

}
