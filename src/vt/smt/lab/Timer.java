package vt.smt.lab;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

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

    public String getTime(){
    	return time.getString();
    }
    public void draw(RenderWindow window){
//        window.draw(sprite);
        window.draw(time);
    }
    protected boolean wasStopped = false;
    public void update(float currentTime){
        if(wasStopped)
            return;
        try{
        time.setString(getTimeView(currentTime));
       // time.move(new Vector2f(0.01f,-0.1f));
        }catch(Exception e){
        	//e.printStackTrace();
        }
    }
    protected String getTimeView(float time){
        try {
            return (String) (Float.toString(time)).subSequence(0, 5);
        }catch (IndexOutOfBoundsException e){
            return Float.toString(time);
        }
    }
    public void stop(){
        wasStopped = true;
    }
}
