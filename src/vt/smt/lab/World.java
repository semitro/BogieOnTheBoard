package vt.smt.lab;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import java.nio.file.Paths;

/**
 * Created by semitro on 05.05.17.
 */
public class World extends Thread{
    private RenderWindow window;
    private Sprite fone;
    private Board board;
    private Bogie bogie;
    static final float g = 9.81908f;
    private PhysicalEngine engine = new PhysicalEngine();
    public World(){
        super();
        window = new RenderWindow(new VideoMode(800,600),"BogieOnTheBoard", WindowStyle.CLOSE);
        board = new Board(new Vector2f(20,500),400,10,(float) Math.toDegrees((float)Math.asin(1/78.0f)));
        bogie = new Bogie(new Vector2f(20,50),60,10,(float) Math.toDegrees((float)Math.asin(1/78.0f)));
        try {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("img/fone.jpg"));
            fone = new Sprite();
            fone.setTexture(t);
        }catch (Exception e ){
            e.printStackTrace();
            System.out.println("Не удалось загрузить главынй фон окна");
        }

    }

    @Override
    public void run(){
        Clock clock = new Clock();
        //main loop
        while (window.isOpen()){
            Event event = window.pollEvent();
            if(event != null)
                handleEvent(event);

            window.clear();
            update(clock.getElapsedTime().asSeconds());
            clock.restart();
            render();
            window.display();
        }
    }
    private void handleEvent(Event event){
        if(event.type.equals(Event.Type.CLOSED))
            window.close();
    }
    private void update(float deltaTime){
       // engine.update(bogie,board,deltaTime);
        bogie.update(deltaTime,board);
        board.update(deltaTime);
    }
    private void render(){
        window.draw(fone);
        board.render(window);
        bogie.render(window);
    }
}
