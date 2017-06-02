package vt.smt.lab;

import org.jsfml.system.Vector2f;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by semitro on 02.06.17.
 */
public class ClearButton extends vt.smt.lab.Button {
    private List<Clearable> toClean = new LinkedList<>();
    public ClearButton(Vector2f pos, Clearable ... clearables){
        super(pos,"img/clear.png");
        toClean.addAll(Arrays.asList(clearables));
    }

    @Override
    public void call() {
        toClean.forEach(e->e.clear());
    }
}
