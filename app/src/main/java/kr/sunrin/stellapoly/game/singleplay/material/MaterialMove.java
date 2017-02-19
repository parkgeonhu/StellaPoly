package kr.sunrin.stellapoly.game.singleplay.material;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;

import kr.sunrin.stellapoly.framework.Image;
import kr.sunrin.stellapoly.framework.implementation.AndroidImage;

/**
 * Created by 건후 on 2015-08-24.
 */
public class MaterialMove {
    Image bm;
    int bm_offsetX, bm_offsetY;

    Path animPath;
    PathMeasure pathMeasure;
    float pathLength;

    float step;   //distance each step, �����̴� �ӵ�
    float distance;  //distance moved

    float[] pos;
    float[] tan;

    Matrix matrix;

    MaterialMove(Image image, int initX, int initY, int desX, int desY) {
        bm_offsetX = 50 / 2;
        bm_offsetY = 50 / 2;
        animPath = new Path();
        animPath.moveTo(initX, initY);
        animPath.lineTo(desX, desY);
        animPath.close();

        pathMeasure = new PathMeasure(animPath, false);
        pathLength = pathMeasure.getLength();
        step = 3;
        distance = 0;
        pos = new float[2];
        tan = new float[2];

        matrix = new Matrix();
    }

    public void update() {
        if (distance < pathLength) {
            pathMeasure.getPosTan(distance, pos, tan);

            matrix.reset();
            float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

            distance += step;
        } else {
            distance = 0;
        }
    }

    public int getX() {
        return (int) (pos[0] - bm_offsetX);
    }

    public int getY() {
        return (int) (pos[1] - bm_offsetY);
    }

    Matrix getMatrix() {
        return matrix;
    }
}
