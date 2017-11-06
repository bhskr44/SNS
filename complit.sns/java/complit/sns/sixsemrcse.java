package complit.sns;

import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class sixsemrcse extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onrender(View v) {
        if (v.getId() == R.id.shome) {
            startActivity(new Intent(getApplicationContext(), shome.class));
        } else if (v.getId() == R.id.sroutine) {
            startActivity(new Intent(getApplicationContext(), sixsemrcse.class));
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
                Toast.makeText(sixsemrcse.this, "You clicked Yes.", 0).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hi.! I am a student of Assam Engineering Institute want to have a conversation with You.Please, Respond as soon as possible.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                sixsemrcse.this.startActivity(sendIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(sixsemrcse.this, "You clicked No.", 0).show();
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
        if (item.getItemId() == R.id.action_share) {
            shareText();
        }
        return super.onOptionsItemSelected(item);
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
