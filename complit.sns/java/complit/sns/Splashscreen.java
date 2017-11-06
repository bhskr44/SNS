package complit.sns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splashscreen extends Activity {
    Thread splashTread;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getWindow().setFormat(1);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
        this.splashTread = new Thread() {
            public void run() {
                int waited = 0;
                while (waited < 3500) {
                    try {
                        AnonymousClass1.sleep(100);
                        waited += 100;
                    } catch (InterruptedException e) {
                        Splashscreen.this.finish();
                        return;
                    } catch (Throwable th) {
                        Splashscreen.this.finish();
                    }
                }
                Intent intent = new Intent(Splashscreen.this, home.class);
                intent.setFlags(65536);
                Splashscreen.this.startActivity(intent);
                Splashscreen.this.finish();
                Splashscreen.this.finish();
            }
        };
        this.splashTread.start();
    }
}
