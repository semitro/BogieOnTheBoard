package vt.smt.lab;

import org.jsfml.system.Vector2f;

/**
 * Created by semitro on 01.06.17.
 */
public class Speedometr extends Timer {
    private Bogie bogie;
    private Board board;
    public Speedometr(Vector2f pos, Bogie bogie, Board board){
        super(pos);
        this.board = board;
        this.bogie = bogie;
    }

    @Override
    public void update(float currentTime) {

        this.time.setString(getTimeView(PhysicalEngine.getSpeed(bogie,board,currentTime,0)) + " м/c");
    }
}
