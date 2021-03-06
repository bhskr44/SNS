package complit.sns;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class getfacultyname extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;
    private Context context;
    private TextView roleField;

    public getfacultyname(Context context, TextView roleField, int flag) {
        this.context = context;
        this.roleField = roleField;
        this.byGetOrPost = flag;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            String username = arg0[0];
            String link = "http://bhaskar.esy.es/getfacultyname.php?username=" + username + "&password=" + arg0[1];
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
    }
}
