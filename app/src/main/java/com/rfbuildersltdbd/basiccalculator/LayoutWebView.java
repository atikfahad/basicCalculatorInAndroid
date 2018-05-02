package com.rfbuildersltdbd.basiccalculator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

import java.net.URL;

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
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()){
            webView.loadUrl("file:///android_asset/index.html");
        }
        else
            Toast.makeText(this, "You are connected with Internet!", Toast.LENGTH_LONG).show();
    }
    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long>{

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(URL... urls) {
            return null;
        }
    }
}
