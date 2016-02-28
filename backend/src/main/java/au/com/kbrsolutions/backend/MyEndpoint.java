/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package au.com.kbrsolutions.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import au.com.kbrsolutions.jokes.JokesJavaLib;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.kbrsolutions.com.au",
    ownerName = "backend.kbrsolutions.com.au",
    packagePath=""
  )
)
public class MyEndpoint {

    // FIXME: 25/02/2016 - remove the mehod below before sending for review
    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "getJokeFromJavaLibrary")
    public MyBean getJokeFromJavaLibrary() {
        JokesJavaLib jokeSource = new JokesJavaLib();
        String joke = jokeSource.tellJavaLibAWizardJoke();
        MyBean response = new MyBean();
        response.setData(joke);
        return response;
    }

}
