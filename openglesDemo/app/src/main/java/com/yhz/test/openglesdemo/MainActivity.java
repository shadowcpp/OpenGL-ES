package com.yhz.test.openglesdemo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.yhz.test.openglesdemo.Renderer.myRenderer;

public class MainActivity extends Activity {
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new myRenderer(this));


        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
