package vt.smt.lab;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;


public class SettingsButton extends vt.smt.lab.Button {
    private Bogie bogie;
    public SettingsButton(Vector2f position, Bogie bogie){
        super(position,"img/weight.png");
        commitButton.setText("Установить вес!");

        this.bogie = bogie;
        frame.setTitle("Введите массу тележки (в кг)");

    }

    public void call(){
        dataTextArea.setText(Float.toString(bogie.getWeight()));
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
        frame.toFront();
        commitButton.addActionListener(e->{
            try {
                float weight = Float.parseFloat(dataTextArea.getText());
                bogie.setWeight(weight);
            }catch (Exception parseException){
                parseException.printStackTrace();
            }
        });
        frame.setVisible(true);
    }

}
