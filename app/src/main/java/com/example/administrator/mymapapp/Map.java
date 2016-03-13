package com.example.administrator.mymapapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class Map extends  Container {

     Paint paint;
    Bitmap Resmap;
    Bitmap map;
    int mapHeight;
    int mapWidth;
    float focusX;
    float focusY;
    int MaxX;
    Context context ;
    Matrix matrix;
    float rateX = 1;
    float rateY = 1;
    List<building> buildingList;


    Map(Resources res,int graphicPath,int mapWidth,int mapHeight,float focusX,float focusY,Context context) throws IOException {
        paint = new Paint();

        this.focusX = focusX;
        this.focusY = focusY;
        Resmap = BitmapFactory.decodeResource(res,graphicPath);

        map = Bitmap.createScaledBitmap(Resmap, Resmap.getWidth(), Resmap.getHeight(), true);
        this.mapHeight = Resmap.getHeight();
        this.mapWidth = Resmap.getWidth();
        this.context = context;
        matrix=new Matrix();
        buildingList = new ArrayList<>();


    }
    public void translate(float transX,float transY){
        matrix.setScale(rateX, rateY, 0, 0);
        matrix.postTranslate(focusX += transX, focusY += transY);
        System.out.println("X:  " + focusX);
        System.out.println("Y:  " + focusY);

//        focusX += transX;
//        focusY += transY;

    }
    public void Scale(double Mulx, double Muly,float FocusX,float FocusY) throws IOException {
            matrix.setScale(rateX *= Mulx, rateY *= Muly, 0, 0);
            matrix.postTranslate(focusX += (float) ((focusX - FocusX) * (Mulx - 1)), focusY += (float) ((focusY - FocusY) * (Muly - 1)));

//            map = Bitmap.createScaledBitmap(Resmap,mapWidth = (int) (mapWidth*Mulx),mapHeight = (int) (mapHeight*Muly),true);
//            focusX += (float) ((focusX-FocusX)*(Mulx-1));
//            focusY += (float) ((focusY-FocusY)*(Muly-1));

//            map = revitionImageSize(graphicPath,mapWidth = (int) (mapWidth*Mulx),mapHeight = (int) (mapHeight*Muly));




    }

    public Matrix getMatrix() {
        return matrix;
    }
    //    private static Bitmap big(Bitmap bitmap,double Mulx, double Muly) {
//        Matrix matrix = new Matrix();
//        matrix.postScale((float)Mulx,(float)Muly); //长和宽放大缩小的比例
//        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//        return resizeBmp;
//    }
//
//    private static Bitmap small(Bitmap bitmap,double Mulx, double Muly) {
//        Matrix matrix = new Matrix();
//        matrix.postScale((float)Mulx,(float)Muly); //长和宽放大缩小的比例
//        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//        return resizeBmp;
//    }

    public void recycle(){
        if(map!=null){
            map.recycle();
        }
    }


    @Override
    public void childrenView(Canvas canvas) {
//        canvas.drawBitmap(map,focusX,focusY,paint);
        canvas.drawBitmap(map, matrix, paint);
    }

    public void addBuilding(building building){
        buildingList.add(building);
    }

    public List<building> getBuildingList() {
        return buildingList;
    }

    @Override
    public void draw(Canvas canvas, MatrixValue matrixValue) {
        super.draw(canvas, new MatrixValue(rateX,rateY,focusX,focusY));
    }
    public  boolean  ifbBigEnough(){
        if(rateX > 2.5){
            return true;
        }
        else
            return false;
    }
    public  boolean ifSmallEnough(){
        if(rateX <0.1){
            return true;
        }else {
            return false;
        }
    }

}
