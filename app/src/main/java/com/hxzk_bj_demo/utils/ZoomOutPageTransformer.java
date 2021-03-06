package com.hxzk_bj_demo.utils;

import android.annotation.SuppressLint;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * 解决viewpager从a滑动到b,中间tab闪现的问题
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    @SuppressLint("NewApi")
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        // [-Infinity,-1)
        if (position < -1) {
            view.setAlpha(MIN_ALPHA);
            view.setScaleX(MIN_SCALE);
            //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
        } else if (position <= 1)
        { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;

            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(MIN_ALPHA);
            view.setScaleX(MIN_SCALE);
        }
    }
}