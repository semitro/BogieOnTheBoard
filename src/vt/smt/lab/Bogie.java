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
    public Bogie(Vector2f position, int width, int height,float angle){
        super(position, width, height,angle);
    }
    float time = 0;

    public void reset(Vector2f position, float angle) {
        time = 0;
        sprite.setPosition(position);
        sprite.setRotation(angle);
    }

    public void update(float currentTime, Board board){
        time += currentTime;
        double pos = PhysicalEngine.getPosition(this,board,time,0);
        sprite.setPosition(board.getSprite().getPosition().x - Transformer.PIXEL_IN_METR*(float)(pos* Math.sin(Math.toRadians(90 - (180 - sprite.getRotation())))),
                board.getSprite().getPosition().y - board.getSprite().getTextureRect().height +
                        Transformer.PIXEL_IN_METR*(float)(pos* Math.cos(Math.toRadians(90 - (180 - sprite.getRotation()))))
                );
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
