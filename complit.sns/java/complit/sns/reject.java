package complit.sns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class reject extends AppCompatActivity {
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);
        TextView rtvstudent = (TextView) findViewById(R.id.rtvstudent);
        TextView rtvfaculty = (TextView) findViewById(R.id.rtvfaculty);
        ImageView ahome = (ImageView) findViewById(R.id.ahome);
        ImageView aupload = (ImageView) findViewById(R.id.aupload);
        ImageView areject = (ImageView) findViewById(R.id.areject);
    }

    public void onclick(View v) {
        if (v.getId() == R.id.ahome) {
            startActivity(new Intent(getApplicationContext(), aafterlogin.class));
        } else if (v.getId() == R.id.aupload) {
            startActivity(new Intent(getApplicationContext(), upload.class));
        } else if (v.getId() == R.id.areject) {
            startActivity(new Intent(getApplicationContext(), reject.class));
        } else if (v.getId() == R.id.rtvstudent) {
            startActivity(new Intent(getApplicationContext(), srejectfor.class));
        } else {
            startActivity(new Intent(getApplicationContext(), frejectfor.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
            return true;
        } else if (id == R.id.action_addstudent) {
            startActivity(new Intent(getApplicationContext(), addstudent.class));
            return true;
        } else if (id == R.id.action_addfaculty) {
            startActivity(new Intent(getApplicationContext(), fregister.class));
            return true;
        } else if (id == R.id.action_blockfaculty) {
            startActivity(new Intent(getApplicationContext(), frejectfor.class));
            return true;
        } else if (id == R.id.action_blockstudent) {
            startActivity(new Intent(getApplicationContext(), srejectfor.class));
            return true;
        } else {
            shareText();
            return super.onOptionsItemSelected(item);
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
