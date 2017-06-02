package vt.smt.lab;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;


public class SettingsButton extends vt.smt.lab.Button {
    private Bogie bogie;
    public SettingsButton(Vector2f position, Bogie bogie){
        super(position,"img/gravity.png");
        commitButton.setText("Установить ускорение свободного падения!");

        this.bogie = bogie;
        frame.setTitle("Желаете изменить ускорение свободного падения?");

    }

    public void call(){
        dataTextArea.setText(Float.toString(Transformer.g));
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
        frame.toFront();
        commitButton.addActionListener(e->{
            try {
                Transformer.g = Float.parseFloat(
                        dataTextArea.getText());
            }catch (Exception parseException){
                parseException.printStackTrace();
            }
        });
        frame.setVisible(true);
    }

}
