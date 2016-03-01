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

import au.com.kbrsolutions.builditbigger.utils.ProgressBarHandler;
import au.com.kbrsolutions.jokeandroidlib.JokeViewActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements JokesEndpointsAsyncTask.JokesEndpointsCallbacks {

    private ProgressBarHandler mProgressBarHandler;

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

        mProgressBarHandler = new ProgressBarHandler(getContext());

        return root;
    }

//    public void launchJokeActivity(View view) {
//        sendJokeAsyncRequestToGce();
//    }

    private String sendJokeAsyncRequestToGce() {
        mProgressBarHandler.show();
        new JokesEndpointsAsyncTask(this).execute();
        return null;
    }

    @Override
    public void processResponseFromGce(String reponse) {
        Intent intent = new Intent(getContext(), JokeViewActivity.class);
        intent.putExtra(JokeViewActivity.JOKE_KEY, "From MainActivity: " + reponse);
        startActivity(intent);
        mProgressBarHandler.hide();
    }

}
