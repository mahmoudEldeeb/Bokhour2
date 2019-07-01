package com.eldeeb.bokhour.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.eldeeb.bokhour.R;

public class SplashActivity extends AppCompatActivity {
    ImageView splash;

    Animation fade0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash= (ImageView) findViewById(R.id.splash);
        fade0 = AnimationUtils.loadAnimation(this, R.anim.push_left_enter);
        splash.startAnimation(fade0);

        fade0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(SplashActivity.this, Main.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(R.anim.push_up_enter, R.anim.push_up_exit);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
