package a.icarus.simpleIml;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("unused")
public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;
    private List<String> titles;

    public SimpleFragmentAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    public SimpleFragmentAdapter(@NonNull AppCompatActivity activity, @NonNull List<Fragment> fragments) {
        super(activity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() == fragments.size()) {
            return titles.get(position);
        }
        return super.getPageTitle(position);
    }
}
