package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.pma_lv1.adapters.PageSlideAdapter;
import com.example.pma_lv1.fragments.PersonalInfoFragment;
import com.example.pma_lv1.fragments.StudentInfoFragment;
import com.example.pma_lv1.fragments.SummaryFragment;

public class CreateNewRecordActivity extends AppCompatActivity {
    ViewPager viewPager;
    PageSlideAdapter pageSlideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);

        viewPager = findViewById(R.id.viewPager);
        pageSlideAdapter = new PageSlideAdapter(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        pageSlideAdapter.addFragment(new PersonalInfoFragment());
        pageSlideAdapter.addFragment(new StudentInfoFragment());
        pageSlideAdapter.addFragment(new SummaryFragment());
        viewPager.setAdapter(pageSlideAdapter);
    }
}