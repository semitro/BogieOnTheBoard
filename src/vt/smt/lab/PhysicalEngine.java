package vt.smt.lab;


import org.jsfml.system.Vector2f;

public class PhysicalEngine {
    protected static double g = 9.819;
    PhysicalEngine(){

    }
    //ГЛЕБ, ПИШИ ЗДЕСЬ

    // Начальные параметры задаются в World - конструктор - ищи короче
    public static double getPosition(Bogie bogie,Board board, float time, float v0){

        return v0*time + g*Math.cos(Math.toRadians(90 - bogie.sprite.getRotation()))*time*time/2.0f;
    }
    public void update(Bogie bogie, Board board, float deltaTime){
//
//        board.getSprite().getRotation();
//        board.getSprite().setPosition(45);
//        board.getSprite().rotate(25);
          //  bogie.setSpeed(0.2);
         // bogie.setSpeed(bogie.getSpeed() + deltaTime*g*Math.sin(Math.toDegrees(90 - bogie.getSprite().getRotation())));
//        float  sin=(float)Math.sin(Math.toRadians(board.getSprite().getRotation()));// n -количество подложенных палетиков, k-расстояние между столбиками. задается не здесь
//        float cos=(float)Math.cos(Math.toRadians(board.getSprite().getRotation()));
//        float g= 9.81908f;
//        t=t + deltaTime;
//        bogie.getSprite().setPosition(new Vector2f(g*t*t/2.0f*sin,
//                g*t*t/2.0f*sin + board.getSprite().getPosition().y - board.getSprite().getTextureRect().height));
//        //bogie.setSpeed(bogie.getSpeed().x + g*cos*deltaTime, bogie.getSpeed().y - g*sin*deltaTime );


//        if (g*t*t/2.0f*cos< 0.4)
//        System.out.println(t);
    }

    float t=0;
}
