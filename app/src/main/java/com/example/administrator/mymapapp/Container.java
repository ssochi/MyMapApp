package com.example.administrator.mymapapp;

import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class Container {
    private List<Container> children = null;
    Matrix matrix;
    Container(){
        children = new ArrayList<Container>();
        matrix = new Matrix();
    }

    public void draw(Canvas canvas,MatrixValue matrixValue) {
        canvas.save();
        childrenView(canvas);
        for (Container c : children) {
            c.draw(canvas,matrixValue);
        }
        canvas.restore();
    }

    public void childrenView(Canvas canvas) {

    }

    public void addChildrenView(Container child) {
        children.add(child);
    }

    public void removeChildrenView(Container child) {
        children.remove(child);
    }
}
