package com.fhi.sampledimvc.mvp.view.Util;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * @author David Wu (david10608@gmail.com)
 *         Created by pl4gue on 10.10.17.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    Drawable mDivider;

    public DividerItemDecoration(Drawable canvas) {
        mDivider = canvas;
    }

    @Override
    public void getItemOffsets(Rect outRect, android.view.View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = mDivider.getIntrinsicHeight(); //keeping space for divider
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        android.view.View last = parent.getChildAt(0);
        for (int i = 1; i < parent.getChildCount() - 1; i++) {
            mDivider.setBounds(last.getLeft(), last.getBottom(), last.getRight(), last.getBottom() + mDivider.getIntrinsicHeight());
            mDivider.draw(c);
            android.view.View v = parent.getChildAt(i);
            last = v;
        }
    }
}
