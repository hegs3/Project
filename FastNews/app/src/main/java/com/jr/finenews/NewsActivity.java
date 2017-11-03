package com.jr.finenews;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class NewsActivity extends AppCompatActivity {

    TextView content;
    TextView title2;
    ImageView imgNews;


    String imgLink="";
    String contents="";
    String link="";
    String title="";
    String mLink="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);




        Intent intent=getIntent();
        link=intent.getStringExtra("link");
        title=intent.getStringExtra("title");

        imgNews=(ImageView)findViewById(R.id.imgNews);
        content=(TextView)findViewById(R.id.content);
        title2=(TextView)findViewById(R.id.title1);




        new Thread(){
            @Override
            public void run() {
                try {
                    Document document= Jsoup.connect(link).get();
                    Elements elements=document.select("meta[property=og:image]");
                    imgLink= elements.attr("content");
                    contents=document.select("div[class=article_content]").select("div[itemprop=articleBody]").text()+"\n";
                    mLink="http://mnews.jtbc.joins.com/News/"+document.select("form[id=form1]").attr("action");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        title2.setText(title);
                        if(imgLink.equals("")){
                            imgNews.setVisibility(View.GONE);
                            content.setPadding(0,24,0,0);
                        }
                        Glide.with(NewsActivity.this).load(imgLink).into(imgNews);
                        content.setText(contents);
                    }
                });
                } catch (Exception e) {e.printStackTrace();}
            }
        }.start();
    }

    public void btnLnk(View v){
        Intent i=new Intent(Intent.ACTION_VIEW);
        Uri u= Uri.parse(mLink);
        i.setData(u);
        startActivity(i);
    }
}
