package vt.smt.lab;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class PhysicalEngine {
    PhysicalEngine(){

    }

    // Начальные параметры задаются в World - конструктор - ищи короче
    public static double getPosition(Bogie bogie,Board board, float time, float v0){
        //return v0*time + Transformer.g*Math.cos(Math.toRadians(90 - bogie.sprite.getRotation()))*time*time/2.0f;
        /*
                А может, тележка и не должна ехать?
         */
        if(!willRun(bogie,board))
            return 0;

        return v0*time + Transformer.g *
                (Math.sin(Math.toRadians(bogie.sprite.getRotation()))
                        - board.getFrictionCoefficient()*Math.cos(Math.toRadians(bogie.getSprite().getRotation()))
                )* time*time/2.0f;

    }
    public static float getSpeed(Bogie bogie, Board board, float time, float v0){
        if(!willRun(bogie,board))
            return 0;

        return (float)(v0*time + Transformer.g *
                (Math.sin(Math.toRadians(bogie.sprite.getRotation()))
                        - board.getFrictionCoefficient()*Math.cos(Math.toRadians(bogie.getSprite().getRotation()))
                )* time);

    }
    public static float getTimeFlag(Board board,Flag flag){
        return (float)Math.sqrt(
                2*flag.getDistanceM()/(Transformer.g*Math.sin(Math.toRadians(180 - board.getSprite().getRotation())))
        );
    }
    public void update(Bogie bogie, Board board, float deltaTime){

    }
    private static boolean willRun(Bogie bogie,Board board){
        return cos(toRadians(bogie.getSprite().getRotation()))*board.getFrictionCoefficient() <
                sin(toRadians(bogie.getSprite().getRotation()));
    }
    float t=0;
}
