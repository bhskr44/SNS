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

public class changeoldpassword extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;
    private Context context;
    private TextView roleField;

    public changeoldpassword(Context context, TextView roleField, int flag) {
        this.context = context;
        this.roleField = roleField;
        this.byGetOrPost = flag;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            String username = arg0[0];
            String password = arg0[1];
            String link = "http://bhaskar.esy.es/updatepasswrd.php?username=" + username + "&password=" + password + "&newpassword=" + arg0[2];
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
        if ("Unsuccessful 0".equals(result)) {
            new Builder(this.context).setTitle("Pasword Not Changed").setMessage("Check Your Internet Connection.").setPositiveButton("Okay", new OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(changeoldpassword.this.context.getApplicationContext(), "Thank You", 0).show();
                }
            }).setIcon(17301543).show();
        } else {
            new Builder(this.context).setTitle("Pasword Changed").setMessage("Next You have to enter your new Password while Loggin in.").setPositiveButton("Okay", new OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(changeoldpassword.this.context.getApplicationContext(), "Thank You", 0).show();
                }
            }).setIcon(17301520).show();
        }
    }
}
