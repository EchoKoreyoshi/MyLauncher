package com.itwwy.mylauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mList;
    private AppsAdapter mAppsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        onSetWallpaper();
        mList = findViewById(R.id.apps_list);
        mList.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        mList.addItemDecoration(new GridSpacingItemDecoration(4, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
        List<AppUtils.AppInfo> appsInfo = AppUtils.getAppsInfo();
        for (int i = 0; i < appsInfo.size(); i++) {
            if (AppUtils.isSystemApp(appsInfo.get(i).getPackageName())) {
                appsInfo.remove(i);
            }
        }
        for (int m = 0; m < appsInfo.size(); m++) {
            if (appsInfo.get(m).getName().contains("com."))
                appsInfo.remove(m);
        }
        mAppsAdapter = new AppsAdapter(R.layout.item_apps, appsInfo);
        mAppsAdapter.setOnItemClickListener(this);
        mList.setAdapter(mAppsAdapter);
    }

    private void onSetWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper, "chooser_wallpaper");
        //发送设置壁纸的请求
        startActivity(chooser);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String packageName = mAppsAdapter.getData().get(position).getPackageName();
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            ActivityUtils.startActivity(launchIntentForPackage);
        } else {
//            ToastUtils.showShort("此应用没有可视化界面！");
            Toast.makeText(this, "此应用没有可视化界面!", Toast.LENGTH_SHORT).show();
        }
    }
}
