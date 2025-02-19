package newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SplashMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_main);

        Intent intent=new Intent(getApplicationContext(), NewsActivity.class);


        new Handler().postDelayed(() -> {

            startActivity(intent);

            finish();
        },1000);
    }
}