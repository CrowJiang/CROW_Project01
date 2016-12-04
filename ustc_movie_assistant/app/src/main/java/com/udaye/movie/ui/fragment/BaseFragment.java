package com.udaye.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.udaye.movie.data.retrofit.RetrofitRepository;

/**
 * fragment 基类
 * <p/>
 */
public  class BaseFragment extends Fragment {
    RetrofitRepository mRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = RetrofitRepository.getInstance(getActivity());
    }
}
