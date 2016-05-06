package com.example.qkx.demomvp.welfaredetail;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qkx.demomvp.R;
import com.example.qkx.demomvp.data.Welfare;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 16/4/28.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static String TAG = MyRecyclerAdapter.class.getSimpleName();

    private Context mContext;

    private List<Welfare.Result> mData;

    private LayoutInflater mInflater;

    public MyRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = new ArrayList<>();
    }

    public MyRecyclerAdapter(Context mContext, List<Welfare.Result> mData) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * !!!!!!!!!!!
         * @attachToRoot ----> false
         */
        View view = mInflater.inflate(R.layout.welfare_frag_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Welfare.Result r = mData.get(position);
        Picasso.with(mContext)
                .load(r.url)
                .into(viewHolder.ivPic);
        viewHolder.tvPicId.setText("" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Welfare.Result> data) {
        this.mData = data;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvPicId;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tvPicId = (TextView) itemView.findViewById(R.id.tv_pic_id);
        }
    }
}
