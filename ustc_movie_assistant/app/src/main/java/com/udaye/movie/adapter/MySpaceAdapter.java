package com.udaye.movie.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.udaye.movie.R;
import com.udaye.movie.entity.MySpaceBean;
import com.udaye.movie.ui.main.MovieDetailActivity;
import com.udaye.library.pullloadlibrary.CommonViewHolder;
import com.udaye.library.pullloadlibrary.RecyclerViewCommonAdapter;

/**
 * 用户界面
 */
public class MySpaceAdapter extends RecyclerViewCommonAdapter<MySpaceBean.SubjectsBean> {
    /**
     * @param mContext 上下文对象
     * @param list     数据List
     */
    public MySpaceAdapter(Context mContext, List<MySpaceBean.SubjectsBean> list) {
        super(mContext, list, R.layout.view_top_250);
    }

    public void update(List<MySpaceBean.SubjectsBean> list) {
        addList((ArrayList<MySpaceBean.SubjectsBean>) list);
    }

    @Override
    public void onListBindViewHolder(CommonViewHolder holder, int position) {
        final MySpaceBean.SubjectsBean subjectsBean = getItem(position);
        holder.setText(R.id.tv_movie_name, subjectsBean.getTitle());
        holder.setText(R.id.tv_movie_score, String.format(mContext.getString(R.string.tip_score), String.valueOf(subjectsBean.getRating().getAverage())));
        holder.setText(R.id.tv_movie_date, String.format(mContext.getString(R.string.tip_movie_date), subjectsBean.getYear()));
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_movie_icon);
        Glide.with(mContext)
                .load(mList.get(position).getImages().getLarge())
                .placeholder(android.R.color.white)
                .into(imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(MovieDetailActivity.getCallingIntent(mContext, subjectsBean.getId()));
            }
        });

    }
}
