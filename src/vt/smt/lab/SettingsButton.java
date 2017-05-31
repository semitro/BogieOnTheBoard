package vt.smt.lab;

import javafx.scene.control.*;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by semitro on 11.05.17.
 */
public class SettingsButton extends vt.smt.lab.Button {
    private RightFlagOwner flag;
    public SettingsButton(Vector2f position,RightFlagOwner flag){
        super(position,"img/Button.png");
        this.flag = flag;
    }

    public void setMinDistanceM(float minDistanceM) {
        this.minDistanceM = minDistanceM;
    }
    private float minDistanceM = 0;


    public void call(){
        frame.setTitle("Позиция правого флажка");
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
        frame.toFront();
        dataTextArea.setText(Float.toString(flag.getFlagDistance()));
        commitButton.addActionListener(e->{
            try {
                float distance = Float.parseFloat(dataTextArea.getText());
                if(distance >= minDistanceM)
                    flag.setRightFlagPosition(distance);
            }catch (Exception parseException){
                parseException.printStackTrace();
            }
        });
        //frame.pack();
        frame.setVisible(true);
    }

}
