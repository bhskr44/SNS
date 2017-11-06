package complit.sns;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class srecord extends AppCompatActivity {
    private MediaRecorder myAudioRecorder;
    View o;
    private String outputFile = null;
    Button play;
    Button record;
    Button stop;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srecord);
        this.play = (Button) findViewById(R.id.button2);
        this.stop = (Button) findViewById(R.id.button3);
        this.record = (Button) findViewById(R.id.button);
        this.stop.setEnabled(false);
        this.play.setEnabled(false);
        gettime();
        String sep = File.separator;
        String newFolder = "SNS_RECORDINGS";
        new File(Environment.getExternalStorageDirectory().toString() + sep + newFolder).mkdir();
        this.outputFile = Environment.getExternalStorageDirectory().toString() + sep + newFolder + sep + gettime() + "Lctr.3gp";
        this.myAudioRecorder = new MediaRecorder();
        this.myAudioRecorder.setAudioSource(1);
        this.myAudioRecorder.setOutputFormat(1);
        this.myAudioRecorder.setAudioEncoder(3);
        this.myAudioRecorder.setOutputFile(this.outputFile);
        this.record.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    srecord.this.myAudioRecorder.prepare();
                    srecord.this.myAudioRecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                srecord.this.record.setEnabled(false);
                srecord.this.stop.setEnabled(true);
                Toast.makeText(srecord.this.getApplicationContext(), "Recording started", 1).show();
            }
        });
        this.stop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                srecord.this.myAudioRecorder.stop();
                srecord.this.myAudioRecorder.release();
                srecord.this.myAudioRecorder = null;
                srecord.this.stop.setEnabled(false);
                srecord.this.play.setEnabled(true);
                Toast.makeText(srecord.this.getApplicationContext(), "Audio recorded successfully", 1).show();
            }
        });
        this.play.setOnClickListener(new OnClickListener() {
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();
                try {
                    m.setDataSource(srecord.this.outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    m.prepare();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                m.start();
                Toast.makeText(srecord.this.getApplicationContext(), "Playing audio", 1).show();
            }
        });
    }

    public void onrender(View v) {
        if (v.getId() == R.id.shome) {
            startActivity(new Intent(getApplicationContext(), shome.class));
        } else if (v.getId() == R.id.sroutine) {
            startActivity(new Intent(getApplicationContext(), sroutine.class));
        } else if (v.getId() == R.id.srecord) {
            startActivity(new Intent(getApplicationContext(), srecord.class));
        } else if (v.getId() == R.id.result) {
            startActivity(new Intent(getApplicationContext(), result.class));
        } else if (v.getId() == R.id.snews) {
            startActivity(new Intent(getApplicationContext(), snews.class));
        } else {
            startActivity(new Intent(getApplicationContext(), supdates.class));
        }
    }

    public static String gettime() {
        try {
            return new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void open(View view) {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("This will take you to Whatsapp. Do you want to Continue??");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(srecord.this, "You clicked Yes.", 0).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hi.! I am a student of Assam Engineering Institute want to have a conversation with You.Please, Respond as soon as possible.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                srecord.this.startActivity(sendIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(srecord.this, "You clicked No.", 0).show();
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
