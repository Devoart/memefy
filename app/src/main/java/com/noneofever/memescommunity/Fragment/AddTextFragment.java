package com.noneofever.memescommunity.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noneofever.memescommunity.Adapter.ColorAdapter;
import com.noneofever.memescommunity.Adapter.FontAdapter;
import com.noneofever.memescommunity.Interface.AddTextFragmentListener;
import com.noneofever.memescommunity.R;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddTextFragment extends BottomSheetDialogFragment implements FontAdapter.FontAdapterClickListener, ColorAdapter.ColorAdapterListener {


    int colorSelected = Color.parseColor("#000000");

    AddTextFragmentListener listener;

    EditText edit_add_text;
    RecyclerView recycler_color,recycler_font;
    Button button_done;

    Typeface typefaceSelected = Typeface.DEFAULT;

    public void setListener(AddTextFragmentListener listener) {
        this.listener = listener;
    }

    static AddTextFragment instance;

    public static AddTextFragment getInstance(){
        if(instance == null){
            instance = new AddTextFragment();
        }
        return instance;
    }

    public AddTextFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_add_text, container, false);

        edit_add_text = view.findViewById(R.id.edit_add_text);
        button_done = view.findViewById(R.id.btn_done);
        recycler_color = view.findViewById(R.id.recycler_color_edit);
        recycler_font = view.findViewById(R.id.recycler_font);

        recycler_color.setHasFixedSize(true);
        recycler_color.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        ColorAdapter colorAdapter = new ColorAdapter(getContext(),this);
        recycler_color.setAdapter(colorAdapter);

        recycler_font.setHasFixedSize(true);
        recycler_font.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        FontAdapter fontAdapter = new FontAdapter(getContext(),this);
        recycler_font.setAdapter(fontAdapter);

        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit_add_text.getText().toString().isEmpty()) {
                    listener.onAddTextButtonClick(typefaceSelected, edit_add_text.getText().toString(), colorSelected);
                    edit_add_text.setText("");
                }else if(edit_add_text.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please write a text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onColorSelected(int color) {
        colorSelected = color;
    }

    @Override
    public void onFontSelected(String fontName) {
        typefaceSelected = Typeface.createFromAsset(getContext().getAssets(),new StringBuilder("fonts/")
                .append(fontName).toString());
    }
}