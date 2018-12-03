package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class TradeFragment extends Fragment {
    private FloatingActionButton fabTrade, fabCreateTrade;
    private Animation FabOpen, FabClose, FabRotationClockwise, FabRotationAntiClockwise;
    private LinearLayout fab_box;
    private boolean Fab_isOpen;
    private RecyclerView rv_trade;

    public static final String TAG = "tradeFragment";
    public TradeFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.trade_fragment, container, false);
        fabTrade = (FloatingActionButton) rootView.findViewById(R.id.fab_trade);
        fabCreateTrade = (FloatingActionButton) rootView.findViewById(R.id.fab_createTrade);
        fab_box = (LinearLayout) rootView.findViewById(R.id.fab_box);
        
        rv_trade = rootView.findViewById(R.id.rv_trade);
        rv_trade.setHasFixedSize(true);
        rv_trade.setItemViewCacheSize(20);
        rv_trade.setDrawingCacheEnabled(true);
        rv_trade.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rv_trade.setLayoutManager(new LinearLayoutManager(getContext()));

        FabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        FabRotationClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        FabRotationAntiClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);

        fabTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( Fab_isOpen ) {
                    fab_box.startAnimation(FabClose);
                    fabCreateTrade.startAnimation(FabClose);
                    fabTrade.startAnimation(FabRotationAntiClockwise);
                    fab_box.setVisibility(View.INVISIBLE);
                    fabCreateTrade.setClickable(false);
                    Fab_isOpen = false;
                }
                else {
                    fab_box.startAnimation(FabOpen);
                    fabCreateTrade.startAnimation(FabOpen);
                    fabTrade.startAnimation(FabRotationClockwise);
                    fabCreateTrade.setClickable(true);
                    fab_box.setVisibility(View.VISIBLE);
                    Fab_isOpen = true;
                }
            }
        });

        fabCreateTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_box.startAnimation(FabClose);
                fabCreateTrade.startAnimation(FabClose);
                fabTrade.startAnimation(FabRotationAntiClockwise);
                fab_box.setVisibility(View.INVISIBLE);
                fabCreateTrade.setClickable(false);
                Fab_isOpen = false;
                EventFragment.toggleVisiblity();
            }
        });
        return rootView;
    }
}
