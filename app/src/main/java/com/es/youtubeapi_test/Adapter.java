package com.es.youtubeapi_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private Context context;
    private ArrayList<String> urlList = new ArrayList<>();
    private Main2Activity mActivity;

    Adapter(Context context, Main2Activity mActivity) {
        this.context = context;
        this.mActivity = mActivity;
    }
    void addAll(ArrayList<String> urlList){
        this.urlList = urlList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false),context);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder cholder = (ItemViewHolder)holder;
        final String url = urlList.get(position);


        cholder.youTubeThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(url);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
//                        youTubeThumbnailView.setVisibility(View.VISIBLE);
//                        cholder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()){
                    youTubeInitializationResult.getErrorDialog(mActivity,RECOVERY_DIALOG_REQUEST);
                }else{
                    String errorMessage = String.format(context.getString(R.string.error_player),youTubeInitializationResult.toString());
                    Toast.makeText(context, ""+errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });




//        cholder.frameLayout
//        Toast.makeText(context, "fragment 조각안에 재생", Toast.LENGTH_SHORT).show();
//        ViewFragment f = ViewFragment.newInstance(url);
//        FragmentManager manager = mActivity.getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.view_container,f).commit();
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        FrameLayout frameLayout;
        protected FrameLayout relativeLayoutOverYouTubeThumbnailView;
        protected ImageView playButton;
        Context context;

        YouTubeThumbnailView youTubeThumbnailView;
        ItemViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            youTubeThumbnailView = (YouTubeThumbnailView)itemView.findViewById(R.id.view_container);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (FrameLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        }

        @Override
        public void onClick(View view) {
//            ViewFragment f = ViewFragment.newInstance(urlList.get(getAdapterPosition()));
//            FragmentManager manager = mActivity.getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativeLayout_over_youtube_thumbnail,f).commit();
            Intent intent = YouTubeStandalonePlayer.createVideoIntent(mActivity, urlList.get(getAdapterPosition()), urlList.get(getAdapterPosition()));
            mActivity.startActivity(intent);
        }
    }
}
