package complit.sns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class alogin extends AppCompatActivity {
    Button b1;
    Button b2;
    int counter = 3;
    EditText ed1;
    EditText ed2;
    TextView tx1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alogin);
        this.b1 = (Button) findViewById(R.id.b1);
        this.ed1 = (EditText) findViewById(R.id.ausern);
        this.ed2 = (EditText) findViewById(R.id.apassword);
        this.b2 = (Button) findViewById(R.id.b2);
        this.tx1 = (TextView) findViewById(R.id.attempts);
        this.tx1.setVisibility(8);
        this.b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (alogin.this.ed1.getText().toString().equals(BuildConfig.FLAVOR) && alogin.this.ed2.getText().toString().equals(BuildConfig.FLAVOR)) {
                    Toast.makeText(alogin.this.getApplicationContext(), "Redirecting...", 0).show();
                    alogin.this.startActivity(new Intent(alogin.this.getApplicationContext(), aafterlogin.class));
                    return;
                }
                Toast.makeText(alogin.this.getApplicationContext(), "Wrong Credentials", 0).show();
                alogin.this.tx1.setVisibility(0);
                alogin.this.tx1.setBackgroundColor(-65536);
                alogin complit_sns_alogin = alogin.this;
                complit_sns_alogin.counter--;
                alogin.this.tx1.setText(Integer.toString(alogin.this.counter));
                if (alogin.this.counter == 0) {
                    alogin.this.b1.setEnabled(false);
                }
            }
        });
        this.b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                alogin.this.finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            finish();
            return true;
        } else if (id != R.id.action_share) {
            return super.onOptionsItemSelected(item);
        } else {
            shareText();
            return true;
        }
    }

    public void shareText() {
        Intent share = new Intent("android.intent.action.SEND");
        share.setType("text/plain");
        share.addFlags(524288);
        share.putExtra("android.intent.extra.SUBJECT", "Student Notifying System of AEI");
        share.putExtra("android.intent.extra.TEXT", "Hi,Share our app if have liked our app, Please do share http://www.bhaskar.esy.es/download/sns.apk");
        startActivity(Intent.createChooser(share, "SNS of AEI"));
    }
}
