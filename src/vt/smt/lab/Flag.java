package vt.smt.lab;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.nio.file.Paths;
import java.time.Clock;

/**
 * Created by semitro on 09.05.17.
 */
public class Flag {
    private Sprite sprite;
    private Texture texutre;
    private Timer clock;
    private float distanceM;
    public Flag(Board board,float distanceM){
        this.distanceM = distanceM;
        texutre = new Texture();
        try {
            texutre.loadFromFile(Paths.get("img/flag.png"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Не загрузилась текстура флага из img/flag.png");
        }
        sprite = new Sprite(texutre);
        setPosition(distanceM,board);
        clock = new Timer(new Vector2f(sprite.getPosition().x - 55, sprite.getPosition().y - 70 ));
    }
    public void setPosition(float distanceM, Board board){
        this.distanceM = distanceM;
        sprite.setRotation(board.getSprite().getRotation());
        sprite.setPosition(
                board.getSprite().getPosition().x +
                        distanceM*Transformer.PIXEL_IN_METR*(float)Math.cos(Math.toRadians(sprite.getRotation())),
                board.getSprite().getPosition().y - sprite.getGlobalBounds().height - 1 +
                        distanceM*Transformer.PIXEL_IN_METR*(float)Math.sin(Math.toRadians(sprite.getRotation()))
        );
    }
    public void update(float currentTime){
        clock.update(currentTime);
//        sprite.rotate(2f);
       
    }
    public String getTime(){
    	return clock.getTime();
    }
    public void render(RenderWindow window){
        clock.draw(window);
        window.draw(sprite);
    }

    public void stop(){
        clock.stop();
    }
    public float getDistanceM(){
        return this.distanceM;
    }
}
