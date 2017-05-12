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
    public Timer(Vector2f position){
        font = new Font();
        try {
            font.loadFromFile(Paths.get("img/font.ttf"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Шрифт в таймере не загрузился");
        }
        time = new Text("00:00", font);
        time.setPosition(position);
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
}
