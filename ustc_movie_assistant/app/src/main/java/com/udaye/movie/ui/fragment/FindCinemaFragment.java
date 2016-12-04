package com.udaye.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;


import java.util.List;

import com.udaye.movie.R;
import com.udaye.movie.adapter.FindCinemaAdapter;
import com.udaye.movie.entity.CommonBean;
import com.udaye.movie.util.RecyclerViewUtil.GridMarginDecoration;
import com.udaye.library.pullloadlibrary.GridRecyclerView;
import com.udaye.library.pullloadlibrary.OnLoadMoreListener;
import com.udaye.library.pullloadlibrary.SuperRecyclerView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 即将上映
 */
public class FindCinemaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    GridRecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    FindCinemaAdapter mFindCinemaAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<CommonBean.SubjectsBean> mSubjectsEntities;
    CommonBean mCommonBean;

    public static FindCinemaFragment newInstance() {
        Bundle args = new Bundle();
        FindCinemaFragment fragment = new FindCinemaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //@Nullable
   // @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_commingsoon, null);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh_layout);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mRecyclerView = (GridRecyclerView) view.findViewById(R.id.recyclerview);
//        return view;
//    }

   // @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
//        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                switch (position % 4) {
//                    case 0:
//                        return 3;
//                    default:
//                        return 1;
//                }
//            }
//        });
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
//        mRecyclerView.addItemDecoration(new GridMarginDecoration(
//                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(SuperRecyclerView recyclerView) {
//                if (mCommonBean != null) {
//                    if (mCommonBean.isHasNext()) {
//                        recyclerView.showFootProgress();
//                        requestDefaultData(mCommonBean.getNextIndex());
//                    } else {
//                        recyclerView.showFootProgressEnd();
//                    }
//                }
//            }
//        });
//        setPreLoadData();
//    }

    @Override
    public void onRefresh() {
        if (mFindCinemaAdapter != null) {
            mFindCinemaAdapter.clear();
        }
        //requestDefaultData(0);
    }

//    public void setPreLoadData() {
//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(true);
//                onRefresh();
//            }
//        });
//    }

//    private void requestDefaultData(int pageIndex) {
//        mRepository.getCommingSoonMovie(pageIndex, 20)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonBean>() {
//                    @Override
//                    public void onCompleted() {
//                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
//                            mSwipeRefreshLayout.setRefreshing(false);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
//                            mSwipeRefreshLayout.setRefreshing(false);
//                        }
//                    }
//
//                    @Override
//                    public void onNext(CommonBean commonBean) {
//                        mCommonBean = commonBean;
//                        mSubjectsEntities = commonBean.getSubjects();
//                        if (mFindCinemaAdapter == null) {
//                            mFindCinemaAdapter = new FindCinemaAdapter(mSubjectsEntities, getActivity());
//                            mRecyclerView.setAdapter(mFindCinemaAdapter);
//                        } else {
//                            mFindCinemaAdapter.update(mSubjectsEntities);
//                        }
//                    }
//                });
//    }
}
