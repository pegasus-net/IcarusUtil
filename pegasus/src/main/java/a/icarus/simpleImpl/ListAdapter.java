package a.icarus.simpleImpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import a.icarus.utils.CastUtils;

@SuppressWarnings("unused")
abstract public class ListAdapter<T, VH extends ListAdapter.ViewHolder> extends BaseAdapter {
    private final List<T> list;
    private final int layoutId;
    private Context context;

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
        VH holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent);
            holder = onCreateViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = CastUtils.cast(convertView.getTag());
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
}
