package com.bp.hmi.citytour.http;

import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.EnteredShBean;
import com.bp.hmi.citytour.bean.HomeVideoBean;
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

    @FormUrlEncoded
    @POST(ApiService.HOME_API_ENTER_SH)
    Observable<HomeVideoBean> verifyPhoneCode(@FieldMap Map<String, String> params);

    /**
     * 获取天气
     */
    @GET(ApiService.HOME_WEATHER_API)
    Observable<WeatherBean> getWeatherInfo(@QueryMap Map<String, String> params);

    /**
     * 走进上海
     */
    @GET(ApiService.HOME_API_ENTER_SH)
    Observable<EnteredShBean> getEnterShData();

    /**
     * 视频
     */
    @GET(ApiService.HOME_API_HOME_VIDEO)
    Observable<HomeVideoBean> getVideoData();


    /**
     * 活动
     */
    @GET(ApiService.HOME_API_HOME_ACTIVITY)
    Observable<ActivityTabBean> getActivityData();

    /**
     * 购物券
     */
    @GET(ApiService.HOME_API_HOME_CARDS)
    Observable<CardsBean> getCardsData();





}
