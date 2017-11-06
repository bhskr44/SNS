package complit.sns;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class addstd extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;
    private Context context;
    private TextView roleField;

    public addstd(Context context, TextView roleField, int flag) {
        this.context = context;
        this.roleField = roleField;
        this.byGetOrPost = flag;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            String name = arg0[0];
            String username = arg0[1];
            String roll = arg0[2];
            String branch = arg0[3];
            String sem = arg0[4];
            String email = arg0[5];
            String phone = arg0[6];
            String link = "http://bhaskar.esy.es/addstudent.php?name=" + name + "&username=" + username + "&roll=" + roll + "&branch=" + branch + "&semester=" + sem + "&email=" + email + "&phone=" + phone + "&password=" + arg0[7] + "&role=Allowed";
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            BufferedReader in = new BufferedReader(new InputStreamReader(client.execute(request).getEntity().getContent()));
            StringBuffer sb = new StringBuffer(BuildConfig.FLAVOR);
            String str = BuildConfig.FLAVOR;
            str = in.readLine();
            if (str != null) {
                sb.append(str);
            }
            in.close();
            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public void onPostExecute(String result) {
        this.roleField.setText(result);
        if ("Record Inserted Succesfully !!".equals(result)) {
            new Builder(this.context).setTitle("Register Successfull").setMessage("The Registered User Can Use the App Now.").setPositiveButton("Okay", new OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(addstd.this.context.getApplicationContext(), "Thank You For Registering", 0).show();
                }
            }).setIcon(17301520).show();
        } else {
            new Builder(this.context).setTitle("Register Unsuccessfull").setMessage("Opps!! Something is wrong, Try Again !!").setPositiveButton("Okay", new OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(addstd.this.context.getApplicationContext(), "Make Sure You are connected to Internet", 0).show();
                }
            }).setIcon(17301560).show();
        }
    }
}
