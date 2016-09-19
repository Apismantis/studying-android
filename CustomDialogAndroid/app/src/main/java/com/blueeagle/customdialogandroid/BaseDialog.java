package com.blueeagle.customdialogandroid;

import android.widget.LinearLayout.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);

        // config base dialog
        // set background to transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // hide dialog title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Rect displayRectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        int minWidth = (int) (displayRectangle.width() * 0.9);

        // set width, height for dialog
        getWindow().setLayout(minWidth, LayoutParams.WRAP_CONTENT);
    }

}
