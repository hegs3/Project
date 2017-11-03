package com.jr.finenews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by alfo6-6 on 2017-08-24.
 */

public class CustomFragment extends Fragment  {
    YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    String itemId="";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public YouTubePlayerSupportFragment getYoutubeFragment(){
        return youTubePlayerSupportFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_onair,container,false);

        FragmentManager fm=getActivity().getSupportFragmentManager();

        FragmentTransaction fmt=fm.beginTransaction();
        youTubePlayerSupportFragment=new YouTubePlayerSupportFragment();

        fmt.add(R.id.test1234,youTubePlayerSupportFragment).commit();
        return view;
    }



}
