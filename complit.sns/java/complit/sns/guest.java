package complit.sns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class guest extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
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
