package com.mt.newsdemo.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mt.newsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NewsFragment extends Fragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private static final int PAGELIMIT = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(PAGELIMIT);
        setupViewPager();
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.top));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.cars));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.jokes));
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager() {
        MyPagerAdapter mPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),getString(R.string.top));
        mPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),getString(R.string.nba));
        mPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS),getString(R.string.cars));
        mPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES),getString(R.string.jokes));
        mViewPager.setAdapter(mPagerAdapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<String> mFragmentTitles = new ArrayList<String>();
        private final List<Fragment> mFragments = new ArrayList<Fragment>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
