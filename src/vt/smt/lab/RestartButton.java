package vt.smt.lab;

import org.jsfml.system.Vector2f;

/**
 * Created by semitro on 11.05.17.
 */
public class RestartButton extends vt.smt.lab.Button {
    private Restartable restarter;
    public RestartButton(Vector2f position, Restartable restarter){
        super(position,"img/repeat.png");
        this.restarter = restarter;
        commitButton.addActionListener(e->restarter.restart());
    }

    @Override
    public void call() {
        restarter.restart();
        //frame.setVisible(true);
    }
}
