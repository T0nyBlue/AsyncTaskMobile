package lab3.AsyncTask;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * The SimpleAsyncTask app contains a button that launches an AsyncTask
 * which sleeps in the asynchronous thread for a random amount of time.
 */
public class MainActivity extends AppCompatActivity {

//    //Key for saving the state of the TextView
//    private static final String TEXT_STATE = "currentText";
//
//    // The TextView where we will show results
//    private TextView mTextView = null;
//
//    /**
//     * Initializes the activity.
//     * @param savedInstanceState The current state data
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //  Initialize mTextView
//        mTextView = (TextView) findViewById(R.id.textView1);
//
//        // Restore TextView if there is a savedInstanceState
//        if(savedInstanceState!=null){
//            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
//        }
//    }
//
//    /**`
//     * Handles the onCLick for the "Start Task" button. Launches the AsyncTask
//     * which performs work off of the UI thread.
//     *
//     * @param view The view (Button) that was clicked.
//     */
//    public void startTask (View view) {
//        // Put a message in the text view
//        mTextView.setText(R.string.napping);
//
//        // Start the AsyncTask.
//        // The AsyncTask has a callback that will update the textview.
//        new SimpleAsyncTask(mTextView).execute();
//    }
//
//    /**
//     * Saves the contents of the TextView to restore on configuration change.
//     * @param outState The bundle in which the state of the activity is saved       when it is spontaneously destroyed.
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Save the state of the TextView
//        outState.putString(TEXT_STATE, mTextView.getText().toString());
//    }
//
Button startTask;

    private static final String TEXT_STATE = "currentText";
    private TextView mTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTask = findViewById(R.id.startTask);

        mTextView = (TextView) findViewById(R.id.textViewAsync);

        startTask.setOnClickListener((View view) -> {
            // Put a message in the text view
            mTextView.setText(R.string.napping);
            // Start the AsyncTask.
            // The AsyncTask has a callback that will update the textview.
            new SimpleAsyncTask(mTextView).execute();
        });


        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }


    //    AsyncTask
    public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

        private TextView mTextView;

        public SimpleAsyncTask(TextView tv) {
            mTextView = tv;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Random r = new Random();
            int n = r.nextInt(11);

            int s = n * 200;
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        protected void onPostExecute(String result) {
            mTextView.setText(result);
        }
    }
}