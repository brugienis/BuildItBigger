package au.com.kbrsolutions.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.runner.RunWith;

import classesforunittests.Echo;

import static org.junit.Assert.assertEquals;

/**
 * Created by business on 26/02/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokesEndpointsAsyncTaskTaskTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testDoInBackground() throws Exception {
        assertEquals("returned wrong value", "hello", Echo.echo("helloX"));
    }

    @org.junit.Test
    public void testOnPostExecute() throws Exception {
        assertEquals("returned wrong value", "hello", Echo.echo("helloY"));
    }
}