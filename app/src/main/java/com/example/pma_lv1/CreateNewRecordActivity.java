package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.pma_lv1.adapters.PageSlideAdapter;
import com.example.pma_lv1.fragments.PersonalInfoFragment;
import com.example.pma_lv1.fragments.StudentInfoFragment;
import com.example.pma_lv1.fragments.SummaryFragment;
import com.example.pma_lv1.viewModels.SummaryVM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CreateNewRecordActivity extends AppCompatActivity {
    ViewPager viewPager;
    PageSlideAdapter pageSlideAdapter;
    private SummaryVM viewModelSummary;
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