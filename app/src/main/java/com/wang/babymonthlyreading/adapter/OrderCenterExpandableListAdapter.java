package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;
import com.wang.babymonthlyreading.enums.OrderState;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class OrderCenterExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<GroupEntity> groupList;
    private final List<List<ChildEntity>> childList;

    public OrderCenterExpandableListAdapter(List<GroupEntity> groupList, List<List<ChildEntity>> childList,
                                            Context context) {
        this.context = context;
        if (groupList == null) {
            throw new NullPointerException("groupList must be not null");
        }
        if (childList == null) {
            childList = new ArrayList<>();
        }
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupList.get(groupPosition).hashCode();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_expand_group, parent,
                    false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.groupTitleText = convertView.findViewById(R.id.text_group_title);
            groupViewHolder.orderStateText = convertView.findViewById(R.id.text_group_order_state);
            groupViewHolder.indicatorText = convertView.findViewById(R.id.text_group_indicator);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        GroupEntity groupEntity = groupList.get(groupPosition);
        groupViewHolder.groupTitleText.setText(groupEntity.title);


        if (isExpanded) {
            groupViewHolder.indicatorText.setBackgroundResource(R.drawable.ic_up);
            groupViewHolder.orderStateText.setVisibility(View.VISIBLE);
            String html = "<span style=\"color:#999999\">订单状态: </span>" + "<span style=\"color:#fe564c\">"
                    + groupEntity.orderState.getDesc() + "</span>";
            groupViewHolder.orderStateText.setText(Html.fromHtml(html,
                    Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
        } else {
            groupViewHolder.orderStateText.setVisibility(View.GONE);
            groupViewHolder.indicatorText.setBackgroundResource(R.drawable.ic_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_expand_child, parent,
                    false);
            childViewHolder = new ChildViewHolder();

            childViewHolder.orderBookImg = convertView.findViewById(R.id.img_order_book);
            childViewHolder.bookTitleText = convertView.findViewById(R.id.text_order_book_title);
            childViewHolder.bookPriceText = convertView.findViewById(R.id.text_order_book_price);
            childViewHolder.bookCountText = convertView.findViewById(R.id.text_order_book_count);
            childViewHolder.bookTagLinear = convertView.findViewById(R.id.linear_child_book_tag);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        ChildEntity childEntity = childList.get(groupPosition).get(childPosition);
        Glide.with(context).load(childEntity.bookImg).into(childViewHolder.orderBookImg);
        childViewHolder.bookTitleText.setText(childEntity.title);
        childViewHolder.bookPriceText.setText(formatPrice(childEntity.price));
        childViewHolder.bookCountText.setText(String.format(Locale.CHINA, "× %d", childEntity.count));
        addTags(childViewHolder.bookTagLinear, childEntity.booTags);
        return convertView;
    }

    private void addTags(LinearLayout layout, List<BookClassifyInfo> classifyInfoList) {
        for (BookClassifyInfo booTag : classifyInfoList) {
            TextView child = new TextView(context);
            child.setText(booTag.getDesc());
            child.setTextColor(ContextCompat.getColor(context, R.color.coffee));
            child.setTextSize(14);
            child.setBackgroundResource(R.drawable.book_tag_bg);
            child.setPadding(8, 0, 8, 0);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            child.setLayoutParams(params);
            layout.addView(child);
        }
    }

    private String formatPrice(float price) {
        return String.format(Locale.CHINA, "￥ %.2f", price);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupViewHolder {
        private TextView groupTitleText;
        private TextView orderStateText;
        private TextView indicatorText;
    }

    private static class ChildViewHolder {
        private ImageView orderBookImg;
        private TextView bookTitleText;
        private TextView bookPriceText;
        private TextView bookCountText;
        private LinearLayout bookTagLinear;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class ChildEntity {
        private Drawable bookImg;
        private String title;
        private float price;
        private int count;
        private List<BookClassifyInfo> booTags;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class GroupEntity {
        private String title;
        private OrderState orderState;
    }
}
