package vt.smt.lab;

import javafx.scene.shape.Rectangle;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by semitro on 05.05.17.
 */
public class World implements RightFlagOwner, Restartable{
    private RenderWindow window;
    private Sprite fone;
    private Board board;
    private Bogie bogie;
    private Timer mainClock = new Timer(new Vector2f(490,12));
    private Flag rightFlag, leftFlag;
    static final float g = 9.81908f;
    private PhysicalEngine engine = new PhysicalEngine();
    private float currentTime = 0;
    private RestartButton restartButton;
    private PlotButton plotButton;
    private SettingsButton settingsButton;
    private FrictionButton frictionButton;
    private AngleButton angleButton;
    private ClearButton clearButton;
    //Счётчик значений для таблицы
    private ResultCounter resultCounter = new ResultCounter();
    private Speedometr speedometr;
    private SaveResultButton saveButton;
    private InboundedPlot speedPlot;
    private InboundedPlot postionPlot;

    public World(){
        window = new RenderWindow(new VideoMode(900,500),"Качение тележки с учётом трения");
        board = new Board(new Vector2f(0,270),980,80,0.75f);
        bogie = new Bogie(new Vector2f(15,50),100,40,0.75f);
        // Позиция доски там адекватно устанавливается
        this.restart();
        restartButton = new RestartButton(new Vector2f( window.getView().getSize().x - 75, 210),this);
        plotButton = new PlotButton(new Vector2f( 290,window.getView().getSize().y - 88));
        saveButton  = new SaveResultButton(new Vector2f(450,window.getView().getSize().y - 80));
        settingsButton = new SettingsButton(new Vector2f(window.getView().getSize().x - 75,10),bogie);
        frictionButton = new FrictionButton(new Vector2f(window.getView().getSize().x - 75 + 32 , 77 + 32),board);
        angleButton = new AngleButton(new Vector2f(window.getView().getSize().x - 75, 144),board);

        speedometr = new Speedometr(new Vector2f(40,12),bogie,board);
        speedPlot = new InboundedPlot(window,new Rectangle(180,60,220,145),2,10);
        speedPlot.setOyString("u,м/с");
        postionPlot = new InboundedPlot(window,new Rectangle(444,60,220,145),2,10);
        postionPlot.setOyString("s,м");
        clearButton = new ClearButton(new Vector2f(window.getView().getSize().x - 150, 10),speedPlot,postionPlot);

        try {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("img/fone.jpg"));
            fone = new Sprite();
            fone.setTexture(t);
        }catch (Exception e ){
            e.printStackTrace();
            System.out.println("Не удалось загрузить главынй фон окна");
        }
        ScheduledExecutorService plotUpdater = Executors.newScheduledThreadPool(1);
        // Обновление точек графика
        plotUpdater.scheduleAtFixedRate(()->{
                    speedPlot.add(currentTime,PhysicalEngine.getSpeed(bogie,board,currentTime,0));
                    postionPlot.add(currentTime,(float)PhysicalEngine.getPosition(bogie,board,currentTime,0f));
        },
        50, 75, TimeUnit.MILLISECONDS
        );
    }

    public void run(){
        Clock clock = new Clock();
        //main loop
        while (window.isOpen()){
			Event event = window.pollEvent();
            if(event != null)
                handleEvent(event);

            window.clear();
            currentTime += clock.getElapsedTime().asSeconds();
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
        if(event.type.equals(Event.Type.CLOSED)){
            window.close();
            System.exit(0);
        }
        else
        if(event.type.equals(Event.Type.MOUSE_BUTTON_PRESSED)) {
            if (restartButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)) {
                Random r = new Random(System.currentTimeMillis());
                Color newColor = new Color(r.nextInt()%255,r.nextInt()%255,r.nextInt()%255);
                speedPlot.setColor(newColor);
                postionPlot.setColor(newColor);
                restartButton.call();
            }
//            else
//            	if(plotButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x,Mouse.getPosition(window).y)){
//            		plotButton.call();
//            		plotButton.setValues(resultCounter.getResultList());
//            	}
            	else
            	    if(settingsButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x,Mouse.getPosition(window).y))
            	        settingsButton.call();
            	else
            	    if(frictionButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x,Mouse.getPosition(window).y))
            	        frictionButton.call();
            	else
            	    if(clearButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x,Mouse.getPosition(window).y))
            	        clearButton.call();
            	else
                    if(angleButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x,Mouse.getPosition(window).y))
                        angleButton.call();
//            if(saveButton.getSprite().getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)){
//            	resultCounter.addResult(new ResultCounter.Result(
//            			leftFlag.getDistanceM(),rightFlag.getDistanceM(),
//            			Float.parseFloat(leftFlag.getTime()),Float.parseFloat(rightFlag.getTime())));
//            	if(resultCounter.getResultList().size() > 5)
//            		resultCounter.getResultList().remove(0);
//            }
        }
    }

    @Override
    public void restart() {
        currentTime = 0;
        bogie.reset(
                new Vector2f(
                    board.getSprite().getPosition().x,
                        board.getSprite().getPosition().y -
                                bogie.getSprite().getGlobalBounds().height*(float)Math.sin(Math.toRadians(board.getSprite().getRotation()))
                ),
                board.getSprite().getRotation()
        );
    }

    private void update(float deltaTime){
        engine.update(bogie,board,deltaTime);
//    	if(bogie.getSprite().getPosition().x + bogie.getSprite().getLocalBounds().width < this.window.getSize().x)
			bogie.update(deltaTime,board);
        board.update(currentTime);

        mainClock.update(currentTime);
        frictionButton.getSprite().rotate(0.5f);
        speedometr.update(currentTime);

    }
    private void render(){
        window.draw(fone);
        speedPlot.draw();
        postionPlot.draw();
        speedometr.draw(window);
       //(board.mainClock.draw(window);
        mainClock.draw(window);
        board.render(window);
        bogie.render(window);
        restartButton.render(window);
       // plotButton.render(window);
       // saveButton.render(window);
        frictionButton.render(window);
        settingsButton.render(window);
        clearButton.render(window);
        angleButton.render(window);
    }
}
