package mobile.splistener;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_);

        goToNextScreen();
    }

    private void goToNextScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent int_login = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(int_login);
                finish();


            }
        }, 5000);
    }


}
