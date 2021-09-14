package com.wang.babymonthlyreading.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.activity.SearchResultActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * 搜索页面搜索历史、热门搜索RecyclerView适配器
 */
public class SearchHintAdapter extends RecyclerView.Adapter<SearchHintAdapter.ViewHolder> {
    /**
     * 由于搜索历史需要添加到头部，LinkedList更加高效
     */
    private final LinkedList<String> infoList;
    private Context context;


    /**
     * 只会取infos中前9个元素
     *
     * @param infos
     */
    public SearchHintAdapter(List<String> infos) {
        this.infoList = new LinkedList<>();
        addAll2InfoList(infos);
    }

    private static final String TAG = "SearchHintAdapter";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_search_hint, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.hintText.setOnClickListener(v -> {
            TextView textView = (TextView) v;
            SearchResultActivity.startSearchResultActivity(context, textView.getText().toString());
            Activity searchActivity = (Activity) this.context;
            searchActivity.finish();
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String info = infoList.get(position);
        holder.hintText.setText(info);
    }

    /**
     * 向RecyclerView中添加一条数据, 添加到头部
     * 如果数据已存在，则不添加
     * 如果infoList长度大于9，那么删除最后一条数据
     * <p>
     * 使用notifyItemInserted(0);刷新数据集的时候，第二行高度会变大，原因未知
     *
     * @param info
     */
    @SuppressLint("NotifyDataSetChanged")
    public void addFirst(String info) {
        if (infoList.contains(info))
            return;
        addFirst2InfoList(info);
        notifyDataSetChanged();
    }

    /**
     * 移除所有数据
     */
    @SuppressLint("NotifyDataSetChanged")
    public void removeAll() {
        infoList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    /**
     * 把{infos}集合中的元素添加到infoList
     * 如果infos集合长度大于9，那么之取前面的9个
     * 这是因为搜索历史、热门搜索只需要显示前面的9个item
     *
     * @param infos
     */
    private void addAll2InfoList(@NonNull List<String> infos) {
        if (infos.size() <= 9) {
            infoList.addAll(infos);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            infoList.add(infos.get(i));
        }
    }

    /**
     * 向infoList集合头部添加一个元素
     * 如果infoList长度大于9，那么删除最后一个元素
     *
     * @param info
     */
    private void addFirst2InfoList(@NonNull String info) {
        infoList.addFirst(info);
        if (infoList.size() > 9) {
            infoList.removeLast();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView hintText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hintText = itemView.findViewById(R.id.text_item_search_hint);
        }
    }

    /**
     * 设置每个item的间距
     * 每一行三个item:
     * 第一个元素设置右外边距为space * 2/3
     * 第二个元素设置左外边距为space/2， 右外边距为x/2
     * 第三个元素设置左外边距为space * 2/3
     *
     * @link {https://wsl-1258519056.cos.ap-guangzhou.myqcloud.com/img/image-20210901111154841.png}
     */
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            int position = parent.getChildLayoutPosition(view);
            if (position % 3 == 0) {
                outRect.right = space * 2 / 3;
            } else if (position % 3 == 1) {
                outRect.left = space / 2;
                outRect.right = space / 2;
            } else {
                outRect.left = space * 2 / 3;
            }
            if (position >= 3) {
                outRect.top = 20;
            }
        }
    }
}
