package com.rfbuildersltdbd.basiccalculator;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

import static android.os.Environment.getDataDirectory;

public class LayoutWebView extends AppCompatActivity {
    private static double value1 = 0, value2 = 0;
    private static String operator = null;
    private static double result = 0;
    static double memoryResult = 0;
    static boolean dotTest = true;

    public class WebAppInterface
    {
        Context mContext;

        WebAppInterface(Context ctx){
            this.mContext = ctx;
        }

        @android.webkit.JavascriptInterface
        public String addNum(String num)
        {
            return num;

        }
        @android.webkit.JavascriptInterface
        public String addOperator(String str, String opr)
        {
            value1 = Double.parseDouble(str);
            operator = opr;
            dotTest = true;
            return str + opr;
        }
        @android.webkit.JavascriptInterface
        public double reset(){
            dotTest = true;
            return 0;
        }
        @android.webkit.JavascriptInterface
        public double getResult(String value2)
        {
            switch (operator){
                case "+" : {
                    result = value1 + Double.parseDouble(value2);
                    break;
                }
                case "-" : {
                    result = value1 - Double.parseDouble(value2);
                    break;
                }
                case "*" : {
                    result = value1 * Double.parseDouble(value2);
                    break;
                }
                case "/" : {
                    result = value1 / Double.parseDouble(value2);
                    break;
                }
                default: {
                    result = 0;
                    break;
                }
            }
            dotTest = true;
            return result;
        }
    }
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_layout_web_view);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        if (shouldAskPermissions()) {
            askPermissions();
        }
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()){
                DownloadFilesTask filesToDownload = new DownloadFilesTask();
                filesToDownload.execute("https://avatars3.githubusercontent.com/u/26101936?s=400&u=85c109865c8a5bf937e9e9ca6c596a78416a67b9&v=4");
                webView.loadUrl("file:///android_asset/index.html");
        }
        else {
            Toast.makeText(this, "You are not connected with Internet! So, loading app from local", Toast.LENGTH_LONG).show();
            webView.loadUrl("file:///android_asset/index.html");
        }
    }


class DownloadFilesTask extends AsyncTask<String, String, String> {
    ProgressDialog pDialog;

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        String root = "";
        try {
            //String root = Environment.getExternalStorageDirectory().toString();
            //String root = Environment.getDataDirectory().toString();
            root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            //String root3 = Environment.getDataDirectory().toString();
            //root = getFilesDir().toString();
            Log.d("download folder path : ", root);
            System.out.println("Downloading");
            URL url = new URL(f_url[0]);

            URLConnection connection = url.openConnection();
            connection.connect();
            int lenghtOfFile = connection.getContentLength();
            publishProgress("Downloaded");
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream(root+"/atik.jpg");
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }

            output.flush();

            output.close();
            input.close();



        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return root;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        pDialog.setMessage(values[0]);
        //super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String file_url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Log.d("FILE_URL", file_url);
        intent.setDataAndType(Uri.parse("file://" + file_url + "/atik.jpg"), "image/*");
        startActivity(intent);
        pDialog.dismiss();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(LayoutWebView.this);
        pDialog.setMessage("Downloading Necessary Files");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

}
}
