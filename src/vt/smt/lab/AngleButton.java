package vt.smt.lab;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

/**
 * Created by semitro on 01.06.17.
 */
public class AngleButton extends vt.smt.lab.Button {
    private PhysicalObject angleOwner;
    public AngleButton(Vector2f pos,PhysicalObject angleOwner){
        super(pos,"img/angle.png");
        this.angleOwner = angleOwner;
        frame.setTitle("Установить угол (в градусах)");
        commitButton.setText("Установить угол!");
    }
    @Override
    public void call() {
        dataTextArea.setText(Float.toString(angleOwner.getSprite().getRotation()));
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
        commitButton.addActionListener(e->{
            try {
                angleOwner.getSprite().setRotation(Float.parseFloat(dataTextArea.getText()));
            }catch (Exception parseException){
                System.out.println();
                parseException.printStackTrace();
            }
        });
        frame.toFront();
        frame.setVisible(true);
    }
}
