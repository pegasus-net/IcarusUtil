package a.icarus.impl;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import a.icarus.inter.OnEmptyListener;
import a.icarus.utils.ConversionTool;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

@SuppressWarnings("unused")
abstract public class ListAdapter<T, VH extends ListAdapter.ViewHolder> extends BaseAdapter {
    protected final List<T> list;
    protected final int layoutId;
    protected Context context;
    protected OnEmptyListener onEmptyListener;

    public void setOnEmptyListener(OnEmptyListener onEmptyListener) {
        this.onEmptyListener = onEmptyListener;
    }

    public ListAdapter(List<T> list, @LayoutRes int layoutId) {
        this.list = list;
        this.layoutId = layoutId;
    }

    public List<T> getList() {
        return list;
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
        if (context == null) {
            context = parent.getContext();
        }
        VH holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            holder = onCreateViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ConversionTool.cast(convertView.getTag());
        }
        onBindViewHolder(holder, list.get(position), position);
        return convertView;

    }

    protected abstract VH onCreateViewHolder(@NonNull View convertView);

    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item, int position);

    public static class ViewHolder {
        public final View rootView;

        public ViewHolder(@NonNull View itemView) {
            rootView = itemView;
        }

        public final <T extends View> T findViewById(@IdRes int id) {
            return rootView.findViewById(id);
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        if (onEmptyListener != null) {
            onEmptyListener.isEmpty(list.isEmpty());
        }

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (onEmptyListener != null) {
            onEmptyListener.isEmpty(list.isEmpty());
        }
    }
}
