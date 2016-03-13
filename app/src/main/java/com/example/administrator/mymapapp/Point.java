package com.example.administrator.mymapapp;

/**
 * Created by Administrator on 2016/1/16.
 */
public class Point {
    float X;
    float Y;


    Point(float X,float Y){
        this.X = X;
        this.Y = Y;
    }
    Point(){
        X = 0;
        Y = 0;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public void equal(Point in){
        setX(in.getX());
        setY(in.getY());
    }


}
