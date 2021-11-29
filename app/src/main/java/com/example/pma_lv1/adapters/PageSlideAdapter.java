package com.example.pma_lv1.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageSlideAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> lFragment = new ArrayList<>();
    public PageSlideAdapter(FragmentManager fm, int behavior){
        super(fm, behavior);
    }
    @Override
    public Fragment getItem(int position){
        return lFragment.get(position);
    }
    @Override
    public int getCount(){
        return lFragment.size();
    }
    public void addFragment(Fragment f){
        lFragment.add(f);
    }
}
