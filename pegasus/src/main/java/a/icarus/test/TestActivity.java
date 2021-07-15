package a.icarus.test;

import android.view.View;
import android.view.ViewGroup;

import a.icarus.R;
import a.icarus.component.BaseActivity;
@SuppressWarnings("unused")
public class TestActivity extends BaseActivity {
    private ViewGroup rootView;

    @Override
    protected void initView() {
        setContentView(R.layout.test_activity);
        rootView = findViewById(R.id.test_root);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    protected void setBackgroundColor(int color) {
        rootView.setBackgroundColor(color);
    }

    public void clickA(View view) {
    }

    public void clickB(View view) {
    }

    public void clickC(View view) {
    }

    public void clickD(View view) {
    }

    public void clickE(View view) {
    }

    public void clickF(View view) {
    }

}
