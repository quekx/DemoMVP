package com.example.qkx.demomvp.welfaredetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.qkx.demomvp.R;
import com.example.qkx.demomvp.injection.Injection;
import com.example.qkx.demomvp.utils.ActivityUtils;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareDetailActivity extends AppCompatActivity {

    private WelfareDetailFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welfare_act);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        init();

//        W
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 导航
        toolbar.setNavigationIcon(R.drawable.login_ban_back);
        // Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title and Subtitle
        toolbar.setTitle("Welfare");
        toolbar.setSubtitle("福利");
//        toolbar.setTitleTextColor();
        // Menu
        toolbar.inflateMenu(R.menu.toolbar_menu);
        // 点击
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(WelfareDetailActivity.this, "search!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_refresh_random:
                        Toast.makeText(WelfareDetailActivity.this, "refresh random!", Toast.LENGTH_SHORT).show();
                        mFragment.refreshRandom();
                        break;
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelfareDetailActivity.this, "back!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        mFragment = (WelfareDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (null == mFragment) {
            mFragment = WelfareDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mFragment, R.id.contentFrame);
        }

        new WelfareDetailPresenter(Injection.provideWelfareRepository(this), mFragment);
    }

}
