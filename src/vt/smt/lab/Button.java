package vt.smt.lab;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by semitro on 11.05.17.
 */

public abstract class Button {
    private Sprite sprite;
    private Texture texture;
    protected JFrame frame;
    protected JButton commitButton;
    protected JTextArea dataTextArea;
    public Button(Vector2f position, String pathToTexture){
        frame = new JFrame("Настроечки");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));
        frame.setSize(new Dimension(200,92));
        dataTextArea = new JTextArea();
        dataTextArea.setVisible(true);
        frame.add(dataTextArea);
        commitButton = new JButton("Применить");
        frame.add(commitButton);

        commitButton.setVisible(true);
        texture = new Texture();
        try {
            texture.loadFromFile(Paths.get(pathToTexture));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Картинка кнопки " + pathToTexture + " не найдена");
        }
        sprite = new Sprite(texture);
        sprite.setPosition(position);
    }
    public abstract void call();
    public Sprite getSprite(){
        return sprite;
    }
    public void render(RenderWindow w){
        w.draw(sprite);
    }

}
