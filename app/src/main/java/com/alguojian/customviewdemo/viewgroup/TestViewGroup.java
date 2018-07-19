package com.alguojian.customviewdemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.alguojian.customviewdemo.layoutprams.WallLayoutParams;

/**
 * 自定义横向瀑布流，实现单选和多选
 *
 * @author alguojian
 * @date 2018/7/18
 */
public class TestViewGroup extends ViewGroup {

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    /**
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0;
        int left = 0;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {

            View childAt = getChildAt(i);

            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();

            int measuredHeight = childAt.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
            int measuredWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            //换行
            if (measuredWidth + lineWidth > getMeasuredWidth()) {

                top += lineHeight;
                left = 0;
                lineHeight = measuredHeight;
                lineWidth = measuredWidth;
            } else {

                lineHeight = Math.max(measuredHeight, lineHeight);
                lineWidth += measuredWidth;
            }

            //的到字view的当前位置
            int ll = left + layoutParams.leftMargin;
            int tt = top + layoutParams.topMargin;
            int rr = ll + childAt.getMeasuredWidth();
            int bb = tt + childAt.getMeasuredHeight();

            childAt.layout(ll, tt, rr, bb);

            left += measuredWidth;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new WallLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new WallLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new WallLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 测量子view
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //每一行的高度
        int lineWidth = 0;

        //每一行的高度
        int lineHeight = 0;

        //整个viewGroup的宽度
        int allWidth = 0;

        //整个viewGroup的高度
        int allHeight = 0;

        //获得系统的建议宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //获得控件的宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        //获得子view的数量
        int count = getChildCount();

        //遍历测量子view
        for (int i = 0; i < count; i++) {

            //获得子view对象
            View childAt = getChildAt(i);

            //测量子view，通过自view对象以及父view的测量参数
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            //获得子view的margin的layoutParams来获得子view的四周间距，兼容自定义viewGroup设置了margin
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();

            //获得子view测量的宽度
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            //获得子view测量的高度
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;


            //如果现在的宽度加上一个字view的宽度大于整个viewGroup的宽度，那么就需要换行显示
            if (lineWidth + childWidth > width) {

                //换行后，刷新viewGroup的宽度，记录一个最大值
                allWidth = Math.max(lineWidth, childWidth);

                //记录viewGroup的高度
                allHeight += lineHeight;

                lineWidth = childWidth;
                lineHeight = childHeight;

            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }


            //当时最后一行的时候，就不会换行，所以需要手动加上最后一行的高度
            if (i == count - 1) {

                allHeight += lineHeight;

                //获得最大的宽度，比较值钱宽度的最大值，以及当前行的宽度值
                allWidth = Math.max(lineWidth, allWidth);
            }

        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? width : allWidth, heightMode == MeasureSpec.EXACTLY ? height : allHeight);

    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View childAt = getChildAt(i);

            childAt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(index);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
