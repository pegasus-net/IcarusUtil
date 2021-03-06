package a.icarus.impl;

import android.view.View;

import org.jetbrains.annotations.NotNull;

import a.icarus.R;
import a.icarus.component.BaseFragment;

@SuppressWarnings("unused")
public class ColorFragment extends BaseFragment {
    private final int color;

    public ColorFragment(int color) {
        this.color = color;
    }

    @Override
    protected int setLayout() {
        return R.layout.empty;
    }

    @Override
    protected void initView(@NotNull View view) {
        rootView.setBackgroundColor(color);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
