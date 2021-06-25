package a.icarus.utils;

import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PagerHelper {

    public static void bind(ViewPager pager, BottomNavigationView navigation) {
        final PagerAdapter adapter = pager.getAdapter();
        final Menu menu = navigation.getMenu();
        if (adapter == null || adapter.getCount() <= 0) {
            new BindException("ViewPager Adapter is empty").printStackTrace();
            return;
        }
        if (menu == null || menu.size() <= 0) {
            new BindException("BottomNavigationView Menu is empty").printStackTrace();
            return;
        }
        if (pager.getAdapter().getCount() != navigation.getMenu().size()) {
            new BindException("Data size is not same").printStackTrace();
            return;
        }
        pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                navigation.setSelectedItemId(navigation.getMenu().getItem(position).getItemId());
            }
        });
        navigation.setOnNavigationItemSelectedListener(item -> {
            for (int i = 0; i < menu.size(); i++) {
                if (Objects.equals(menu.getItem(i), item)) {
                    pager.setCurrentItem(i, false);
                }
            }
            return true;
        });
    }

    private static class BindException extends Exception {

        public BindException(String message) {
            super(message);
        }
    }

    abstract private static class OnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
