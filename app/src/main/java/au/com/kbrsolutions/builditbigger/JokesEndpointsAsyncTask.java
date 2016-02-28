package au.com.kbrsolutions.builditbigger;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import au.com.kbrsolutions.backend.myApi.MyApi;

/**
 * Created by business on 24/02/2016.
 */
// FIXME: 25/02/2016 - add Android Unit Test
public class JokesEndpointsAsyncTask extends AsyncTask<Activity, Void, String> {

    /**
    * Declares callback methods that have to be implemented by parent Activity
    */
    public interface JokesEndpointsCallbacks {
        void processResponseFromGce(String reponse);
    }

    private JokesEndpointsCallbacks mCallbacks;
    private static MyApi myApiService = null;

    private final static String LOG_TAG = JokesEndpointsAsyncTask.class.getSimpleName();

    JokesEndpointsAsyncTask(JokesEndpointsCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    protected String doInBackground(Activity... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")  // flocalhost's IP address in Android emulator
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")    // for Genymotion
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJokeFromJavaLibrary().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallbacks.processResponseFromGce("From GCE: " + result);
    }
}
