package com.myapplication.Picky;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentAdapter2 extends FragmentStateAdapter{

    public fragmentAdapter2(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
// it is made to call the taps of the Doctor

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new fourth();
            case 2 :
                return new third();

        }

        return new first();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
