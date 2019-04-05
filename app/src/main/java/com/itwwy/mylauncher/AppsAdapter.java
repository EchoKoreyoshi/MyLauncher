package com.itwwy.mylauncher;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ：vee
 * @package ：com.itwwy.mylauncher
 * @date ：2019/4/6
 * @describe :
 */
public class AppsAdapter extends BaseQuickAdapter<AppUtils.AppInfo, BaseViewHolder> {
    public AppsAdapter(int layoutResId, @Nullable List<AppUtils.AppInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppUtils.AppInfo item) {
            helper.setText(R.id.tv_app, item.getName());
            helper.setImageDrawable(R.id.img_app, item.getIcon());
    }
}
