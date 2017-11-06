package complit.sns;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class frejectfor extends AppCompatActivity {
    TextView blockfaculty;
    Context context;
    EditText faculty;
    TextView role;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frejectfor);
        this.faculty = (EditText) findViewById(R.id.facid);
        this.blockfaculty = (TextView) findViewById(R.id.blockfaculty);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    public void onclick(View v) {
        if (v.getId() == R.id.ahome) {
            startActivity(new Intent(getApplicationContext(), aafterlogin.class));
        } else if (v.getId() == R.id.aupload) {
            startActivity(new Intent(getApplicationContext(), upload.class));
        } else {
            startActivity(new Intent(getApplicationContext(), reject.class));
        }
    }

    public void onblockclick(View v) {
        if (this.faculty.getText().toString().equals(BuildConfig.FLAVOR)) {
            new Builder(this.context).setTitle("Enter Faculty Id.").setMessage("Faculty Id field is Empty,Enter Faculty Id to proceed forward.").setPositiveButton("Okay", new OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(frejectfor.this.context.getApplicationContext(), "Reenter the Faculty Id.", 0).show();
                }
            });
            return;
        }
        new blockf(this, this.role, 0).execute(new String[]{facid});
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
