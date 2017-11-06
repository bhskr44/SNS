package complit.sns;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class upload extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView ahome;
    ImageView areject;
    ImageView aupload;
    Context context;
    EditText edit;
    ImageView imagetoupload;
    Button upload;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        this.upload = (Button) findViewById(R.id.uploadbutton);
        this.edit = (EditText) findViewById(R.id.imagename);
        this.ahome = (ImageView) findViewById(R.id.ahome);
        this.aupload = (ImageView) findViewById(R.id.aupload);
        this.areject = (ImageView) findViewById(R.id.areject);
        this.imagetoupload = (ImageView) findViewById(R.id.imagetoupload);
    }

    public void onclick(View v) {
        if (v.getId() == R.id.ahome) {
            startActivity(new Intent(getApplicationContext(), aafterlogin.class));
        } else if (v.getId() == R.id.aupload) {
            startActivity(new Intent(getApplicationContext(), upload.class));
        } else if (v.getId() == R.id.areject) {
            startActivity(new Intent(getApplicationContext(), reject.class));
        } else if (v.getId() == R.id.imagetoupload) {
            startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), RESULT_LOAD_IMAGE);
        }
    }

    public void onupload(View view) {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("This Function is not working properly,Please wait for the next version.");
        alertDialogBuilder.setPositiveButton("It's Okay", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(upload.this, "Thank You for your co-operation.", 0).show();
            }
        });
        alertDialogBuilder.create().show();
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == RESULT_LOAD_IMAGE && resultcode == -1 && data != null) {
            this.imagetoupload.setImageURI(data.getData());
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
