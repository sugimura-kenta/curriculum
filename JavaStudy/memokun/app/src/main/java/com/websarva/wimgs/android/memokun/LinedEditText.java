package com.websarva.wimgs.android.memokun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

//メモ帳のように罫線のあるEditTextにしたいためカスタム。
public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    //コンストラクタ
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //EditTextViewのレイアウト後の幅
        int width = getMeasuredWidth();
        //EditTextViewのレイアウト後の高さ
        int height = getMeasuredHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom();
        //パッディングを考慮
        int paddingTop = getExtendedPaddingTop();
        //テキストの高さ
        int lineHeight = getLineHeight();
        //有効な描写領域から行数を計算
        int textCount = height / lineHeight;
        //入力された行数
        int lines = this.getLineCount();
        //有効な行数と実際に入力された行数のうち、大きい方を選ぶ
        int lineCount = Math.max(textCount, lines);

        float[] points = new float[lineCount << 2];
        //座標位置を計算
        for(int i = 0; i < lineCount; i++) {
            points[(i << 2) + 0] = 0;
            points[(i << 2) + 1] = i * lineHeight + paddingTop;
            points[(i << 2) + 2] = width;
            points[(i << 2) + 3] = i * lineHeight;
        }

        //色の設定
        Paint paint = getPaint();
        paint.setColor(Color.parseColor("#acacac"));
        //直線の描写
        canvas.drawLines(points, paint);
        //親クラスの描画処理
        super.onDraw(canvas);

    }
}
