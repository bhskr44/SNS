package complit.sns;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class shome extends AppCompatActivity {
    public static final String Password = "passwordKey";
    public static final String Username = "nameKey";
    public static final String mypreference = "mypref";
    private Button changeoldpass;
    final Context context = this;
    View o;
    private TextView role;
    private TextView rrole;
    SharedPreferences sharedpreferences;
    private EditText spass;
    private EditText suser;
    private EditText updatepassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shome);
        this.suser = (EditText) findViewById(R.id.susern);
        this.spass = (EditText) findViewById(R.id.spassword);
        this.updatepassword = (EditText) findViewById(R.id.updatespass);
        this.role = (TextView) findViewById(R.id.role);
        this.rrole = (TextView) findViewById(R.id.rrole);
        this.changeoldpass = (Button) findViewById(R.id.changeoldpass);
        this.sharedpreferences = getSharedPreferences(mypreference, 0);
        if (this.sharedpreferences.contains(Username)) {
            this.suser.setText(this.sharedpreferences.getString(Username, BuildConfig.FLAVOR));
        }
        if (this.sharedpreferences.contains(Password)) {
            this.spass.setText(this.sharedpreferences.getString(Password, BuildConfig.FLAVOR));
        }
        getnamecall();
    }

    private void getnamecall() {
        String username = this.suser.getText().toString();
        String password = this.spass.getText().toString();
        new getname(this, this.role, 0).execute(new String[]{username, password});
        String retain = this.role.getText().toString();
    }

    public void changeoldpass(View view) {
        String username = this.suser.getText().toString();
        String password = this.spass.getText().toString();
        String newpassword = this.updatepassword.getText().toString();
        new changeoldpassword(this, this.rrole, 0).execute(new String[]{username, password, newpassword});
        String retain = this.rrole.getText().toString();
    }

    public void onrender(View v) {
        if (v.getId() == R.id.shome) {
            startActivity(new Intent(getApplicationContext(), shome.class));
        } else if (v.getId() == R.id.sroutine) {
            startActivity(new Intent(getApplicationContext(), sroutine.class));
        } else if (v.getId() == R.id.srecord) {
            startActivity(new Intent(getApplicationContext(), srecord.class));
        } else if (v.getId() == R.id.snews) {
            startActivity(new Intent(getApplicationContext(), snews.class));
        } else if (v.getId() == R.id.result) {
            startActivity(new Intent(getApplicationContext(), result.class));
        } else {
            startActivity(new Intent(getApplicationContext(), supdates.class));
        }
    }

    public void open(View view) {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("This will take you to Whatsapp. Do you want to Continue??");
        alertDialogBuilder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(shome.this, "You clicked Yes.", 0).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hi.! I am a student of Assam Engineering Institute want to have a conversation with You.Please, Respond as soon as possible.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                shome.this.startActivity(sendIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(shome.this, "You clicked No.", 0).show();
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_routine1) {
            startActivity(new Intent(getApplicationContext(), sroutine.class));
            return true;
        } else if (id == R.id.action_record) {
            startActivity(new Intent(getApplicationContext(), srecord.class));
            return true;
        } else if (id == R.id.whatsapp) {
            open(this.o);
            return true;
        } else if (id == R.id.action_share) {
            shareText();
            return true;
        } else {
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
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
