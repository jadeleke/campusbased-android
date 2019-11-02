package blueticks.fabitech.com.campusbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity{

    private final static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("user_data",
                        MODE_PRIVATE);
                boolean status =  sharedPreferences.getBoolean("loggedIn", false);
                if (status){
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);



    }


}
