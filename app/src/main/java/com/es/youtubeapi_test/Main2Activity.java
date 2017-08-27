package com.es.youtubeapi_test;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String url = "i_yLpCLMaKk";
        Toast.makeText(this, "fragment 조각안에 재생", Toast.LENGTH_SHORT).show();
        ViewFragment f = ViewFragment.newInstance(url);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.view_container,f).commit();

        //in fragment
//        ViewFragment f = ViewFragment.newInstance(url);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction(); //getChildFragmentMagamer()
//        transaction.add(R.id.view_container,f).commit();
    }
}
