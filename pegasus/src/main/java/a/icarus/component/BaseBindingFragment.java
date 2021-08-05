package a.icarus.component;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
@SuppressWarnings("unused")
abstract public class BaseBindingFragment<T extends ViewDataBinding> extends BaseFragment {

    protected T binding;

    @Override
    protected void initView(@NonNull View view) {
        binding = DataBindingUtil.bind(view);
    }

}
