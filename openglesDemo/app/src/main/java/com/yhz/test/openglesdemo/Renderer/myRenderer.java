package com.yhz.test.openglesdemo.Renderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;

import com.yhz.test.openglesdemo.opengl.LineProgram;
import com.yhz.test.openglesdemo.opengl.PointProgram;
import com.yhz.test.openglesdemo.opengl.TriangleProgram;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class myRenderer implements GLSurfaceView.Renderer {
    private Context mContext;

    private PointProgram mPointProgram = null;
    private LineProgram  mLineProgram = null;
    private TriangleProgram mTriangleProgram = null;

    private int mViewportWidth;
    private int mViewportHeight;

    public myRenderer(Context context){
        mContext = context;

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        mViewportWidth = dm.widthPixels;
        mViewportHeight = dm.heightPixels;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glViewport(0,0,mViewportWidth,mViewportHeight);
        GLES20.glClearColor(0,0,0,0);

        if (mPointProgram == null) {
            mPointProgram = new PointProgram(mContext, mViewportWidth, mViewportHeight);
        }

        if(mLineProgram == null){
            mLineProgram = new LineProgram(mContext, mViewportWidth, mViewportHeight);
        }

        if(mTriangleProgram == null){
            mTriangleProgram = new TriangleProgram(mContext,mViewportWidth,mViewportHeight);
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClearColor(0,0,0,1.f);

        PointF p1 = new PointF(mViewportWidth*0.2f,mViewportHeight*0.8f);
        PointF p2 = new PointF(mViewportWidth*0.8f,mViewportHeight*0.4f);
        PointF arr[] = {p1,p2};
        mLineProgram.drawLines(arr,Color.WHITE,8.f);


        p1 = new PointF(mViewportWidth*0.5f,mViewportHeight*0.2f);
        p2 = new PointF(mViewportWidth*0.2f,mViewportHeight*0.7f);
        PointF p3 = new PointF(mViewportWidth*0.8f,mViewportHeight*0.7f);

        PointF triangleArr[] = {p1,p2,p3};
        mTriangleProgram.drawTriangle(triangleArr,Color.BLUE);

        mPointProgram.draw(new PointF(mViewportWidth*0.5f,mViewportHeight*0.5f), Color.RED,50.f);

    }
}
