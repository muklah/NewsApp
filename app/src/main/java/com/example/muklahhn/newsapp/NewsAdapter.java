package com.example.muklahhn.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Muklah H N on 14/07/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerViewHolder> {

    ArrayList<NewsItem> mNewsItems;
    private Context context;
    private final RecyclerViewAdapterOnClickHandler mClickHandler;

    public interface RecyclerViewAdapterOnClickHandler {
        void onClick(NewsItem movie);
    }

    public NewsAdapter(RecyclerViewAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView SectionName;
        public final TextView WebTitle;

        public RecyclerViewHolder(View view) {
            super(view);
            SectionName = (TextView)itemView.findViewById(R.id.section_name);
            WebTitle = (TextView)itemView.findViewById(R.id.web_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            NewsItem movie = mNewsItems.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.SectionName.setText(String.valueOf(mNewsItems.get(position).getSectionName()));
        holder.WebTitle.setText(String.valueOf(mNewsItems.get(position).getWebTitle()));
    }


    @Override
    public int getItemCount() {
        if (null == mNewsItems)
            return 0;
        else {
            return mNewsItems.size();
        }
    }

    public void setNewsData(ArrayList<NewsItem> newsData) {
        mNewsItems = newsData;
        notifyDataSetChanged();
    }

}