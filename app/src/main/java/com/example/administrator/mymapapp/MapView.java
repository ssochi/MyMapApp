package com.example.administrator.mymapapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date; import android.text.format.DateFormat;


/**
 * Created by Administrator on 2016/1/16.
 */
public class MapView extends SurfaceView implements Callback {

    Timer timer = null;
    Container container ;
    Map map;

    building gongXueGuan;
    building chengShiGuangChang;
    building pengYuanGongYu;
    building daXueShengHuiGuan;
    building XinZhenLou;
    building XiCaoChang;
    building TiYuGuan;
    building XiaoHuaYuan;
    building DiLiuChanTing;
    building XiaoLeiYiHaoLou;
    building XiaoLeiErHaoLou;
    building DiYiCanTing;
    building HuiMingCanTing;
    building XiaoLeiSiHaoLou;
    building DiSanCanTing;
    building XiaoWeiShengShuo;
    building DiZhiLou;
    building JiChuLou;
    building YuYanLou;
    building TuShuGuan;
    building ZhongHeLou;
    building ShiYanLou;
    building DongQing;



    Point focus;
    float WindowHeight ;
    float WindowWidth;
    float CanvasScaleX = 1;
    float CanvasScaleY = 1;
    float CanvasTranslateX = 0;
    float CanvasTranslateY = 0;

    double ScaleBigger =  1.05;
    double ScaleSmaller =  0.95;
    float distance = 0;
    float lastDistance = 0;
    Point startPoint = new Point() ;
    Point lastPoint = new Point();
    Boolean locktransform = false;
    int i = 1;
    int Value_moveTime = 25;
    int moveTime = Value_moveTime;
    Boolean firstTrans = true;
    boolean firstInit = true;
    boolean shouldClick = true;
    boolean shouldScale = true;
    SimpleDateFormat df ;
    Date curDate; //PROCESSING
    Date endDate ;

    BulidingGroup bulidingGroup;






    public MapView(Context context,AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    private void init()  {
        getWindowHeightAndWidth();
        container = new Container();
        focus = new Point(0,0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
            map = new Map(getResources(),R.mipmap.emptymap,(int)WindowWidth,(int)WindowHeight,focus.getX(),focus.getY(),getContext());
            bulidingGroup = new BulidingGroup();

            pengYuanGongYu = new building(getResources(),R.mipmap.text,1350,450,185,295,25,getContext(),"鹏\n远\n公\n寓",true);
            daXueShengHuiGuan = new building(getResources(),R.mipmap.text,308,250,1400,1015,0,getContext(),"大学生会馆",true);
            gongXueGuan = new building(getResources(),R.mipmap.text,770,120,2100,860,0,getContext(),"工学馆",true);
            XinZhenLou = new building(getResources(),R.mipmap.text,330,100,2330,1110,0,getContext(),"行政楼",true);
            chengShiGuangChang = new building(getResources(),R.mipmap.text,330,665,2320,1260,0,getContext(),"沉\n思\n广\n场",true);
            XiCaoChang = new building(getResources(),R.mipmap.text,440,1700,1790,1270,0,getContext(),"西\n操\n场",true);
            TiYuGuan = new building(getResources(),R.mipmap.text,250,430,1320,2300,0,getContext(),"体育馆",true);
            XiaoHuaYuan = new building(getResources(),R.mipmap.text,520,480,2500,2010,0,getContext(),"小花园",true);
            DiLiuChanTing = new building(getResources(),R.mipmap.text,190,190,2700,2600,0,getContext(),"六餐",true);
            XiaoLeiYiHaoLou = new building(getResources(),R.mipmap.text,269,105,3123,1328,0,getContext(),"校内一号楼",true);
            XiaoLeiErHaoLou = new building(getResources(),R.mipmap.text,257,100,3464,1338,0,getContext(),"校内二号楼",true);
            DiYiCanTing = new building(getResources(),R.mipmap.text,262,120,3124,1122,0,getContext(),"第一餐厅",true);
            HuiMingCanTing = new building(getResources(),R.mipmap.text,151,43,3249,1054,0,getContext(),"回民餐厅",true);
            XiaoLeiSiHaoLou = new building(getResources(),R.mipmap.text,219,94,3472,1108,0,getContext(),"校内四号楼",true);
            DiSanCanTing = new building(getResources(),R.mipmap.text,216,94,3873,1168,0,getContext(),"第三餐厅",true);
            XiaoWeiShengShuo = new building(getResources(),R.mipmap.text,316,56,4257,1184,0,getContext(),"校卫生所",true);
            YuYanLou = new building(getResources(),R.mipmap.text,242,85,4253,1321,0,getContext(),"语言楼",true);
            DiZhiLou = new building(getResources(),R.mipmap.text,64,292,4093,1315,0,getContext(),"地\n址\n楼",true);
            JiChuLou = new building(getResources(),R.mipmap.text,139,319,4099,1699,0,getContext(),"基\n础\n楼",true);
            TuShuGuan = new building(getResources(),R.mipmap.text,198,253,4351,1688,0,getContext(),"图书馆",true);
            ZhongHeLou = new building(getResources(),R.mipmap.text,103,884,4590,1330,0,getContext(),"综\n合\n楼",true);
            ShiYanLou = new building(getResources(),R.mipmap.text,235,133,4475,2259,0,getContext(),"实验楼",true);







        } catch (IOException e) {
            e.printStackTrace();
        }





        add(pengYuanGongYu,"pengYuanGongYu");
        add(daXueShengHuiGuan,"daXueShengHuiGuan");
        add(gongXueGuan,"gongXueGuan");
        add(XinZhenLou,"XinZhenLou");
        add(chengShiGuangChang,"chengShiGuangChang");
        add(XiCaoChang,"XiCaoChang");
        add(TiYuGuan,"TiYuGuan");
        add(XiaoHuaYuan,"XiaoHuaYuan");
        add(DiLiuChanTing,"DiLiuChanTing");
        add(XiaoLeiYiHaoLou,"XiaoLeiYiHaoLou");
        add(XiaoLeiErHaoLou,"XiaoLeiErHaoLou");
        add(DiYiCanTing,"DiYiCanTing");
        add(HuiMingCanTing,"HuiMingCanTing");
        add(XiaoLeiSiHaoLou,"XiaoLeiSiHaoLou");
        add(DiSanCanTing,"DiSanCanTing");
        add(XiaoWeiShengShuo,"XiaoWeiShengShuo");
        add(YuYanLou,"YuYanLou");
        add(DiZhiLou,"DiZhiLou");
        add(JiChuLou,"JiChuLou");
        add(TuShuGuan,"TuShuGuan");
        add(ZhongHeLou,"ZhongHeLou");
        add(ShiYanLou,"ShiYanLou");


        container.addChildrenView(map);
    }

    private void add(building building,String index) {
        building.setIndex(index);
        map.addChildrenView(building);
        map.addBuilding(building);
        bulidingGroup.addList(index);
    }

    public void draw(){
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        container.draw(canvas, new MatrixValue());
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(firstInit){
            init();
            firstInit = false;
        }

        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        draw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    public  void  startTimer(){
        if (timer == null){
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    float dy = lastPoint.getY() - startPoint.getY();
                    float dx = lastPoint.getX() - startPoint.getX();
                    Canvas canvas = getHolder().lockCanvas();
                    map.translate(-dx * moveTime / Value_moveTime/3*2, -dy *moveTime/Value_moveTime/3*2);
                    canvas.drawColor(Color.WHITE);
                    getContainer().draw(canvas, new MatrixValue());
                    getHolder().unlockCanvasAndPost(canvas);
                    moveTime --;
                    if(moveTime<=1){
                        moveTime = Value_moveTime;
                        stopTimer();
                    }
                }


            },0,1);
        }
    }

    private void minusMoveTime() {
        moveTime -- ;
    }

    public  void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public void getWindowHeightAndWidth() {
        WindowHeight = this.getHeight();
        WindowWidth = this.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    //关于触屏的一系列操作
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            stopTimer();
            shouldClick = true;


        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            lastPoint.equal(startPoint);
            if(event.getPointerCount()>1){
                firstTrans = true;

                distance = (float)Math.sqrt (Math.pow((event.getX(1)-event.getX(0)),2) + Math.pow((event.getY(1)-event.getY(0)),2)) ;
                try {
                    if(ScaleDraw(distance, lastDistance, (event.getX(0) + event.getX(1)) / 2, (event.getY(0) + event.getY(1)) / 2)){
                        shouldClick = false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lastDistance = distance;
            }else
            {
                startPoint.setX(event.getX());
                startPoint.setY(event.getY());


                    if(firstTrans){
                        lastPoint.equal(startPoint);
                        firstTrans = false;
                    }
                    if(!locktransform){
                        if(TranslateDraw(startPoint,lastPoint)){
                             curDate = new Date(System.currentTimeMillis()); //PROCESSING
                            shouldClick = false;
                        }
                    }else {
                        locktransform = false;
                    }




            }
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
             endDate = new Date(System.currentTimeMillis());
            Point upPoint = new Point(event.getX(),event.getY());
            System.out.println(upPoint.getX() + "   " + upPoint.getY());
                if (shouldClick){
                    if(pengYuanGongYu.matrixValue.ScaleX>0.8)//图太小 不能点击
                    ifOnclick(upPoint);
                }else {
                    long diff = endDate.getTime() - curDate.getTime();
                    if(diff<100){
                        startTimer();
                    }
                }
                distance = 0;
                lastDistance = 0;
                firstTrans = true;


        }
        return true;
    }

    private boolean ifOnclick(Point startPoint) {


            for (int i=0;i<map.getBuildingList().size();i++){
                if(map.getBuildingList().get(i).onClicked(startPoint)){
                    System.out.println("click");


                    return false;
                }
            }

        return false;
    }

    private boolean TranslateDraw(Point startPoint, Point lastPoint) {
        float transX = 0;
        float transY = 0;
//        float translateValue = 35;
//        if(startPoint.getX()-lastPoint.getX()>5){
//            transX = startPoint.getX()-lastPoint.getX();
//        }if(startPoint.getX()-lastPoint.getX()<-5){
//            transX = startPoint.getX()-lastPoint.getX();
//        }if(startPoint.getY()-lastPoint.getY()>5){
//            transY = startPoint.getY()-lastPoint.getY();
//        }if(startPoint.getY()-lastPoint.getY()<-5){
//            transY = startPoint.getY()-lastPoint.getY();
//        }
        transX = startPoint.getX()-lastPoint.getX();
        transY = startPoint.getY()-lastPoint.getY();

            Canvas canvas = getHolder().lockCanvas();
            map.translate(transX,transY);
            canvas.drawColor(Color.WHITE);
            getContainer().draw(canvas, new MatrixValue());
            getHolder().unlockCanvasAndPost(canvas);



        if (transX<=5&&transY<=5&&transX>=-5&&transY>=-5){
            return  false;
        }else
            return  true;

    }

    private Container getContainer() {
        container = new Container();
        container.addChildrenView(map);
        return container;
    }

    private boolean ScaleDraw(float distance,float lastDistance,float focusX,float focusY) throws IOException {

            locktransform = true;

        if(distance - lastDistance > 5){
            if (!map.ifbBigEnough()){
                Canvas canvas = getHolder().lockCanvas();
                canvas.drawColor(Color.WHITE);
                map.Scale(ScaleBigger, ScaleBigger, focusX, focusY);
                getContainer().draw(canvas,new MatrixValue());
                getHolder().unlockCanvasAndPost(canvas);
                System.out.println("放大");
            }
            return true;
        }
        else if(distance - lastDistance < -5){
            if(!map.ifSmallEnough()){
                Canvas canvas = getHolder().lockCanvas();
                canvas.drawColor(Color.WHITE);
                map.Scale(ScaleSmaller,ScaleSmaller,focusX,focusY);
                System.out.println("缩小");
                getContainer().draw(canvas,new MatrixValue());
                getHolder().unlockCanvasAndPost(canvas);
            }
            return true;


        }
        return  false;
    }


}
