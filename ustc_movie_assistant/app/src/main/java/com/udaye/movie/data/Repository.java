package com.udaye.movie.data;

import java.util.List;

import com.udaye.movie.entity.CelebrityBean;
import com.udaye.movie.entity.CommonBean;
import com.udaye.movie.entity.MovieDetailBean;
import com.udaye.movie.entity.TheatersMoive;
import com.udaye.movie.entity.MySpaceBean;

import rx.Observable;

/**
 * 修改历史：
 */
public interface Repository {

    /**
     * 获取正在上映
     *
     * @param city
     * @return
     */
    Observable<List<TheatersMoive.SubjectsEntity>> gettheatersMovie(String city);


    /**
     * 获取即将上映的电影
     *
     * @param x
     * @param y
     * @return
     */
    Observable<CommonBean> getFindCinema(int x, int y);

    /**
     * 获取文章详情
     *
     * @param id id
     * @return
     */
    Observable<MovieDetailBean> getMovieDetail(int id);

    /**
     * 获取演员详情
     *
     * @param id
     * @return
     */
    Observable<CelebrityBean> getCelebrityDetail(int id);


    /**
     * 我最喜欢电影
     *
     * @param start
     * @param count
     * @return
     */
    Observable<MySpaceBean> getMySpaceMovie(int start, int count);
}