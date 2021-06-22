package a.icarus.component;

import android.view.View;

import a.icarus.R;

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
    protected void initView(View view) {
        rootView.setBackgroundColor(color);
    }

    @Override
    protected void initData() {

    }
}
