package complit.sns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addstudent extends AppCompatActivity {
    private Button addstudent;
    private TextView role;
    private EditText sbranch;
    private EditText semail;
    private EditText sname;
    private EditText spassword;
    private EditText sphone;
    private EditText sroll;
    private EditText ssem;
    private EditText susername;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        this.sname = (EditText) findViewById(R.id.sname);
        this.susername = (EditText) findViewById(R.id.susername);
        this.sroll = (EditText) findViewById(R.id.sroll);
        this.sbranch = (EditText) findViewById(R.id.sbranch);
        this.semail = (EditText) findViewById(R.id.semail);
        this.sphone = (EditText) findViewById(R.id.sphone);
        this.spassword = (EditText) findViewById(R.id.spassword);
        this.ssem = (EditText) findViewById(R.id.ssem);
        this.role = (TextView) findViewById(R.id.status);
    }

    public void onclick(View view) {
        String name = this.sname.getText().toString();
        String username = this.susername.getText().toString();
        String roll = this.sroll.getText().toString();
        String sem = this.ssem.getText().toString();
        String branch = this.sbranch.getText().toString();
        String email = this.semail.getText().toString();
        String phone = this.sphone.getText().toString();
        String password = this.spassword.getText().toString();
        new addstd(this, this.role, 0).execute(new String[]{name, username, branch, roll, sem, email, phone, password});
        String retain = this.role.getText().toString();
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
        } else if (id != R.id.action_blockstudent) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivity(new Intent(getApplicationContext(), srejectfor.class));
            return true;
        }
    }
}
