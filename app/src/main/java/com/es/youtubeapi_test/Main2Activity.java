package com.es.youtubeapi_test;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private Adapter adapter;
    private RecyclerView recyclerView;

    private ArrayList<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*String url = "i_yLpCLMaKk";
        Toast.makeText(this, "fragment 조각안에 재생", Toast.LENGTH_SHORT).show();
        ViewFragment f = ViewFragment.newInstance(url);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.view_container,f).commit();

*/
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);// 선형
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        urlList.add("i_yLpCLMaKk");
        urlList.add("UEGpGjF7GbA");
        adapter = new Adapter(this,this);
        adapter.addAll(urlList);
        recyclerView.setAdapter(adapter);



        //in fragment
//        ViewFragment f = ViewFragment.newInstance(url);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction(); //getChildFragmentMagamer()
//        transaction.add(R.id.view_container,f).commit();
    }
}
