package a.icarus.impl;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import a.icarus.utils.Cast;

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
            holder = Cast.cast(convertView.getTag());
        }
        onBindViewHolder(holder, list.get(position));
        return convertView;

    }

    protected abstract VH onCreateViewHolder(View convertView);

    protected abstract void onBindViewHolder(VH holder, T item);

    public static class ViewHolder {
        public final View rootView;

        public ViewHolder(@NonNull View itemView) {
            rootView = itemView;
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

    public interface OnEmptyListener {
        void isEmpty(boolean isEmpty);
    }
}
