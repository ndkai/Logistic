package com.iot.logisticsapp.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.google.rpc.context.AttributeContext;
import com.iot.logisticsapp.R;

public class Helper {
    public static Bitmap convertIconRawToMaker(Resources res, int id, String text) {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(400, 400, conf);
        Canvas canvas1 = new Canvas(bmp);

// paint defines the text color, stroke width and size
        Paint color = new Paint();
        color.setTextSize(40);
        color.setColor(0xffEE5F5F);
        color.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;

        Bitmap imageBitmap = BitmapFactory.decodeResource(res,
                id, opt);
        Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, Constants.markerWidth, Constants.markerHeight, true);

// modify canvas
        canvas1.drawBitmap(resized, 0, 0, color);
        canvas1.drawText(text, 30, 40, color);
        return bmp;

    }
}
