package com.noneofever.memescommunity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.noneofever.memescommunity.Adapter.EmojiAdapter;
import com.noneofever.memescommunity.Interface.EmojiFragmentListener;
import com.noneofever.memescommunity.R;

import com.noneofever.memescommunity.photoeditor.PhotoEditor;

public class EmojiFragment extends BottomSheetDialogFragment implements EmojiAdapter.EmojiAdapterListener {

    RecyclerView recyclerView_emoji;
    EmojiFragmentListener listener;

    public void setListener(EmojiFragmentListener listener) {
        this.listener = listener;
    }

    static EmojiFragment instance;

    public static EmojiFragment getInstance(){
        if(instance == null){
            instance = new EmojiFragment();
        }
        return instance;
    }



    public EmojiFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_emoji, container, false);

        recyclerView_emoji = view.findViewById(R.id.recycler_emoji);
        recyclerView_emoji.setHasFixedSize(true);
        recyclerView_emoji.setLayoutManager(new GridLayoutManager(getActivity(),5));

        EmojiAdapter emojiAdapter = new EmojiAdapter(getContext(), PhotoEditor.getEmojis(getContext()),this);
        recyclerView_emoji.setAdapter(emojiAdapter);


        return  view;
    }

    @Override
    public void onEmojiItemSelected(String emoji) {
        listener.onEmojiSelected(emoji);
    }
}