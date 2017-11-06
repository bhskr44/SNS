package complit.sns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class home extends AppCompatActivity {
    static TextView textView17;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView17 = (TextView) findViewById(R.id.textView17);
        gettime();
    }

    public static String gettime() {
        try {
            String currentTime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
            textView17.setText(currentTime);
            return currentTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onclick(View v) {
        if (v.getId() == R.id.ustvstudent) {
            startActivity(new Intent(getApplicationContext(), slogin.class));
        } else if (v.getId() == R.id.ustvfaculty) {
            startActivity(new Intent(getApplicationContext(), flogin.class));
        } else if (v.getId() == R.id.ustvadministrator) {
            startActivity(new Intent(getApplicationContext(), alogin.class));
        } else {
            startActivity(new Intent(getApplicationContext(), guest.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            shareText();
            return true;
        } else if (id != R.id.action_contact) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivity(new Intent(getApplicationContext(), guest.class));
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
