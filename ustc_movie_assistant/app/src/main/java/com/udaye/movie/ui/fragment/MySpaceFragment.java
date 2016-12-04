package com.udaye.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.udaye.movie.R;
import com.udaye.movie.adapter.MySpaceAdapter;
import com.udaye.movie.entity.MySpaceBean;
import com.udaye.movie.util.RecyclerViewUtil.GridMarginDecoration;
import com.udaye.library.pullloadlibrary.LinearRecyclerView;
import com.udaye.library.pullloadlibrary.OnLoadMoreListener;
import com.udaye.library.pullloadlibrary.SuperRecyclerView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * myspace fragement
 *
 */
public class MySpaceFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    LinearRecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MySpaceAdapter mySpaceAdapter;

    List<MySpaceBean.SubjectsBean> mList;
    MySpaceBean mMySpaceBean;

    public static MySpaceFragment newInstance() {

        Bundle args = new Bundle();

        MySpaceFragment fragment = new MySpaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top250, null);
        recyclerView = (LinearRecyclerView) view.findViewById(R.id.rv_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(SuperRecyclerView recyclerView) {
                if (mMySpaceBean != null && mMySpaceBean.isHasNext()) {
                    recyclerView.showFootProgress();
                    requestData(mMySpaceBean.getNextIndex(), 20);
                } else {
                    recyclerView.showFootProgressEnd();
                }
            }
        });
        setPreLoadData();
    }


    @Override
    public void onRefresh() {
        if (mySpaceAdapter != null) {
            mySpaceAdapter.clear();
        }
        requestData(0, 20);
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }


    private void requestData(int start, int count) {
        mRepository.getMySpaceMovie(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MySpaceBean>() {
                    @Override
                    public void onCompleted() {
                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onNext(MySpaceBean mySpaceBean) {
                        if (mySpaceBean != null) {
                            recyclerView.showFootProgressEnd();

                            mMySpaceBean = mySpaceBean;
                            mList = mySpaceBean.getSubjects();
                            if (mySpaceAdapter == null) {
                                mySpaceAdapter = new MySpaceAdapter(getContext(), mList);
                                recyclerView.setAdapter(mySpaceAdapter);
                            } else {
                                mySpaceAdapter.update((ArrayList<MySpaceBean.SubjectsBean>) mList);
                            }
                        }
                    }
                });
    }

}

