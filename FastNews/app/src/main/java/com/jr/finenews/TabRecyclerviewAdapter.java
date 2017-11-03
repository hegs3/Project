package com.jr.finenews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alfo6-6 on 2017-06-16.
 */

    public class TabRecyclerviewAdapter extends RecyclerView.Adapter<TabRecyclerviewAdapter.ViewHolder>{
        ArrayList<TabArraylist> newsItems;
        Context context;

    public TabRecyclerviewAdapter(ArrayList<TabArraylist> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    //
        @Override
        public TabRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

           final View itemView=LayoutInflater.from(context).inflate(R.layout.recycler_tab_form, parent, false);
           final ViewHolder holder=new ViewHolder(itemView);


            return holder;
        }

    //데이터 세팅!
    @Override
    public void onBindViewHolder(final TabRecyclerviewAdapter.ViewHolder holder, int position) {

        holder.title.setText(newsItems.get(position).getTitle());
        holder.content.setText(newsItems.get(position).getContent());
        holder.date.setText(newsItems.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        TextView date;


        public ViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.txt_title);
            content=(TextView)itemView.findViewById(R.id.txt_content);
            date=(TextView)itemView.findViewById(R.id.txt_date);

            try {
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final String title=newsItems.get(getLayoutPosition()).getTitle();
                        final String link=newsItems.get(getLayoutPosition()).getLink();

                        Intent intent=new Intent(context,NewsActivity.class);
                        intent.putExtra("title",title);
                        intent.putExtra("link",link);
                        context.startActivity(intent);


                    }
                });
            }catch (Exception e){

            }


        }
    }

}
