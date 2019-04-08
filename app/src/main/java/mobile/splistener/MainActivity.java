package mobile.splistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.soundpays.sdk.Soundpays;
import com.soundpays.sdk.callbacks.SoundpaysAudioCallback;

public class MainActivity extends AppCompatActivity {
    Soundpays soundpays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundpays = Soundpays.getInstance(MainActivity.this.getApplication());
        beginAudioScan();
    }

    private void beginAudioScan() {

        //Please declare what you wish to do below once a code has been retrieved


        try {
            soundpays.beginAudioScan(new SoundpaysAudioCallback() {
                @Override
                public void onSuccess() {
                    //This is the main callback when a code is detected use getCode to retrieve the code.
                    receivedCode(getCode());

                }

                @Override
                public void onFailure(Exception e) {
                    //beginAudioScan();
                }
            }, 10);
        } catch (java.lang.IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void receivedCode(final String code) {
        /* The code will either return as
        0 for timeout or non valid code if a valid list of codes was specified when calling soundpays.beginAudioScan()
       -1 if sdk requires app mic permissions
        8 digit code if code was detected and considered valid.

        */
        // code="";
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stopAudioScan();

                if (code == "-1") {
                    Toast.makeText(MainActivity.this, "Mic Permission Needed", Toast.LENGTH_LONG).show();
                    //requestPermissionIfNeeded();
                } else if (code == "0") {
                    beginAudioScan();
                    // } else if (code == "uou") {
//                    Toast.makeText(Scanning_screen.this, "Scanning timed out or valid code not found", Toast.LENGTH_LONG).show();

//                    Txt_scanning.setText("SCAN");


                    //codeTextView.setText("Scanning timed out or valid code not found");
                    //} else if (code == "0") {
                } else {

                    //Text_scan_code.setText(code);
                    beginAudioScan();

                }


            }

        });

    }


    private void stopAudioScan() {
        //scanButton.setText("Scan");
        soundpays.stopAudioScan();
        //  scanning = false;
    }
}
