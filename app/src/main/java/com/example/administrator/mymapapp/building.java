package com.example.administrator.mymapapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/1/31.
 */
public class building extends Map {


    Paint paint;

    Matrix m;
    float x;
    float y;
    float width;
    float height;
    float degrees;
    MatrixValue matrixValue;
    String name;
    int timerSize = 1;
    int Timer = timerSize;
    Intent intent;
    int textSize = 50;
    boolean CanOclick ;
    String index ="";





    building(Resources res, int graphicPath, int mapWidth, int mapHeight, float focusX, float focusY, float degrees,Context context,String name,boolean CanOclick) throws IOException {
        super(res, graphicPath, mapWidth, mapHeight, focusX, focusY, context);
        map = Bitmap.createScaledBitmap(Resmap,mapWidth, mapHeight, true);
        Resmap.recycle();
        x = focusX;
        y = focusY;
        width = mapWidth;
        height = mapHeight;
        this.name = name ;
        intent = new Intent(context,buildingIntroduceActivity.class);
        intent.putExtra("name",name);

        this.degrees = degrees;
        this.CanOclick = CanOclick;
    }


//    building(Resources res,int src,int buildWidth,int buildHeight,float focusX,float focusY){
//        building = BitmapFactory.decodeResource(res,src);
//        building = Bitmap.createScaledBitmap(building, buildWidth, buildHeight, true);
//        this.focusX = focusX;
//        this.focusY = focusY;
//        paint = new Paint();
//
//    }


    public void setIndex(String index) {
        this.index = index;
        intent.putExtra("index",index);
    }

    @Override
    public void childrenView(Canvas canvas) {
//        if(matrixValue.ScaleX>2&&matrixValue.ScaleY>2)
//        if(CanOclick)
//        canvas.drawBitmap(map, m, paint);

        if(matrixValue.ScaleX > 0.8){
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setTextSize(textSize);
            Scanner sc = new Scanner(name);
            int i = 0;
            while (sc.hasNext()){
                String line = sc.nextLine();

                double args = Math.atan( height/width);

                double hypotenuse = Math.sqrt(Math.pow(width*matrixValue.ScaleX/2, 2) + Math.pow(height*matrixValue.ScaleY/2, 2));

                canvas.drawText(line, (float) (x + hypotenuse*Math.sin(Math.PI/180*(90-degrees)-args)-getTextWidth(p,line)/2), (float) (y + hypotenuse*Math.cos(Math.PI/180*(90-degrees)-args)+textSize*i),p);
                i++;
            }
        }




    }

    @Override
    public void draw(Canvas canvas, MatrixValue matrixValue) {
        this.matrixValue = matrixValue;
        m = new Matrix();

        m.setScale(matrixValue.ScaleX, matrixValue.ScaleY, 0, 0);
        m.postTranslate(focusX * matrixValue.ScaleX + matrixValue.TransX, focusY * matrixValue.ScaleY + matrixValue.TransY);
        x = focusX * matrixValue.ScaleX + matrixValue.TransX;
        y =  focusY * matrixValue.ScaleY + matrixValue.TransY;
        m.postRotate(degrees, x, y);
        super.draw(canvas, matrixValue);


        //    检测按钮区域时使用  绘制矩形

//        canvas.drawRect(x, y, x + width * matrixValue.ScaleX, y + height * matrixValue.ScaleY, p);
    }
    public Boolean onClicked(Point point){
        if(CanOclick) {
            if (ifPointOnTheRect(point)) {


                context.startActivity(intent);



                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
    private boolean ifPointOnTheRect(Point point) {
        if(degrees == 0){
            if(point.getX()>=x&&point.getX()<=x + width * matrixValue.ScaleX&&point.getY()>=y&&point.getY()<=y + height * matrixValue.ScaleY){
                return  true;
            }else {
                return  false;
            }
        }
        Point point1 = new Point(x,y);
        Point point2 = new Point(point1.getX()+(float)Math.cos(Math.PI/180*degrees)*width*matrixValue.ScaleX,point1.getY()+(float)Math.sin(Math.PI/180*degrees)*width*matrixValue.ScaleY);
        Point point3 = new Point(point1.getX()-(float)Math.cos(Math.PI/180*(90-degrees))*height*matrixValue.ScaleX,point1.getY()+(float)Math.sin((Math.PI/180*(90-degrees)))*height*matrixValue.ScaleY);
//        System.out.println(x+"     "+y);
//        System.out.println(point2.getX()+"     "+point2.getY());
//        System.out.println(point3.getX()+"     "+point3.getY());
        float b1 = (float) (point1.getY() - Math.tan(Math.PI/180*degrees)*point1.getX());
        float b2 = (float) (point1.getY() - -1/Math.tan(Math.PI/180*degrees)*point1.getX() );
        float b3 = (float) (point2.getY() - -1/Math.tan(Math.PI/180*degrees)*point2.getX());
        float b4 = (float) (point3.getY() - Math.tan(Math.PI/180*degrees)*point3.getX());
        if(point.getY()>Math.tan(Math.PI/180*degrees)*point.getX() + b1&&point.getY()>-1/Math.tan(Math.PI/180*degrees)*point.getX() + b2&&point.getY()<-1/Math.tan(Math.PI/180*degrees)*point.getX() + b3&&point.getY() < Math.tan(Math.PI/180*degrees)*point.getX() + b4)
        {
            return true;
        }
        else {
            return  false;
        }
    }
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
}
