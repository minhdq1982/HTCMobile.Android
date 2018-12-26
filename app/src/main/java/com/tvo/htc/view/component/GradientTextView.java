package com.tvo.htc.view.component;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;

public class GradientTextView extends android.support.v7.widget.AppCompatTextView {
    TextPaint textPaint;

    public GradientTextView(Context context) {
        super(context);
        initView();
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) textPaint.measureText(getText().toString()), (int) textPaint.getFontSpacing());
    }

    private void initView() {
        textPaint = getPaint();
    }

    public void setGradient(int[] colors) {
        createBoringLayout(colors);
        invalidate();
    }

    private void createBoringLayout(int[] colors) {
        Shader textShader = new LinearGradient(
                0,
                getLineHeight(),
                0,
                0,
                colors,
                new float[]{0f, 0.51f, 0.52f, 1f},
                Shader.TileMode.CLAMP); //Assumes bottom and top are colors defined above
        textPaint.setShader(textShader);
    }
}
