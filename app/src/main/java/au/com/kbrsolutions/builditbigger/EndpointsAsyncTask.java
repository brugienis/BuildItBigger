package au.com.kbrsolutions.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import au.com.kbrsolutions.backend.myApi.MyApi;

/**
 * Created by business on 24/02/2016.
 */
// FIXME: 25/02/2016  - remove before sending for review
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

    private final static String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")  // for Adroid emulator
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")  // for Genymotion
//                    .setRootUrl("http://localhost:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            Log.v(LOG_TAG, "doInBackground - myApiService: " + myApiService);
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v(LOG_TAG, "onPostExecute - result: " + result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
