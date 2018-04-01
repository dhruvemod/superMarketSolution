package com.apps.dcodertech.supermarketsolution;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    private String title[]={"Logs","Inventory"};
    public ViewPagerAdapter(FragmentManager manager,int numberOfTabs){
        super(manager);
        this.tabCount = numberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BillActivity tab1 = new BillActivity();
                return tab1;
            case 1:
                inventoryActivity tab3 = new inventoryActivity();
                return tab3;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
