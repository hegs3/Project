package com.jr.finenews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alfo6-6 on 2017-06-15.
 */

public class Tab2Fragment extends Fragment {

    ArrayList<TabArraylist> new2Items=new ArrayList<>();
    RecyclerView recyclerView;
    TabRecyclerviewAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    CardView cardView;
    Tab2Asynctask tab2Asynctask;

    int dy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recycler_tab,container,false);


        readTab2Asynctask();
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.layout_swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (tab2Asynctask == null) {
                    new2Items.clear();
                    readTab2Asynctask();
                    adapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        adapter=new TabRecyclerviewAdapter(new2Items,getContext());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int down;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        down=(int)e.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        int up=(int)e.getY();

                        if(down-up>200) getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        else if(down-up<-200) getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        return view;
    }






    void readTab2Asynctask(){
        try {
            URL url=new URL("http://fs.jtbc.joins.com/RSS/newsroom.xml");
            Tab2Asynctask tab2Asynctask=new Tab2Asynctask();
            tab2Asynctask.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    class Tab2Asynctask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {


            URL url=params[0];
            try {
                XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
                XmlPullParser xpp=xmlPullParserFactory.newPullParser();


                InputStream is=url.openStream();
                xpp.setInput(is,"UTF-8");

                String tag = "";
                TabArraylist item=null;
                int eventType=xpp.next();



                while(eventType!=XmlPullParser.END_DOCUMENT){

                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            tag=xpp.getName();
                            if(tag.equals("item")) {
                                item = new TabArraylist();
                            }else if(tag.equals("title")){
                                xpp.next();
                                if(item!=null)item.setTitle(xpp.getText());}
                            else if(tag.equals("link")){
                                xpp.next();
                                if(item!=null)item.setLink(xpp.getText());
                            } else if(tag.equals("description")){
                                xpp.next();
                                if(item!=null)item.setContent(xpp.getText());
                            }else if(tag.equals("pubDate")){
                                xpp.next();
                                if(item!=null)item.setDate(xpp.getText());
                            }
                            break;


                        case XmlPullParser.END_TAG:
                            tag=xpp.getName();
                            if(tag.equals("item")){
                                new2Items.add(item);
                                item=null;

                                publishProgress();
                            }
                            break;

                    }
                    eventType=xpp.next();
                }



            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return "9ood";
        }


        //업데이트 메소드
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();
        }

        //두인 백그라운드가 종료되면 실행되는 메소드
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    public void home(){
        new2Items.clear();
        readTab2Asynctask();
        adapter.notifyDataSetChanged();
        //recyclerView.scrollToPosition(0);
    }

}
