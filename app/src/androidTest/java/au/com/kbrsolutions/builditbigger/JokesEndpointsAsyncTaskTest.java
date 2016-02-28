package au.com.kbrsolutions.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;

/**
 * Created by business on 28/02/2016.
 */
public class JokesEndpointsAsyncTaskTest
        extends AndroidTestCase
        implements JokesEndpointsAsyncTask.JokesEndpointsCallbacks {

    private final static String LOG_TAG = JokesEndpointsAsyncTaskTest.class.getSimpleName();

    public void testGceJokeRequest() throws InterruptedException, ExecutionException {
            new JokesEndpointsAsyncTask(this).execute().get();
    }

    @Override
    public void processResponseFromGce(String reponse) {
        assertTrue("Response can not be empty", reponse != null && reponse.length() > 0);
    }
}
