package vn.devpro.news.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.news.R;
import vn.devpro.news.models.News;

/**
 * Created by tuaan on 9/16/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    private int layout;
    private ArrayList<News> list;
    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_layout,parent,false);

            viewHolder.imgAvt = convertView.findViewById(R.id.imgAvt);
            viewHolder.txtTilte = convertView.findViewById(R.id.txtTitle);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        News news = list.get(position);
        viewHolder.txtTilte.setText(news.getTitle());

        Picasso.with(context).load(news.getImgURL()).into(viewHolder.imgAvt);

        return convertView;
    }

    class ViewHolder{
        private ImageView imgAvt;
        private TextView txtTilte;
    }
}
