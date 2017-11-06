package complit.sns;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class getnews extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;
    private Context context;
    public TableLayout result;
    private TextView roleField;

    public getnews(Context context, TextView roleField, TableLayout result) {
        this.context = context;
        this.roleField = roleField;
        this.result = result;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            String status = arg0[0];
            String link = "http://bhaskar.esy.es/getnews.php?xyz=" + status + "&whom=" + arg0[1];
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
        if (result.equals("Sorry")) {
            this.result.removeAllViews();
            this.roleField.setText("Sorry No New News found ! \n Try After Sometime.");
            return;
        }
        String[] splitStringArray = result.split(",");
        int strInt = splitStringArray.length / 10;
        this.result.removeAllViews();
        this.roleField.setText(String.valueOf(strInt) + " New News Are Available.");
        int k = 0;
        int i = 0;
        while (i < splitStringArray.length / 1) {
            int j = 0;
            int k2 = k;
            while (j < 1) {
                switch (j) {
                    case R.styleable.View_android_theme /*0*/:
                        TableRow tbRow0 = new TableRow(this.context);
                        TextView tv0 = new TextView(this.context);
                        k = k2 + 1;
                        tv0.setText("  *  " + splitStringArray[k2]);
                        tbRow0.addView(tv0);
                        this.result.addView(tbRow0);
                        break;
                    default:
                        k = k2;
                        break;
                }
                j++;
                k2 = k;
            }
            TableRow tbRow10 = new TableRow(this.context);
            TextView tv10 = new TextView(this.context);
            tv10.setText("========================================= ");
            tbRow10.addView(tv10);
            this.result.addView(tbRow10);
            i++;
            k = k2;
        }
    }
}
