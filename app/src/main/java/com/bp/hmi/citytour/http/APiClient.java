package com.bp.hmi.citytour.http;

import com.bp.hmi.citytour.bean.VideoBean;
import com.bp.hmi.citytour.bean.WeatherBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/08/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface APiClient {

    /**
     * 校验验证码是否正常
     */
    @FormUrlEncoded
    @POST(ApiService.HOME_VIDEO_API)
    Observable<VideoBean> verifyPhoneCode(@FieldMap Map<String, String> params);

    /**
     * 全部楼盘
     */
    @GET(ApiService.HOME_WEATHER_API)
    Observable<WeatherBean> getWeatherInfo(@QueryMap Map<String, String> params);

}
