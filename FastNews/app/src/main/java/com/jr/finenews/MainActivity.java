package com.jr.finenews;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int ONAIR_VIDEO=0;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ArrayList<NaviItem> items=new ArrayList<>();
    NaviAdapter adapter;
    ListView navi;

    MainFragmentPagerAdapter mainFragmentPagerAdapter;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;
    ImageView imgToolbar;
    TextView onair_time;

    String itemId="";
    String time="";


    MenuItem menuItem;

    CollapsingToolbarLayout collap;
    AppBarLayout appbar;
    View empty;
    CoordinatorLayout coordinatorLayout;

    DrawerLayoutEdgeToggle mDrawerToggle;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        //Status bar 투명(완) 만들기
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        onair_time=(TextView)findViewById(R.id.onair_time);
        drawerLayout=(DrawerLayout)findViewById(R.id.layout_drawer);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        collap= (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        appbar=(AppBarLayout)findViewById(R.id.layout_appbar);
        empty=(View)findViewById(R.id.empty);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.layout_coordinatorLayout);




        int out=R.drawable.back;
        int in=R.drawable.next;

        mDrawerToggle = new DrawerLayoutEdgeToggle(this,drawerLayout,out,in,true, Gravity.START){

            @Override
            public void onDrawerClosed(View arg0) {
                super.onDrawerClosed(arg0);
            }

            @Override
            public void onDrawerOpened(View arg0) {
                super.onDrawerOpened(arg0);
            }

            @Override
            public void onDrawerSlide(View arg0, float slideOffset) {
                super.onDrawerSlide(arg0, slideOffset);
            }
        };

        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setVerticalTopOffset(50);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                View v=mDrawerToggle.getDrawerLayoutHandle();
                if (verticalOffset <= -(appbar.getHeight()+empty.getHeight()-8) +toolbar.getHeight()) {
                    v.setVisibility(View.VISIBLE);
                }else {
                    v.setVisibility(View.INVISIBLE);
                }

            }
        });


        tabLayout=(TabLayout)findViewById(R.id.layout_tab);
        pager=(ViewPager)findViewById(R.id.pager);
        imgToolbar=(ImageView)findViewById(R.id.img_toolbar);

        items.add(new NaviItem(R.drawable.menu_link,"Jtbc Link"));
        items.add(new NaviItem(android.R.drawable.presence_video_online,"Jtbc OnAir"));

        navi=(ListView)findViewById(R.id.navi);
        adapter=new NaviAdapter(items,this);
        View headAfoot=getLayoutInflater().inflate(R.layout.navi_head,navi,false);
        navi.addHeaderView(headAfoot);
        navi.setAdapter(adapter);
        headAfoot=getLayoutInflater().inflate(R.layout.navi_foot,navi,false);
        navi.addFooterView(headAfoot);
        navi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        drawerLayout.closeDrawers();
                        break;
                    case 1:
                        Intent i=new Intent(Intent.ACTION_VIEW);
                        Uri u= Uri.parse("http://mnews.jtbc.joins.com");
                        i.setData(u);
                        startActivity(i);
                        break;
                    case 2:
                        if(!itemId.equals("")){
                            yf.initialize(DeveloperKey.getDeveloperKey(), new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                                    youTubePlayer.cueVideo(itemId);
                                    youTubePlayer.setFullscreen(true);
                                }

                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });
                            layout.onAirCreate();
                        }
                        switch (ONAIR_VIDEO){
                            case 0:
                                Toast.makeText(MainActivity.this, "Error. \n앱을 재시작 해주십시오.", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this, "OnAir정보를 가져오고 있습니다. \n잠시후 다시 시도하십시오.", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "OnAir정보를 가져오는데 실패했습니다. \nOnAir시간 및 네트워크를 확인하십시오.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case 3:
                        drawerLayout.closeDrawers();
                        break;
                }
            }
        });

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        Glide.with(MainActivity.this).load(R.drawable.bgbk).into(imgToolbar);

        mainFragmentPagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager());

        tabLayout.setupWithViewPager(pager,true);
        pager.setAdapter(mainFragmentPagerAdapter);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect("https://www.youtube.com/user/JTBC10news").get();
                    Elements elements = doc.select("li[class=yt-lockup clearfix featured-content-item yt-uix-slider-item yt-lockup-video yt-lockup-tile fluid vve-check]");
                    itemId = elements.attr("data-context-item-id");
                    doc=Jsoup.connect("http://www.youtube.com/watch?v="+itemId).get();
                    elements=doc.select("strong[class=watch-time-text metadata-updateable-date-text]");
                    time=elements.text();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Thread.currentThread();
                            if(itemId=="") {
                                menuItem.setIcon(android.R.drawable.presence_video_busy);
                                Toast.makeText(MainActivity.this, "OnAir Off", Toast.LENGTH_SHORT).show();
                                ONAIR_VIDEO=2;
                            }
                            else {
                                onair_time.setText(time);
                                menuItem.setIcon(android.R.drawable.presence_video_online);
                                Toast.makeText(MainActivity.this, "OnAir On", Toast.LENGTH_SHORT).show();
                                ONAIR_VIDEO=4;

                            }

                        }
                    });
                }catch (IOException e) {e.printStackTrace();}
            }
        }.start();



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:Glide.with(MainActivity.this).load(R.drawable.bgbk).into(imgToolbar);
                        break;
                    case 1:Glide.with(MainActivity.this).load(R.drawable.bgwh).into(imgToolbar);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });




        width=((WindowManager) this.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        height=((WindowManager) this.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getHeight();

        layout= (CustomLayout) findViewById(R.id.layout);
        fm=getSupportFragmentManager();
        cf= (CustomFragment) fm.findFragmentById(R.id.youtube);
        yf=cf.getYoutubeFragment();
        player= (RelativeLayout) findViewById(R.id.test1234);
        fragment2Bottom=(View)findViewById(R.id.fragback);
        layout.getData(width,height,fragment2Bottom,yf,fm);





    }
    public void clickFabHome(View v){
        pager.setCurrentItem(0,true);
        ((Tab1Fragment)mainFragmentPagerAdapter.fragments[0]).home();
        ((Tab2Fragment)mainFragmentPagerAdapter.fragments[1]).home();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option,menu);
        menuItem=menu.findItem(R.id.menu_live);
        if(itemId=="") {
            menuItem.setIcon(android.R.drawable.presence_video_busy);
            ONAIR_VIDEO = 1;
        }
        else {
            menuItem.setIcon(android.R.drawable.presence_video_online);
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_link:
                Intent i=new Intent(Intent.ACTION_VIEW);
                Uri u= Uri.parse("http://mnews.jtbc.joins.com");
                i.setData(u);
                startActivity(i);
                break;
            case R.id.menu_live:
                if(!itemId.equals("")){
                    yf.initialize(DeveloperKey.getDeveloperKey(), new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.cueVideo(itemId);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                    layout.onAirCreate();
                }
                switch (ONAIR_VIDEO){
                    case 0:
                        Toast.makeText(this, "Error. \n앱을 재시작 해주십시오.", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(this, "OnAir정보를 가져오고 있습니다. \n잠시후 다시 시도하십시오.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "OnAir정보를 가져오는데 실패했습니다. \nOnAir시간 및 네트워크를 확인하십시오.", Toast.LENGTH_SHORT).show();
                        break;
                }
        }
        return super.onOptionsItemSelected(item);

    }
    int width;
    int height;//디스플레이 크기
    YouTubePlayerSupportFragment yf;
    FragmentManager fm;
    CustomFragment cf;
    CustomLayout layout;
    View fragment2Bottom;
    RelativeLayout player;


    @Override
    public void onBackPressed() {
        if(layout.isShown())layout.onAirDelete();
        else this.finish();
//        super.onBackPressed();



    }


}
