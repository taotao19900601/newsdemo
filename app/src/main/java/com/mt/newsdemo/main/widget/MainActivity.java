package com.mt.newsdemo.main.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mt.newsdemo.R;
import com.mt.newsdemo.main.presenter.MainPresenter;
import com.mt.newsdemo.main.presenter.MainPresenterImpl;
import com.mt.newsdemo.main.view.MainView;
import com.mt.newsdemo.utils.LogUtil;

public class MainActivity extends AppCompatActivity implements MainView {
    public String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDawableContent(mNavigationView);
        mMainPresenter = new MainPresenterImpl(this);
        switch2News();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupDawableContent(NavigationView mNavigationView) {
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item != null) {
                        mMainPresenter.switchNavigation(item.getItemId());
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void switch2News() {
        LogUtil.e(TAG, "switch2News");
    }

    @Override
    public void switch2Images() {
        LogUtil.e(TAG, "switch2Images");
    }

    @Override
    public void switch2Weather() {
        LogUtil.e(TAG, "switch2Weather");
    }

    @Override
    public void switch2About() {
        LogUtil.e(TAG, "switch2About");
    }
}
