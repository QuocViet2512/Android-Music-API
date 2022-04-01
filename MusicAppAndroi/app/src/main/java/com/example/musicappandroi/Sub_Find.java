package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.Get_Infor_Artist_Model;
import com.example.lib.Models.TrackSongModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sub_Find#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sub_Find extends Fragment {

    public int Option; //  1 =>Search  //  2=>Genre  // 3=>Album   //  4=>Artist  //  5=>Playlist
    public List<TrackSongModel.TrackSong> LSong;

    public Context context;
    public View mView;
    public Sub_Find (int option, List<TrackSongModel.TrackSong>Songs){
        this.Option = option;
        this.LSong = Songs;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sub_Find() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sub_Find.
     */
    // TODO: Rename and change types and number of parameters
    public static Sub_Find newInstance(String param1, String param2) {
        Sub_Find fragment = new Sub_Find();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ////--------------------------//
    RecyclerView RC_FindListSong ;
    Button BackStack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.sub_find, container, false);
        RC_FindListSong = mView.findViewById(R.id.RC_FindListSong);
        RC_FindListSong.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RC_FindListSong.setAdapter(new Extend_Sub_Find_List_Song(getActivity(), LSong, new SendObj() {
            @Override
            public void Myobject(String idSong, ArrayList<TrackSongModel.TrackSong> ListSong) {
                ((MainActivity) getActivity()).Play_A_Song(idSong,ListSong);
            }
        }));
        return mView ;
    }


}