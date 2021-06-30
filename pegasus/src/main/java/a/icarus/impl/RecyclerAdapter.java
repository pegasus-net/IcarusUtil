package a.icarus.impl;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a.icarus.utils.Cast;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("unused")
public abstract class RecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final int layoutId;
    protected final List<T> list;
    protected Context context;
    protected ListAdapter.OnEmptyListener onEmptyListener;

    public RecyclerAdapter(int layoutId, List<T> list) {
        this.layoutId = layoutId;
        this.list = list;
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
        return 0;
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public void setOnEmptyListener(ListAdapter.OnEmptyListener onEmptyListener) {
        this.onEmptyListener = onEmptyListener;
    }

    public interface OnEmptyListener {
        void isEmpty(boolean isEmpty);
    }


    public void notifyDataSetChangedE() {
        notifyDataSetChanged();
        if (onEmptyListener != null) {
            onEmptyListener.isEmpty(list.isEmpty());
        }
    }

    public abstract void onBindViewHolder(VH holder, T item, int position);

    public abstract VH onCreateViewHolder(View view);

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public final <T extends View> T findViewById(@IdRes int id) {
            return itemView.findViewById(id);
        }
    }
}
