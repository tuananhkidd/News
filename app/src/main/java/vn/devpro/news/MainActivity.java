package vn.devpro.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import vn.devpro.news.Activity.NewsDetailActivity;
import vn.devpro.news.Adapter.NewsAdapter;
import vn.devpro.news.models.News;

public class MainActivity extends AppCompatActivity {

    ListView lvNews;
    NewsAdapter adapter;
    ArrayList<News> list;
    final String vnexpress = "https://vnexpress.net/rss/the-thao.rss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNews = (ListView) findViewById(R.id.lvNews);
        list = new ArrayList<>();

        if(!isOnline()){
            Toast.makeText(this,"Please connect internet !",Toast.LENGTH_LONG).show();
            return;
        }else{

            new loadRSS().execute(vnexpress);

            lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);

                    intent.putExtra("LINK",adapter.getItem(i).getLink());
                    startActivity(intent);
                }
            });
        }




    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

    class loadRSS extends AsyncTask<String,Void,ArrayList<News>>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading.....");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            String url = strings[0];
            ArrayList<News> result = new ArrayList<>();
            try {
                Document doc =  Jsoup.connect(url).get();
                Elements element = doc.select("item");
                for(Element e:element){
                    String title = e.select("title").text();
                    String link = e.select("link").text();
                    String des = e.select("description").text();

                    Document linkImageUrl = Jsoup.parse(des);
                    String imgUrl = linkImageUrl.select("img").get(0).attr("src");

                    result.add(new News(title,link,imgUrl));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<News> newses) {

            dialog.dismiss();
            adapter = new NewsAdapter(MainActivity.this,R.layout.item_news_layout,newses);
            lvNews.setAdapter(adapter);
        }
    }
}
