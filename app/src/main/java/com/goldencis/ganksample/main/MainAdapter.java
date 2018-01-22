package com.goldencis.ganksample.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.goldencis.ganksample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2018-1-22.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static final String TAG = "MainAdapter";

    private Context mContext;
    private List<WealEntity.ResultsBean> mData;

    public MainAdapter(Context context, List<WealEntity.ResultsBean> data) {
        this.mContext = context;
        setData(data);
    }

    public MainAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_weal, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WealEntity.ResultsBean bean = mData.get(position);
        String url = bean.getUrl();
        Log.d(TAG, "onBindViewHolder: -->" + url);
        Glide.with(mContext).load(url).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<WealEntity.ResultsBean> data) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

}
