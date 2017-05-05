package vt.smt.lab;


public class PhysicalEngine {
    PhysicalEngine(){

    }
    //ГЛЕБ, ПИШИ ЗДЕСЬ
    public void update(Bogie bogie, Board board, float deltaTime){
        bogie.setSpeed(0, bogie.getSpeed().y - 9.8f*deltaTime );
        System.out.println("Тележка:  " + bogie.getSpeed().y);
    }
}
