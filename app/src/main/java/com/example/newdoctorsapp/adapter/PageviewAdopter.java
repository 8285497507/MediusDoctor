package com.example.newdoctorsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Observable;

public class PageviewAdopter extends FragmentPagerAdapter  {
    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    ArrayList<String> Arraytitle=new ArrayList<>();
    public PageviewAdopter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Arraytitle.get(position);
    }

    public void AddFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        Arraytitle.add(title);

    }
}
