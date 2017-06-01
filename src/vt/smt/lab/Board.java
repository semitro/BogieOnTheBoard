package vt.smt.lab;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by semitro on 05.05.17.
 */
public class Board extends PhysicalObject {
    public Board(Vector2f position, int width, int height, float angle){
        super(position, width, height,angle);
    }


    @Override
    protected void loadTextureImage(int width,int height){
        try {
            texture.loadFromFile(Paths.get("img/smallRails.jpg"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Не удалось создать Board");
        }
    }
}
