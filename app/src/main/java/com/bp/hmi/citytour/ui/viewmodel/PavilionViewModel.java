package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ScheduleEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

public class PavilionViewModel extends BaseViewModel
{
    private static final String                                TAG = PavilionViewModel.class.getSimpleName();
    public               SingleLiveEvent<List<ScheduleEntity>> mBookData;
    public               ObservableField<String>               mBookSum;

    /**
     * Constructor.
     *
     * @param application
     */
    public PavilionViewModel(@NonNull Application application)
    {
        super(application);
        initData();
    }

    protected void initData()
    {
        mBookData = new SingleLiveEvent<>();
        mBookSum = new ObservableField<>("1");
    }


    public BookViewModel.UiChangeObservable uiChangeObservable = new BookViewModel.UiChangeObservable();


    public static class UiChangeObservable
    {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }

    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());


    public void getBookData()
    {
        //模拟数据
        ArrayList<ScheduleEntity> list            = new ArrayList<>();
        ScheduleEntity            scheduleEntity1 = new ScheduleEntity();
        scheduleEntity1.setIcon(R.mipmap.item_1);
        scheduleEntity1.setTitle("中共一大会址");
        scheduleEntity1.setLocation("海兴业路76号");
        scheduleEntity1.setTimeA("08:00");
        scheduleEntity1.setTimeValueA("2小时");
        scheduleEntity1.setTimeB("10:00");
        scheduleEntity1.setTimeValueB("29分钟");
        scheduleEntity1.setWalkA("步行596米");
        scheduleEntity1.setWalkB("步行925米");
        scheduleEntity1.setMetroA("新天地站(10号线6号口)");
        scheduleEntity1.setMetroB("愚园路站(10号线3号口)");
        list.add(scheduleEntity1);
        ScheduleEntity scheduleEntity2 = new ScheduleEntity();
        scheduleEntity2.setIcon(R.mipmap.item_2);
        scheduleEntity2.setTitle("城隍庙");
        scheduleEntity2.setLocation("方浜中路249号");
        scheduleEntity2.setTimeA("10:29");
        scheduleEntity2.setTimeValueA("2小时");
        scheduleEntity2.setTimeB("12:30");
        scheduleEntity2.setTimeValueB("30分钟");
        scheduleEntity2.setWalkA("步行76米");
        scheduleEntity2.setWalkB("步行101米");
        scheduleEntity2.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity2.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity2);
        ScheduleEntity scheduleEntity3 = new ScheduleEntity();
        scheduleEntity3.setIcon(R.mipmap.item_3);
        scheduleEntity3.setTitle("东方明珠");
        scheduleEntity3.setLocation("方浜中路249号");
        scheduleEntity3.setTimeA("13:00");
        scheduleEntity3.setTimeValueA("3小时");
        scheduleEntity3.setTimeB("14:00");
        scheduleEntity3.setTimeValueB("30分钟");
        scheduleEntity3.setWalkA("步行76米");
        scheduleEntity3.setWalkB("步行101米");
        scheduleEntity3.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity3.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity3);

        mBookData.postValue(list);
    }
}
