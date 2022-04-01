package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.AlbumModel;
import com.example.lib.Models.TrackSongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Extend_Sub_Cate_List_Album#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Extend_Sub_Cate_List_Album extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Extend_Sub_Cate_List_Album() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Extend_Sub_Cate_List_Album.
     */
    // TODO: Rename and change types and number of parameters
    public static Extend_Sub_Cate_List_Album newInstance(String param1, String param2) {
        Extend_Sub_Cate_List_Album fragment = new Extend_Sub_Cate_List_Album();
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
//Khai bao
    RecyclerView RC_ListAlbum_Cate;
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.extend_sub_cate_list_album, container, false);
        RC_ListAlbum_Cate= mView.findViewById(R.id.RC_ListAlbum_Cate);
        RC_ListAlbum_Cate.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        GetData_Album();
        return mView;
    }
    public void GetData_Album(){
        Methods methods =getRetrofit().create(Methods.class);
        Call<AlbumModel> call = methods.getAlbum();
        call.enqueue(new Callback<AlbumModel>() {
            @Override
            public void onResponse(Call<AlbumModel> call, Response<AlbumModel> response) {
                if(response.isSuccessful()){
                    RC_ListAlbum_Cate.setAdapter(new List_Album_Cate_Item(response.body().getData(), new DetailListSong() {
                        @Override
                        public void DetailCate(int Option, List<TrackSongModel.TrackSong> Songs) {
                            ((MainActivity)getActivity()).OpenDetail(Option,Songs);
                        }
                    }));

                }
            }
            @Override
            public void onFailure(Call<AlbumModel> call, Throwable t) {
            }
        });
    }
}