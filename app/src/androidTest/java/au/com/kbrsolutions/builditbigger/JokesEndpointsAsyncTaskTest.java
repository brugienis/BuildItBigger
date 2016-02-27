package au.com.kbrsolutions.builditbigger;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by business on 26/02/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokesEndpointsAsyncTaskTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mIntent;
    private MainActivity activity;

    private final static String LOG_TAG = JokesEndpointsAsyncTaskTest.class.getSimpleName();

    // create  a countDownLatch to let us know when our task is done.
    final CountDownLatch countDownLatch = new CountDownLatch(1);

    public JokesEndpointsAsyncTaskTest() {
        super(MainActivity.class);
    }

    @org.junit.Before
    public void setUp() throws Exception {
        //Create an intent to launch target Activity as it is not automatically started by Android Instrumentation
        mIntent = new Intent(getInstrumentation().getContext(), MainActivity.class);
        //Start the activity under test in isolation, in the main thread to avoid assertion error.
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity = startActivity(mIntent, null, null);
            }
        });
    }

    /**
     * Tests the preconditions of this test fixture.
     */
    @SmallTest
    public void testPreconditions() {
        assertNotNull("MainActivity is null", getActivity());
    }

    @org.junit.After
    public void tearDown() throws Exception {
        super.tearDown();
    }

//    @UiThreadTest
    @org.junit.Test
    public void testDoInBackground() throws Exception {
//        Activity activity = new MainActivity();
//        new JokesEndpointsAsyncTask().execute(getActivity());
//        final JokesEndpointsAsyncTask jokesEndpointsAsyncTask = new JokesEndpointsAsyncTask(countDownLatch);
        final JokesEndpointsAsyncTask jokesEndpointsAsyncTask = new JokesEndpointsAsyncTask(null);
        Log.v(LOG_TAG, "testDoInBackground - activity/jokesEndpointsAsyncTask: " + getActivity() + "/" + jokesEndpointsAsyncTask);
        try {
            runTestOnUiThread(new Runnable() {

                @Override
                public void run() {
    //                myTask.execute("Do something");
                    jokesEndpointsAsyncTask.execute(getActivity());
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.v(LOG_TAG, "testDoInBackground - after runTestOnUiThread()");
//        assertEquals("returned wrong value", "hello", Echo.echo("hello"));
    }

    @org.junit.Test
    public void testOnPostExecute() throws Exception {
        Log.v(LOG_TAG, "testOnPostExecute - before await()");
        countDownLatch.await(30, TimeUnit.SECONDS);
        Log.v(LOG_TAG, "testOnPostExecute - after  await()");
//        assertEquals("returned wrong value", "hello", Echo.echo("hello"));
    }
}