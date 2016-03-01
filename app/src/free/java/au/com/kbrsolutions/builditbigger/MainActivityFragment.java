package au.com.kbrsolutions.builditbigger;

/**
 * Created by business on 8/12/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import au.com.kbrsolutions.jokeandroidlib.JokeViewActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements JokesEndpointsAsyncTask.JokesEndpointsCallbacks {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button getJokeBtn = (Button) root.findViewById(R.id.getJokeBtn);
        getJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendJokeAsyncRequestToGce();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void launchJokeActivity(View view) {
        sendJokeAsyncRequestToGce();
    }

    private String sendJokeAsyncRequestToGce() {
        new JokesEndpointsAsyncTask(this).execute();
        return null;
    }

    @Override
    public void processResponseFromGce(String reponse) {
        Intent intent = new Intent(getContext(), JokeViewActivity.class);
        intent.putExtra(JokeViewActivity.JOKE_KEY, "From MainActivity: " + reponse);
        startActivity(intent);
    }
}
