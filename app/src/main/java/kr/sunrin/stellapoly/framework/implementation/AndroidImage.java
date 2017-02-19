package kr.sunrin.stellapoly.framework.implementation;

import android.graphics.Bitmap;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Image;


public class AndroidImage implements Image {
    public Bitmap bitmap;
    Graphics.ImageFormat format;
    
    public AndroidImage(Bitmap bitmap, Graphics.ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }      
}
