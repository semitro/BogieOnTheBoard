package vt.smt.lab;


import org.jsfml.system.Vector2f;

public class PhysicalEngine {
    PhysicalEngine(){

    }

    // Начальные параметры задаются в World - конструктор - ищи короче
    public static double getPosition(Bogie bogie,Board board, float time, float v0){
        return v0*time + Transformer.g*Math.cos(Math.toRadians(90 - bogie.sprite.getRotation()))*time*time/2.0f;
    }
    public static float getTimeFlag(Board board,Flag flag){
        return (float)Math.sqrt(
                2*flag.getDistanceM()/(Transformer.g*Math.sin(Math.toRadians(180 - board.getSprite().getRotation())))
        );
    }
    public void update(Bogie bogie, Board board, float deltaTime){

    }

    float t=0;
}
