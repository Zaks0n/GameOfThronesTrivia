package com.an.got.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.an.got.R;

public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
        setTypeFace(1);
    }

    public CustomButton(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomButton);
        final int fontValue = a.getInt(R.styleable.CustomButton_font, 0);
        a.recycle();
        setTypeFace(fontValue);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomButton);
        final int fontValue = a.getInt(R.styleable.CustomButton_font, 0);
        a.recycle();
        setTypeFace(fontValue);
    }

    public void setTypeFace(int fontValue) {
        Typeface myTypeFace;
        if (fontValue == 1) {
            myTypeFace = Typeface.createFromAsset(this.getContext().getAssets(),"architect_dauhter.ttf");
            this.setTypeface(myTypeFace);
        } else if (fontValue == 2) {
            myTypeFace = Typeface.createFromAsset(this.getContext().getAssets(), "halo_handletter.otf");
            this.setTypeface(myTypeFace);
        } else if (fontValue == 3) {
            myTypeFace = Typeface.createFromAsset(this.getContext().getAssets(), "gt_medium.otf");
            this.setTypeface(myTypeFace);
        }
    }

    @Override
    public void setBackgroundDrawable(Drawable d) {
        // Replace the original background drawable (e.g. image) with a LayerDrawable that
        // contains the original drawable.
        SAutoBgButtonBackgroundDrawable layer = new SAutoBgButtonBackgroundDrawable(d);
        super.setBackgroundDrawable(layer);
    }

    /**
     * The stateful LayerDrawable used by this button.
     */
    protected class SAutoBgButtonBackgroundDrawable extends LayerDrawable {

        // The color filter to apply when the button is pressed
        protected ColorFilter _pressedFilter = new LightingColorFilter(Color.LTGRAY, 1);
        // Alpha value when the button is disabled
        protected int _disabledAlpha = 100;

        public SAutoBgButtonBackgroundDrawable(Drawable d) {
            super(new Drawable[] { d });
        }

        @Override
        protected boolean onStateChange(int[] states) {
            boolean enabled = false;
            boolean pressed = false;

            for (int state : states) {
                if (state == android.R.attr.state_enabled)
                    enabled = true;
                else if (state == android.R.attr.state_pressed)
                    pressed = true;
            }

            mutate();
            if (enabled && pressed) {
                setColorFilter(_pressedFilter);
            } else if (!enabled) {
                setColorFilter(null);
                setAlpha(_disabledAlpha);
            } else {
                setColorFilter(null);
            }

            invalidateSelf();

            return super.onStateChange(states);
        }

        @Override
        public boolean isStateful() {
            return true;
        }
    }
}