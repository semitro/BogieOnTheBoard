package vt.smt.lab;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by semitro on 05.05.17.
 */
public class Bogie extends PhysicalObject{
    public Bogie(Vector2f position, int width, int height){
        super(position, width, height);
    }
    @Override
    protected void loadTextureImage(int width,int height){
        try {
            texture.loadFromFile(Paths.get("img/bogie.jpg"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Не удалось создать Bogie");
        }
    }
}
