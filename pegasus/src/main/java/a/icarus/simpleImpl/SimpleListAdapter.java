package a.icarus.simpleImpl;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

abstract public class SimpleListAdapter<T, VH extends SimpleListAdapter.ViewHolder> extends BaseAdapter {
    public final List<T> list;

    public SimpleListAdapter(List<T> list, @LayoutRes int layoutId) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder = null;
        if (convertView == null) {
            holder = onCreateViewHolder(parent);
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }
        onBindViewHolder(holder, list.get(position));
        return convertView;

    }

    protected abstract VH onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(VH holder, T item);

    public static class ViewHolder {
        public final View rootView;

        public ViewHolder(@NonNull View itemView) {
            rootView = itemView;
        }
    }
}
