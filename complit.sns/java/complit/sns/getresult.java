package complit.sns;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
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

public class getresult extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;
    private Context context;
    public TableLayout result;
    private TextView roleField;

    public getresult(Context context, TextView roleField, TableLayout result) {
        this.context = context;
        this.roleField = roleField;
        this.result = result;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            String link = "http://bhaskar.esy.es/result.php?roll=" + arg0[0];
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
            this.roleField.setText("Sorry No Record Found ! \n Try with different Roll No.");
            return;
        }
        String[] splitStringArray = result.split(",");
        int strInt = splitStringArray.length / 10;
        this.result.removeAllViews();
        this.roleField.setText(String.valueOf(strInt) + " Record found !");
        int k = 0;
        int i = 0;
        while (i < splitStringArray.length / 10) {
            View textView;
            int j = 0;
            int k2 = k;
            while (j < 11) {
                switch (j) {
                    case R.styleable.View_android_theme /*0*/:
                        TableRow tbRow0 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Roll No:    " + splitStringArray[k2]);
                        tbRow0.addView(textView);
                        this.result.addView(tbRow0);
                        break;
                    case R.styleable.View_android_focusable /*1*/:
                        TableRow tbRow1 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Semester:   " + splitStringArray[k2]);
                        tbRow1.addView(textView);
                        this.result.addView(tbRow1);
                        break;
                    case R.styleable.View_paddingStart /*2*/:
                        TableRow tbRow2 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Maths:     " + splitStringArray[k2]);
                        tbRow2.addView(textView);
                        this.result.addView(tbRow2);
                        break;
                    case R.styleable.View_paddingEnd /*3*/:
                        TableRow tbRow3 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Science:   " + splitStringArray[k2]);
                        tbRow3.addView(textView);
                        this.result.addView(tbRow3);
                        break;
                    case R.styleable.View_theme /*4*/:
                        TableRow tbRow4 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Physics:   " + splitStringArray[k2]);
                        tbRow4.addView(textView);
                        this.result.addView(tbRow4);
                        break;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        TableRow tbRow5 = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Chemistry: " + splitStringArray[k2]);
                        tbRow5.addView(textView);
                        this.result.addView(tbRow5);
                        break;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        textView = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Biology:  " + splitStringArray[k2]);
                        textView.addView(textView);
                        this.result.addView(textView);
                        break;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        textView = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Stats.:  " + splitStringArray[k2]);
                        textView.addView(textView);
                        this.result.addView(textView);
                        break;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        textView = new TableRow(this.context);
                        textView = new TextView(this.context);
                        k = k2 + 1;
                        textView.setText("Total:   " + splitStringArray[k2]);
                        textView.addView(textView);
                        this.result.addView(textView);
                        break;
                    case R.styleable.Toolbar_popupTheme /*9*/:
                        textView = new TableRow(this.context);
                        View tv9 = new TextView(this.context);
                        k = k2 + 1;
                        tv9.setText("Result:  " + splitStringArray[k2]);
                        textView.addView(tv9);
                        this.result.addView(textView);
                        break;
                    default:
                        k = k2;
                        break;
                }
                j++;
                k2 = k;
            }
            TableRow tbRow10 = new TableRow(this.context);
            textView = new TextView(this.context);
            textView.setText("========================================= ");
            tbRow10.addView(textView);
            this.result.addView(tbRow10);
            i++;
            k = k2;
        }
    }
}
