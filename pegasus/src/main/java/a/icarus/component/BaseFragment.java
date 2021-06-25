package a.icarus.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

@SuppressWarnings("unused")
abstract public class BaseFragment extends Fragment {

    protected  String TAG;

    protected View rootView;
    protected Context mContext;
    protected AppCompatActivity mActivity;
    protected FragmentManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "TAG:" + getClass().getSimpleName();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), null);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
        if (mActivity != null) {
            manager = mActivity.getSupportFragmentManager();
        }
        initData();
        initListener();
    }

    protected final <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    protected abstract @LayoutRes
    int setLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected void initListener() {
    }

}
