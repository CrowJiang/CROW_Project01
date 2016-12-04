package com.udaye.movie.util;

import android.content.Context;

/**
 * 应用配置
 */
public class AppConfig {

    private static String KEY_IS_GPS = "key_is_see";
    private static String KEY_IS_Cache = "key_is_cache";

    /**
     * 设置是否只看女明星
     *
     * @param context
     * @param isGPS
     */
    public static void setIsGPS(Context context, boolean isGPS) {
        SPUtils.put(context, KEY_IS_GPS, isGPS);
    }

    /**
     * 获取是否只看女明星
     *
     * @param context
     * @return
     */
    public static boolean getIsGPS(Context context) {
        return (boolean) SPUtils.get(context, KEY_IS_GPS, false);
    }

    /**
     * 设置是否缓存数据
     *
     * @param context
     * @param isSee
     */
    public static void setIsCache(Context context, boolean isSee) {
        SPUtils.put(context, KEY_IS_Cache, isSee);
    }

    /**
     * 获取是否缓存数据
     *
     * @param context
     * @return
     */
    public static boolean getIsCache(Context context) {
        return (boolean) SPUtils.get(context, KEY_IS_Cache, true);
    }

}
