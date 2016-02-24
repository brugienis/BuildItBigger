package au.com.kbrsolutions.builditbigger;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import au.com.kbrsolutions.backend.myApi.MyApi;

/**
 * Created by business on 24/02/2016.
 */
public class JokesEndpointsAsyncTaskTask extends AsyncTask<Activity, Void, String> {

    /**
    * Declares callback methods that have to be implemented by parent Activity
    */
    public interface JokesEndpointsCallbacks {
        void processResponseFromGce(String reponse);
    }

    private JokesEndpointsCallbacks mCallbacks;
    private static MyApi myApiService = null;
//    private Context context;

    private final static String LOG_TAG = JokesEndpointsAsyncTaskTask.class.getSimpleName();

    JokesEndpointsAsyncTaskTask() {

    }

    @Override
    protected String doInBackground(Activity... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")  // for Adroid emulator
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

//        context = params[0].first;
//        String name = params[0].second;
        Activity activity = params[0];
        try {
            mCallbacks = (JokesEndpointsCallbacks) activity;
        } catch (Exception e) {
            throw new RuntimeException(
                    activity.getResources()
                            .getString(R.string.callbacks_not_implemented, activity.toString()));
        }

        try {
            Log.v(LOG_TAG, "doInBackground - myApiService: " + myApiService);
            return myApiService.getJokeFromJavaLibrary().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v(LOG_TAG, "onPostExecute - result: " + result);
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        mCallbacks.processResponseFromGce("From GCE: " + result);
    }
}
