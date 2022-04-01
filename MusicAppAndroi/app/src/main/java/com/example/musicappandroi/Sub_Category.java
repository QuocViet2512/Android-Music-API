package com.example.musicappandroi;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Sub_Category extends Fragment {



    public static   Extend_Sub_Cate_List_Song Sub_Cate_Song;
    public static  Extend_Sub_Cate_List_Artist Sub_Cate_Artist;
    public static Extend_Sub_Cate_List_Album Sub_Cate_Album;
    BottomNavigationView Top_Cate_Nvarbar;
    LinearLayout Cate_Content;
    View SubCateView;
    public static int currentSub_Fragment = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         SubCateView = inflater.inflate(R.layout.sub_category, container, false);
        Cate_Content = SubCateView.findViewById(R.id.Cate_Content);
        Top_Cate_Nvarbar = SubCateView.findViewById(R.id.Top_Cate_Nvarbar);
        Top_Cate_Nvarbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Top_Cate_Album:
                        ChangeFragment(new Extend_Sub_Cate_List_Album());
                        currentSub_Fragment = 1;
                        return true;
                    case R.id.Top_Cate_Artist:
                        ChangeFragment(new Extend_Sub_Cate_List_Artist());
                        currentSub_Fragment = 2;
                        return true;
                    case R.id.Top_Cate_Songs:
                        ChangeFragment(new Extend_Sub_Cate_List_Song());
                        currentSub_Fragment = 3;
                        return true;
                }
                return false;
            }
        });

        switch (currentSub_Fragment){
            case 1:
                ChangeFragment(new Extend_Sub_Cate_List_Album());
                break;
            case 2:
                ChangeFragment(new Extend_Sub_Cate_List_Artist());
                break;
            case 3:
                ChangeFragment(new Extend_Sub_Cate_List_Song());
                break;
        }
        return SubCateView;
    }
    private void ChangeFragment(Fragment fragment ){
        FragmentManager fragManagera = getParentFragmentManager();
        fragManagera.beginTransaction(). replace(R.id.  Cate_Content,fragment).addToBackStack(fragment.getClass().getName()).commit();
    }
}