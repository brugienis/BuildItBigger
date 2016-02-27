package au.com.kbrsolutions.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by business on 28/02/2016.
 */
public class JokesEndpointsAsyncTaskTestNew
        extends AndroidTestCase
        implements JokesEndpointsAsyncTask.JokesEndpointsCallbacks {

    private final static String LOG_TAG = JokesEndpointsAsyncTaskTestNew.class.getSimpleName();

    public void testGceJokeRequest() throws InterruptedException, ExecutionException {
            new JokesEndpointsAsyncTask(this).execute().get();
    }

    @Override
    public void processResponseFromGce(String reponse) {
        Log.v(LOG_TAG, "processResponseFromGce - reponse: " + reponse);
        assertTrue("Response can not be empty", reponse != null || reponse.length() > 0);
    }
}
