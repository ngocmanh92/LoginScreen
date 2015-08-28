package com.example.myapplication;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.fragments.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MaterialTabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_tabs);

        List<String> titles = getTitles();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabview);
        for (int i = 0; i < titles.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
                showSnackBar(adapter.getPageTitle(position).toString());
            }
        });
    }

    public void showSnackBar(String title){
        Snackbar.make(findViewById(R.id.viewpager),title,Snackbar.LENGTH_LONG)
                .setAction("My action", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }
    public List<String> getTitles(){
        List<String> titles = new ArrayList<String>();
        for (int i = 0; i < 3; i++){
            titles.add("Tab " + (i+1));
        }
        return titles;
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter{

        List<String> titles;
        public MyPagerAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(titles.get(position));
        }


        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }

    public static class PageFragment extends Fragment{
        public static Fragment newInstance(String title){
            Fragment fragment = new PageFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            fragment.setArguments(args);
            return fragment;
        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(container.getContext());
            String title = getArguments().getString("title");
            textView.setText(title);
            return textView;
        }
    }










}
