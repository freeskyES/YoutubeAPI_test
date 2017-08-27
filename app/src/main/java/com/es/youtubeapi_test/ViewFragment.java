package com.es.youtubeapi_test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


public class ViewFragment extends Fragment {
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private static final String URL = "url";
    String getUrl;

//    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String url) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        fragment.setArguments(args);
//        fragment.init();
        return fragment;
    }
//    private void init(){
//        initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b){
//                    youTubePlayer.cueVideo(getUrl);//"i_yLpCLMaKk"
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                if (youTubeInitializationResult.isUserRecoverableError()){
//                    youTubeInitializationResult.getErrorDialog(getActivity(),RECOVERY_DIALOG_REQUEST);
//                }else{
//                    String errorMessage = String.format(getString(R.string.error_player),youTubeInitializationResult.toString());
//                    Toast.makeText(getContext(), ""+errorMessage, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getUrl = getArguments().getString(URL);
        }

//        Log.d("youtube Test",
//                "사용가능여부:"+ YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity())); //SUCCSESS

//        getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        getChildFragmentManager().beginTransaction().add(R.id.youtube_layout,youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b){
                    youTubePlayer.cueVideo(getUrl);//"i_yLpCLMaKk"
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()){
                    youTubeInitializationResult.getErrorDialog(getActivity(),RECOVERY_DIALOG_REQUEST);
                }else{
                    String errorMessage = String.format(getString(R.string.error_player),youTubeInitializationResult.toString());
                    Toast.makeText(getContext(), ""+errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView) getActivity().findViewById(R.id.youtube_view);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RECOVERY_DIALOG_REQUEST){
//            // Retry initialization if user performed a recovery action
//            getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY,this);

//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
