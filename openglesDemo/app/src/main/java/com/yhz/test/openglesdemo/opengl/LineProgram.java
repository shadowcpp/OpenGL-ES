package com.yhz.test.openglesdemo.opengl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class LineProgram extends ShaderProgram {

    private static final int COORDS_PER_VERTEX = 2;
    private static final int STRIDE = COORDS_PER_VERTEX * 4;
    private static final int POSITION_COUNT = 2;

    private final int mPositionLocation;
    private final int mColorLocation;

    public LineProgram(Context context, int width, int height) {
        super(context, VERTEX, FRAGMENT, width, height);

        mPositionLocation = GLES20.glGetAttribLocation(mProgram, "a_Position");
        mColorLocation = GLES20.glGetUniformLocation(mProgram, "u_Color");
    }



    public void drawRect(RectF rect, int color, float lineWidth) {
        useProgram();

        float x1s = transformX(rect.left);
        float y1s = transformY(rect.top);
        float x2s = transformX(rect.right);
        float y2s = transformY(rect.bottom);
        float[] pointpPosis = new float[5 * POSITION_COUNT];
        FloatBuffer pointsbuffer = ByteBuffer
            .allocateDirect(STRIDE * 5)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();
        int index = 0;
        pointpPosis[index++] = x1s;
        pointpPosis[index++] = y1s;

        pointpPosis[index++] = x1s;
        pointpPosis[index++] = y2s;

        pointpPosis[index++] = x2s;
        pointpPosis[index++] = y2s;

        pointpPosis[index++] = x2s;
        pointpPosis[index++] = y1s;

        pointpPosis[index++] = x1s;
        pointpPosis[index++] = y1s;

        pointsbuffer.position(0);
        pointsbuffer.put(pointpPosis);

        pointsbuffer.position(0);
        GLES20.glVertexAttribPointer(mPositionLocation, POSITION_COUNT, GLES20.GL_FLOAT,
            false, STRIDE, pointsbuffer);
        GLES20.glEnableVertexAttribArray(mPositionLocation);

        float r = Color.red(color) / 255f;
        float g = Color.green(color) / 255f;
        float b = Color.blue(color) / 255f;
        float a = Color.alpha(color) / 255f;
        GLES20.glUniform4f(mColorLocation, r, g, b, a);

        GLES20.glLineWidth(lineWidth);
        GLES20.glDrawArrays(GLES20.GL_LINE_STRIP, 0, 5);

        GLES20.glDisableVertexAttribArray(mPositionLocation);
    }

    public void drawLineStrip(PointF points[], int color, float linewidth) {
        useProgram();

        float[] pointpPosis = new float[points.length * POSITION_COUNT];
        FloatBuffer pointsbuffer = ByteBuffer
            .allocateDirect(STRIDE * points.length)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();

        for(int i = 0; i < points.length; i ++)
        {
            pointpPosis[i*2] = transformX(points[i].x);
            pointpPosis[i*2+1] = transformY(points[i].y);
        }

        pointsbuffer.position(0);
        pointsbuffer.put(pointpPosis);

        pointsbuffer.position(0);
        GLES20.glVertexAttribPointer(mPositionLocation, POSITION_COUNT, GLES20.GL_FLOAT,
            false, STRIDE, pointsbuffer);
        GLES20.glEnableVertexAttribArray(mPositionLocation);

        float r = Color.red(color) / 255f;
        float g = Color.green(color) / 255f;
        float b = Color.blue(color) / 255f;
        float a = Color.alpha(color) / 255f;
        GLES20.glUniform4f(mColorLocation, r, g, b, a);

        GLES20.glLineWidth(linewidth);
        GLES20.glDrawArrays(GLES20.GL_LINE_STRIP, 0, points.length);

        GLES20.glDisableVertexAttribArray(mPositionLocation);
    }

    public void drawLines(PointF points[], int color, float linewidth) {
        useProgram();

        float[] pointpPosis = new float[points.length * POSITION_COUNT];
        FloatBuffer pointsbuffer = ByteBuffer
            .allocateDirect(STRIDE * points.length)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();

        for(int i = 0; i < points.length; i ++)
        {
            pointpPosis[i*2] = transformX(points[i].x);
            pointpPosis[i*2+1] = transformY(points[i].y);
        }

        pointsbuffer.position(0);
        pointsbuffer.put(pointpPosis);

        pointsbuffer.position(0);
        GLES20.glVertexAttribPointer(mPositionLocation, POSITION_COUNT, GLES20.GL_FLOAT,
            false, STRIDE, pointsbuffer);
        GLES20.glEnableVertexAttribArray(mPositionLocation);

        float r = Color.red(color) / 255f;
        float g = Color.green(color) / 255f;
        float b = Color.blue(color) / 255f;
        float a = Color.alpha(color) / 255f;
        GLES20.glUniform4f(mColorLocation, r, g, b, a);

        GLES20.glLineWidth(linewidth);
        GLES20.glDrawArrays(GLES20.GL_LINES, 0, points.length);

        GLES20.glDisableVertexAttribArray(mPositionLocation);
    }

    private static final String VERTEX = "attribute vec4 a_Position;\n" +
            "\n" +
            "void main() {\n" +
            "    gl_Position = a_Position;\n" +
            "}";

    private static final String FRAGMENT = "precision mediump float;\n" +
            "\n" +
            "uniform vec4 u_Color;\n" +
            "\n" +
            "void main() {\n" +
            "    gl_FragColor = u_Color;\n" +
            "}";

}
