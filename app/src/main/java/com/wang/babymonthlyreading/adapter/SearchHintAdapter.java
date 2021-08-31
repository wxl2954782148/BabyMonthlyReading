package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * 搜索页面搜索历史、热门搜索RecyclerView适配器
 */
public class SearchHintAdapter extends RecyclerView.Adapter<SearchHintAdapter.ViewHolder> {
    List<String> infoList;
    private Context context;

    public SearchHintAdapter(List<String> infoList) {
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_search_hint, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String info = infoList.get(position);
        holder.hintBtn.setText(info);
    }

    /**
     * 向RecyclerView中添加一条数据
     * @param info
     */
    public void addItem(String info){
        infoList.add(info);
        notifyItemChanged(infoList.size()-1);
    }
    public void removeAll(){
        infoList.clear();
        notifyItemRangeRemoved(0, infoList.size()-1);
    }

    @Override
    public int getItemCount() {
        return infoList == null ? 0 : infoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Button hintBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hintBtn = itemView.findViewById(R.id.btn_item_search_hint);
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildLayoutPosition(view);
            if (position % 3 == 0) {
                outRect.right = space;
            } else if (position % 3 == 1) {
                outRect.left = space / 2;
                outRect.right = space / 2;
            } else {
                outRect.left = space;
            }
            if (position >= 3){
                outRect.top = 20;
            }
        }
    }
}
