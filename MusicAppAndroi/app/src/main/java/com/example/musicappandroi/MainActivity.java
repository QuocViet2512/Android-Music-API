package com.example.musicappandroi;



import static com.example.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.TrackSongModel;
import com.example.lib.Models.UserApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    public static UserApp.User getUser() {
        return user;
    }

    public static   UserApp.User user;

    ImageButton Showmenu;
    public static int currentOption = 3;
    BottomNavigationView Nvarbar;
    SearchView Search_Box;
    private  List<TrackSongModel.TrackSong>  listsongs ;
    public static  Sub_Play subplay = new Sub_Play();
    public static Sub_Home subhome = new Sub_Home();
    public static  Sub_Category subCategory = new Sub_Category();
    public static  Sub_Playlist subplaylist = new Sub_Playlist();
    public static  Sub_Find sub_find ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Showmenu = findViewById(R.id.Menubar);
        Nvarbar = findViewById(R.id.Nvarbar);
        Search_Box = findViewById(R.id.Search_Box);
        if(getIntent()!=null){
            Intent intent = getIntent();
             Bundle bundle =  intent.getBundleExtra("Sendbundle");
             user = bundle==null? null:(UserApp.User) bundle.getSerializable("Account") ;
        }
        Nvarbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.MainCategory:
                        if(currentOption<1){
                            ChangeFragment(subCategory,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,null);
                        }
                        else if(currentOption>1){
                            ChangeFragment(subCategory,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,null);
                        }
                        currentOption=1;
                        return true;
                    case R.id.MainPlaying:
                        if(currentOption<2){
                            ChangeFragment(subplay,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,null);
                        }
                        else if(currentOption>2){
                            ChangeFragment(subplay,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,null);
                        }
                        currentOption=2;

                        return true;
                    case R.id.MainHome:
                        if(currentOption<3){
                            ChangeFragment(subhome,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,null);
                        }
                        else if(currentOption>3){
                            ChangeFragment(subhome,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,null);
                        }
                        currentOption=3;

                        return true;
                    case R.id.MainPlayList:

                        if(currentOption<4){
                            ChangeFragment(subplaylist,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,null);
                        }
                        else if(currentOption>4){
                            ChangeFragment(subplaylist,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,null);
                        }
                        currentOption=4;
                        return true;
                    case R.id.MainUser:
                        currentOption=5;
                        if(getUser() == null){
                            Intent intent = new Intent(MainActivity.this,Login.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intentI = new Intent(MainActivity.this,Infor_User_App.class);
                            startActivity(intentI);
                        }

                        return true;
                }
                return false;
            }
        });

        Showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });

        if (currentOption == 1) {
            ChangeFragment(subCategory, R.anim.switch_fragment_r_to_l, R.anim.exit_fragment_r_to_l, R.anim.switch_fragment_l_to_r, R.anim.exit_fragment_l_to_r,null);
        } else if (currentOption == 2) {
            ChangeFragment(subplay, R.anim.switch_fragment_r_to_l, R.anim.exit_fragment_r_to_l, R.anim.switch_fragment_l_to_r, R.anim.exit_fragment_l_to_r,null);
        } else if (currentOption == 3||currentOption==6) {
            ChangeFragment(subhome, R.anim.switch_fragment_r_to_l, R.anim.exit_fragment_r_to_l, R.anim.switch_fragment_l_to_r, R.anim.exit_fragment_l_to_r,null);
        } else if (currentOption == 4) {
            ChangeFragment(subplaylist, R.anim.switch_fragment_r_to_l, R.anim.exit_fragment_r_to_l, R.anim.switch_fragment_l_to_r, R.anim.exit_fragment_l_to_r,null);
        } else if (currentOption == 5) {
            if(getUser()== null){
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
            else {
                Intent intentI = new Intent(MainActivity.this,Infor_User_App.class);
                startActivity(intentI);
            }
        }
        GetTextQuery();
    }

    private void ChangeFragment(Fragment fragment , int a1, int a2 , int a3, int a4, Bundle param){
        FragmentManager fragManagera = getSupportFragmentManager();
        if(param!=null){
            fragment.setArguments(param);
        }
        fragManagera.beginTransaction().setCustomAnimations(a1,a2,a3,a4). replace(R.id.MainContent,fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    private void showmenu() {
        PopupMenu popupmenu = new PopupMenu(this, Showmenu);
        popupmenu.getMenuInflater().inflate(R.menu.menu_popup, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Account:
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupmenu.show();
    }

    public void Play_A_Song(String idSong, ArrayList<TrackSongModel.TrackSong> Lsong){
        Bundle bundle = new Bundle();
        bundle.putString("idSong",idSong);
        bundle.putSerializable("LSong", Lsong);

        if(currentOption<2){
            ChangeFragment(subplay,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,bundle);
        }
        else if(currentOption>2){
            ChangeFragment(subplay,R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,bundle);
        }
        currentOption=2;


    }

    public void GetTextQuery(){
        Search_Box.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.SearchTrackSong(query);
                call.enqueue(new Callback<TrackSongModel>() {
                    @Override
                    public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                        List<TrackSongModel.TrackSong> LSong = response.body().getData();
                        OpenDetail(1,LSong);
                    }

                    @Override
                    public void onFailure(Call<TrackSongModel> call, Throwable t) {

                    }
                });

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void OpenDetail(int Option,List<TrackSongModel.TrackSong> Lsong){
      currentOption = 6;
        ChangeFragment(sub_find = new Sub_Find(Option,Lsong),R.anim.switch_fragment_l_to_r,R.anim.exit_fragment_l_to_r,R.anim.switch_fragment_r_to_l,R.anim.exit_fragment_r_to_l,null);
    }

}