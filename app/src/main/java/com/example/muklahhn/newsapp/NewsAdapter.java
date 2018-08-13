package com.example.muklahhn.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        public final TextView WebPublicationDate;
        public final TextView WebTitle;
        public final TextView TagsWebTitle;

        public RecyclerViewHolder(View view) {
            super(view);
            SectionName = itemView.findViewById(R.id.section_name);
            WebPublicationDate = itemView.findViewById(R.id.web_publication_date);
            WebTitle = itemView.findViewById(R.id.web_title);
            TagsWebTitle = itemView.findViewById(R.id.tags_web_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            NewsItem news = mNewsItems.get(adapterPosition);
            mClickHandler.onClick(news);
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
        holder.WebPublicationDate.setText(String.valueOf(mNewsItems.get(position).getWebPublicationDate()));
        holder.WebTitle.setText(String.valueOf(mNewsItems.get(position).getWebTitle()));
        holder.TagsWebTitle.setText("Author: " + String.valueOf(mNewsItems.get(position).getTagsWebTitle()));
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
