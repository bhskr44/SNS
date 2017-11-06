package complit.sns;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class sroutine extends AppCompatActivity {
    public static boolean setfav = false;
    MenuItem cancel;
    MenuItem fav;
    private NotificationManager mNotificationManager;
    NotificationManager manager;
    TextView ms1cse;
    TextView ms2cse;
    TextView ms3cse;
    TextView ms4cse;
    TextView ms5cse;
    TextView ms6cse;
    TextView ms7cse;
    private int notificationID = 100;
    private int numMessages = 0;
    View o;
    MenuItem remove;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sroutine);
        this.ms1cse = (TextView) findViewById(R.id.ms1cse);
        this.ms2cse = (TextView) findViewById(R.id.ms2cse);
        this.ms3cse = (TextView) findViewById(R.id.ms3cse);
        this.ms4cse = (TextView) findViewById(R.id.ms4cse);
        this.ms5cse = (TextView) findViewById(R.id.ms5cse);
        this.ms6cse = (TextView) findViewById(R.id.ms6cse);
        this.ms7cse = (TextView) findViewById(R.id.ms7cse);
        this.fav = (MenuItem) findViewById(R.id.setfav);
        this.remove = (MenuItem) findViewById(R.id.remove);
        this.cancel = (MenuItem) findViewById(R.id.cancel);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
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

    public void days(View v) {
        if (v.getId() == R.id.csemon) {
            startActivity(new Intent(getApplicationContext(), sroutine.class));
        } else if (v.getId() == R.id.csetue) {
            startActivity(new Intent(getApplicationContext(), rcsetue.class));
        } else if (v.getId() == R.id.csewed) {
            startActivity(new Intent(getApplicationContext(), rcsewed.class));
        } else if (v.getId() == R.id.csethu) {
            startActivity(new Intent(getApplicationContext(), rcsethu.class));
        } else if (v.getId() == R.id.csefri) {
            startActivity(new Intent(getApplicationContext(), rcsefri.class));
        } else {
            startActivity(new Intent(getApplicationContext(), rcsesat.class));
        }
    }

    public void open(View view) {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("This will take you to Whatsapp. Do you want to Continue??");
        alertDialogBuilder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(sroutine.this, "You clicked Yes.", 0).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hi.! I am a student of Assam Engineering Institute want to have a conversation with You.Please, Respond as soon as possible.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                sroutine.this.startActivity(sendIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(sroutine.this, "You clicked No.", 0).show();
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
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
        } else {
            if (id == R.id.action_share) {
                shareText();
            } else {
                finish();
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public void classr(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, 5);
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.setfav /*2131558673*/:
                        Toast.makeText(sroutine.this, "Set Favorite", 0).show();
                        Dialog d = new Dialog(sroutine.this);
                        d.setContentView(R.layout.dialog);
                        d.setTitle("Class Time");
                        d.show();
                        break;
                    case R.id.remove /*2131558674*/:
                        Toast.makeText(sroutine.this, "Remove Favorite", 0).show();
                        break;
                    case R.id.cancel /*2131558675*/:
                        Toast.makeText(sroutine.this, "Cancel", 0).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
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
