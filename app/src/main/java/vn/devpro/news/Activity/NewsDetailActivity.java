package vn.devpro.news.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import vn.devpro.news.MainActivity;
import vn.devpro.news.R;

public class NewsDetailActivity extends AppCompatActivity {
    WebView wb;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        wb = (WebView) findViewById(R.id.wbdetail);
        String url = null;
        Intent intent = getIntent();
        if (intent.getExtras() != null) {

            url = intent.getStringExtra("LINK");
        }
        if (url != null) {
            dialog = new ProgressDialog(NewsDetailActivity.this);
            dialog.setTitle("Loading...");
            dialog.setCancelable(false);
            dialog.show();
            wb.setWebViewClient(onWebViewClient);
            wb.loadUrl(url);
        }
    }

    private WebViewClient onWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            dialog.dismiss();
            Toast.makeText(NewsDetailActivity.this,"Loading error...",Toast.LENGTH_SHORT).show();
        }
    };
}
