package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDemo extends Fragment{

    @Override
       public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
             }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onPause() {
                super.onPause();
           }

}
