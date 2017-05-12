package vt.smt.lab;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import java.nio.file.Paths;
import java.util.concurrent.FutureTask;

/**
 * Created by semitro on 05.05.17.
 */
public class World extends Thread implements RightFlagOwner, Restartable{
    private RenderWindow window;
    private Sprite fone;
    private Board board;
    private Bogie bogie;
    private Timer mainClock = new Timer(new Vector2f(330,320));
    private Flag leftFlag;
    private Flag rightFlag;
    static final float g = 9.81908f;
    private PhysicalEngine engine = new PhysicalEngine();
    private float currentTime = 0;
    // Кнопки снизу, двигающие флажки или перезапускающие мир
    private SettingsButton settingsButton;
    private RestartButton restartButton;
    public World(){
        super();
        window = new RenderWindow(new VideoMode(900,400),"BogieOnTheBoard", WindowStyle.CLOSE);
        board = new Board(new Vector2f(25,200),800,20,(float)Math.toDegrees(Math.asin(1/78.0)));
        bogie = new Bogie(new Vector2f(20,50),60,22,(float)Math.toDegrees(Math.asin(1/78.0)));
        settingsButton = new SettingsButton(new Vector2f(250,window.getView().getSize().y - 100),this);
        restartButton = new RestartButton(new Vector2f( 100,window.getView().getSize().y - 80),this);
        leftFlag = new Flag(board,0.210f);
        rightFlag = new Flag(board,0.505f);
        settingsButton.setMinDistanceM(leftFlag.getDistanceM());
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
            currentTime += clock.getElapsedTime().asMilliseconds();
            update(clock.getElapsedTime().asSeconds());
            clock.restart();
            render();
            window.display();
        }
    }
    public void setRightFlagPosition(float distanceM){
        rightFlag.setPosition(distanceM,board);
    }

    @Override
    public float getFlagDistance() {
        return rightFlag.getDistanceM();
    }

    private void handleEvent(Event event){
        if(event.type.equals(Event.Type.CLOSED))
            window.close();
        else

        if(event.type.equals(Event.Type.MOUSE_BUTTON_PRESSED)) {
            if (restartButton.getSprite().getGlobalBounds().contains(Mouse.getPosition().x, Mouse.getPosition().y)) {
                restartButton.call();}
             else
            if (settingsButton.getSprite().getGlobalBounds().contains(Mouse.getPosition().x, Mouse.getPosition().y))
                settingsButton.call();
        }
    }

    @Override
    public void restart() {
        currentTime = 0;
        bogie.reset(
                new Vector2f(
                    board.getSprite().getPosition().x,
                        board.getSprite().getPosition().y - board.getSprite().getGlobalBounds().height
                ),
                board.getSprite().getRotation()
        );
    }

    private void update(float deltaTime){
       // engine.update(bogie,board,deltaTime);
        bogie.update(deltaTime,board);
        board.update(currentTime);
        if(PhysicalEngine.getTimeFlag(board,leftFlag)*1000 > currentTime)
            leftFlag.update(currentTime);
        if(PhysicalEngine.getTimeFlag(board,rightFlag)*1000 > currentTime)
            rightFlag.update(currentTime);
        mainClock.update(currentTime);
    }
    private void render(){
        window.draw(fone);
        settingsButton.render(window);
        restartButton.render(window);
        leftFlag.render(window);
        rightFlag.render(window);
        mainClock.draw(window);
        board.render(window);
        bogie.render(window);
    }
}
