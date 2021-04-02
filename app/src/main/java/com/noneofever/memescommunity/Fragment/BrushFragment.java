package com.noneofever.memescommunity.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.noneofever.memescommunity.Adapter.ColorAdapter;
import com.noneofever.memescommunity.Interface.BrushFragmentListener;
import com.noneofever.memescommunity.R;

public class BrushFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdapterListener {

    SeekBar seekBar_brush_size,seekBar_opacity_size;
    RecyclerView recyclerView_color;
    ToggleButton btn_brush_state;
    ColorAdapter colorAdapter;
    BrushFragmentListener brushFragmentListener;

    static BrushFragment instance;

    public static BrushFragment getInstance(){
        if(instance == null){
            instance = new BrushFragment();
        }
        return instance;
    }

    public void setBrushFragmentListener(BrushFragmentListener brushFragmentListener) {
        this.brushFragmentListener = brushFragmentListener;
    }

    public BrushFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.BottomSheetDialogTheme);
        // Inflate the layout for this fragment
        View view = inflater.cloneInContext(contextThemeWrapper).inflate(R.layout.fragment_brush, container, false);

        seekBar_brush_size = view.findViewById(R.id.seekbar_brush_size);
        seekBar_opacity_size = view.findViewById(R.id.seekbar_brush_opacity);
        btn_brush_state = view.findViewById(R.id.btn_brush_state);
        recyclerView_color = view.findViewById(R.id.recycler_color);

        recyclerView_color.setHasFixedSize(true);
        recyclerView_color.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        colorAdapter = new ColorAdapter(getContext(),this);
        recyclerView_color.setAdapter(colorAdapter);


        seekBar_opacity_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushFragmentListener.onBrushOpacityChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_brush_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushFragmentListener.onBrushSizeChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_brush_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                brushFragmentListener.onBrushStateChangedListener(isChecked);
            }
        });

        return view;
    }


    @Override
    public void onColorSelected(int color) {
        brushFragmentListener.onBrushColorChangedListener(color);
    }
}