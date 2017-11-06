package complit.sns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class flogin extends AppCompatActivity {
    public static final String Password = "passwordKey";
    public static final String Username = "nameKey";
    static Context context = null;
    public static final String mypreference = "mypref";
    private TextView role;
    SharedPreferences sharedpreferences;
    private Button slogin;
    private EditText spass;
    private EditText suser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        this.suser = (EditText) findViewById(R.id.susern);
        this.spass = (EditText) findViewById(R.id.spassword);
        this.slogin = (Button) findViewById(R.id.floginbutton);
        this.role = (TextView) findViewById(R.id.role);
        this.sharedpreferences = getSharedPreferences(mypreference, 0);
        if (this.sharedpreferences.contains(Username)) {
            this.suser.setText(this.sharedpreferences.getString(Username, BuildConfig.FLAVOR));
        }
        if (this.sharedpreferences.contains(Password)) {
            this.spass.setText(this.sharedpreferences.getString(Password, BuildConfig.FLAVOR));
        }
    }

    public void clear(View view) {
        this.suser = (EditText) findViewById(R.id.susern);
        this.spass = (EditText) findViewById(R.id.spassword);
        this.suser.setText(BuildConfig.FLAVOR);
        this.spass.setText(BuildConfig.FLAVOR);
    }

    public void login(View view) {
        String username = this.suser.getText().toString();
        String password = this.spass.getText().toString();
        Editor editor = this.sharedpreferences.edit();
        editor.putString(Username, username);
        editor.putString(Password, password);
        editor.commit();
        new loginf(this, this.role, 0).execute(new String[]{username, password});
        String retain = this.role.getText().toString();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contact) {
            startActivity(new Intent(getApplicationContext(), guest.class));
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
