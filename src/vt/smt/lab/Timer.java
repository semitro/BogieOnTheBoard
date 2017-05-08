package vt.smt.lab;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by semitro on 08.05.17.
 */
public class Timer {
    protected Text time;
    protected Font font;
    protected Sprite sprite;
    protected Texture texture;
    public Timer(Vector2f position){
        loadTexture();
       sprite = new Sprite(texture);
       sprite.setPosition(position);
        font = new Font();try {
            font.loadFromFile(Paths.get("img/font.ttf"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Шрифт в таймере не загрузился");
        }
        time = new Text("00:00", font);
        time.setPosition(sprite.getPosition());
       // time.set
        time.setScale(new Vector2f(2,2));
        time.setColor(Color.BLACK);
    }

    public void draw(RenderWindow window){
//        window.draw(sprite);
        window.draw(time);
    }
    protected boolean wasStopped = false;
    public void update(float currentTime){
        if(wasStopped)
            return;
        time.setString(getTimeView(currentTime));
    }
    protected String getTimeView(float time){
        return new String(Float.toString(time/1000f));
    }
    public void stop(){
        wasStopped = true;
    }
    protected void loadTexture(){
        texture = new Texture();
        try {
            texture.loadFromFile(Paths.get("img/clock.png"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Не удалось загрузить текстуру часов");
        }
    }

}
