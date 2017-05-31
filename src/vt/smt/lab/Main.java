package vt.smt.lab;
public class Main {

    public static void main(String argv[]){
    	
    	Thread.currentThread().setPriority(8);
        World world = new World();
        world.run();
    }
}
