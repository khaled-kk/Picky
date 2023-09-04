package com.myapplication.Picky;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentAdapter extends FragmentStateAdapter{

    public fragmentAdapter( FragmentManager fragmentManager,  Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
// it is made to call the taps of the Patient

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new second();
            case 2 :
                return new sixth();
            case 3:
                return new fifth();

        }

        return new first();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
