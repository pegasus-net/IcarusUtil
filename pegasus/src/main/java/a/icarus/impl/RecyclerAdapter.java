package a.icarus.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a.icarus.inter.OnEmptyListener;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("unused")
public abstract class RecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final int layoutId;
    protected final List<T> list;
    protected Context context;
    protected OnEmptyListener onEmptyListener;

    public RecyclerAdapter(List<T> list, @LayoutRes int layoutId) {
        this.layoutId = layoutId;
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        return onCreateViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public void setOnEmptyListener(OnEmptyListener onEmptyListener) {
        this.onEmptyListener = onEmptyListener;
    }


    public void notifyDataSetChangedWithEmpty() {
        notifyDataSetChanged();
        if (onEmptyListener != null) {
            onEmptyListener.isEmpty(list.isEmpty());
        }
    }

    public abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item, int position);

    public abstract VH onCreateViewHolder(@NonNull View view);

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public final <T extends View> T findViewById(@IdRes int id) {
            return itemView.findViewById(id);
        }
    }
}
