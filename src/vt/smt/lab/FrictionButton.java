package vt.smt.lab;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;


public class FrictionButton extends vt.smt.lab.Button {
    private PhysicalObject frictionObj;
    public FrictionButton(Vector2f pos, PhysicalObject frictionObj){
        super(pos,"img/tire.png");
        commitButton.setText("Установить коэффициент трения!");
        frame.setTitle("Коэффициент трения");
        this.frictionObj = frictionObj;
        this.getSprite().setOrigin(32,32);
    }
    @Override
    public void call() {
        dataTextArea.setText(Float.toString(frictionObj.getWeight()));
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
        commitButton.addActionListener(e->{
            try {
                frictionObj.setFrictionCoefficient(Float.parseFloat(dataTextArea.getText()));
            }catch (Exception parseException){
                System.out.println();
                parseException.printStackTrace();
            }
        });
        frame.toFront();
        frame.setVisible(true);
    }

}
