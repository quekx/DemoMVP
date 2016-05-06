package com.example.qkx.demomvp.welfaredetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qkx.demomvp.R;
import com.example.qkx.demomvp.data.Welfare;

import java.util.List;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareDetailFragment extends Fragment implements WelfareDetailContact.View {

    private static final String ARGUMENT_DATA = "DATA";

    private RecyclerView recyclerView;

    private MyRecyclerAdapter mAdapter;

    private List<String> mData;

    private WelfareDetailContact.Presenter mPresenter;

    public WelfareDetailFragment() {
    }

    public static WelfareDetailFragment newInstance() {
        Bundle args = new Bundle();
        WelfareDetailFragment fragment = new WelfareDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showPic(Welfare data) {
        mAdapter.setData(data.results);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(WelfareDetailContact.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welfare_frag, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);

        mAdapter = new MyRecyclerAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void refreshRandom() {
        mPresenter.refreshRandom();
    }
}
