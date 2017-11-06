package complit.sns;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class fwed extends AppCompatActivity {
    View o;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwed);
    }

    public void days(View v) {
        if (v.getId() == R.id.mon) {
            startActivity(new Intent(getApplicationContext(), froutine.class));
        } else if (v.getId() == R.id.tue) {
            startActivity(new Intent(getApplicationContext(), ftue.class));
        } else if (v.getId() == R.id.wed) {
            startActivity(new Intent(getApplicationContext(), fwed.class));
        } else if (v.getId() == R.id.thu) {
            startActivity(new Intent(getApplicationContext(), fthu.class));
        } else if (v.getId() == R.id.fri) {
            startActivity(new Intent(getApplicationContext(), ffri.class));
        } else {
            startActivity(new Intent(getApplicationContext(), fsat.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_faculty, menu);
        return true;
    }

    public void onrender(View v) {
        if (v.getId() == R.id.fhome) {
            startActivity(new Intent(getApplicationContext(), fhome.class));
        } else if (v.getId() == R.id.froutine) {
            startActivity(new Intent(getApplicationContext(), froutine.class));
        } else if (v.getId() == R.id.fnews) {
            startActivity(new Intent(getApplicationContext(), fnews.class));
        } else {
            startActivity(new Intent(getApplicationContext(), updates.class));
        }
    }

    public void open(View view) {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("This will take you to Whatsapp. Do you want to Continue??");
        alertDialogBuilder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(fwed.this, "You clicked Yes.", 0).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hi.! I am a faculty of Assam Engineering Institute want to have a conversation with You.Please, Respond as soon as possible.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                fwed.this.startActivity(sendIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(fwed.this, "You clicked No.", 0).show();
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
            return true;
        } else if (id == R.id.whatsapp) {
            open(this.o);
            return true;
        } else if (id == R.id.action_share) {
            shareText();
            return true;
        } else {
            startActivity(new Intent(getApplicationContext(), froutine.class));
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
