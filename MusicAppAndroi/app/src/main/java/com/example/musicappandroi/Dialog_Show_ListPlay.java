package com.example.musicappandroi;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.TrackSongModel;

import java.util.List;

public class Dialog_Show_ListPlay extends Dialog {
    List<TrackSongModel.TrackSong> LSong;

    RecyclerView D_Show_ListPlay;
    public Dialog_Show_ListPlay(@NonNull Context context) {
        super(context);
        D_Show_ListPlay = findViewById(R.id.D_Show_ListPlay);
        D_Show_ListPlay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    public Dialog_Show_ListPlay(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected Dialog_Show_ListPlay(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
