package com.jr.finenews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;

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

public class Tab1Fragment extends Fragment {

    ArrayList<TabArraylist> newItems=new ArrayList<>();
    RecyclerView recyclerView;
    TabRecyclerviewAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Tab1Asynctask tab1Asynctask;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_tab, container, false);

        readTab1Asynctask();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                if (tab1Asynctask == null) {
                    newItems.clear();
                    readTab1Asynctask();
                    adapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter = new TabRecyclerviewAdapter(newItems, getContext());
        recyclerView.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayout.VERTICAL, false);
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




    void readTab1Asynctask(){
        try {
            URL url=new URL("http://fs.jtbc.joins.com/RSS/newsflash.xml");
            if(tab1Asynctask==null) {
                tab1Asynctask = new Tab1Asynctask();
                tab1Asynctask.execute(url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
//    RecyclerView.OnScrollListener scrollListener=new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//            super.onScrollStateChanged(recyclerView, newState);
//        }
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            recyclerView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                     int down=0;
//                     int up=0;
//                    switch (event.getAction()){
//                        case MotionEvent.ACTION_DOWN:
//                            down=(int)event.getY();
//                            break;
//                        case MotionEvent.ACTION_UP:
//                            up=(int)event.getY();
//                            break;
//                        default:return false;
//                    }
//                    if(down>up) Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();//보이기
//                    else Toast.makeText(getContext(), "no", Toast.LENGTH_SHORT).show();//안보이기
//
//                    return true;
//                }
//            });
//
//        }
//    };

    class Tab1Asynctask extends AsyncTask<URL,Void,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            newItems.clear();
            URL url=params[0];
            try {
                XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
                XmlPullParser xpp=xmlPullParserFactory.newPullParser();

                InputStream is=url.openStream();
                xpp.setInput(is,"UTF-8");

                String tag="";
                TabArraylist item=null;
                int eventType=xpp.next();

                while (eventType!=XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            tag=xpp.getName();
                            if(tag.equals("item")){
                                item=new TabArraylist();
                            }else if(tag.equals("title")){
                                xpp.next();
                                if(item!=null)item.setTitle(xpp.getText());
                            }else if(tag.equals("link")){
                                xpp.next();
                                if(item!=null)item.setLink(xpp.getText());
                            }else if(tag.equals("description")){
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
                                newItems.add(item);
                                item=null;
                                publishProgress();}
                            break;
                    }
                    eventType=xpp.next();
                }

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            return "end Parsing";
        }

                //데이터 변경
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                adapter.notifyDataSetChanged();

        }

        //완료되면 뿌려주는놈...
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            tab1Asynctask=null;
        }
    }
    public void home(){
        newItems.clear();
        readTab1Asynctask();
        adapter.notifyDataSetChanged();
        //recyclerView.scrollToPosition(0);
    }



}
