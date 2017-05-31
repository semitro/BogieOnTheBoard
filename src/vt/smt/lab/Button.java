package vt.smt.lab;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

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
        frame.setLayout(new GridLayout(1,3));
        
        frame.setSize(new Dimension(200,92));
        dataTextArea = new JTextArea();
        dataTextArea.setVisible(true);
        frame.add(dataTextArea);
        commitButton = new JButton("Применить");
        frame.add(commitButton);
        dataTextArea.setBackground(Color.GRAY);
        dataTextArea.setTabSize(10);
        dataTextArea.setForeground(Color.white);
        commitButton.setBackground(Color.DARK_GRAY);
        commitButton.setForeground(Color.lightGray);
        commitButton.setVisible(true);
        frame.setSize(new Dimension(4200,120));
        commitButton.setPreferredSize(new Dimension(420,32));
        frame.setLayout(new GridLayout(2,1));
        frame.pack();
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
    	//sprite.rotate((float)Math.random()*92.18281828459045235360f);
        return sprite;
    }
    public void render(RenderWindow w){
        w.draw(sprite);
    }

}
