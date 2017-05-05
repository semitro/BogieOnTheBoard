package vt.smt.lab;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by semitro on 05.05.17.
 */
public class Board extends PhysicalObject {
    public Board(Vector2f position, int width, int height, float angle){
        super(position, width, height);
        sprite.setRotation(angle);
    }

    @Override
    protected void loadTextureImage(int width,int height){
        try {
            texture.loadFromFile(Paths.get("img/board.jpg"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Не удалось создать Board");
        }
    }
}
