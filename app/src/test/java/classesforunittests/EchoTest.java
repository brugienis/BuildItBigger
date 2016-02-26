package classesforunittests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by business on 25/02/2016.
 */
public class EchoTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEcho() throws Exception {
        assertEquals("returned wrong value", "hello", Echo.echo("hello"));
    }
}