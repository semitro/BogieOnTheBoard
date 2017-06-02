package vt.smt.lab;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

import java.util.LinkedList;
import java.util.List;

/**
 * Встроенный график
 */
public class InboundedPlot implements Clearable{
    private RenderWindow window;
    private Rectangle plotArea;
    private List<Vector2f> points = new LinkedList<>();
    private List<Pair<Integer,Color>> changeColor = new LinkedList<>();
    private float maxTime;
    private float maxU;
    private CircleShape pointModel;
    private float oneMetrInPixels;
    private float oneSecondInPixels;
    private float space = 20;
    public InboundedPlot(RenderWindow w, Rectangle plotArea, float maxTime, float maxU){
        this.window = w;
        this.plotArea = plotArea;
        this.maxTime = maxTime;
        this.maxU = maxU;
        pointModel = new CircleShape(1);
        oneMetrInPixels = space/ maxU;
        oneSecondInPixels = space/maxTime;
        resetChangleColor();
    }
    public void clear(){
        resetChangleColor();
        points.clear();
    }
    private void resetChangleColor(){
        changeColor.clear();
        changeColor.add(new Pair<>(0,Color.BLACK));
    }
    public void add(float time, float u){
        if(points.size() < 2048)
            points.add(new Vector2f(time,u));
        else
            System.out.println("Слишком много точек. Пожалуйста, очистите графики");
    }
    public void setColor(Color color){
        changeColor.add(new Pair<>(points.size(),color));
    }
    public float getOneMetrInPixels() {
        return oneMetrInPixels;
    }

    public void setOneMetrInPixels(float oneMetrInPixels) {
        this.oneMetrInPixels = oneMetrInPixels;
    }

    public float getOneSecondInPixels() {
        return oneSecondInPixels;
    }

    public void setOneSecondInPixels(float oneSecondInPixels) {
        this.oneSecondInPixels = oneSecondInPixels;
    }

    public void draw(){
        drawGrid(space);
        int nextColor = 0;
        for(int i = 0; i< points.size();i++){
            Vector2f currentPoint = points.get(i);

            if(nextColor < changeColor.size() && i > changeColor.get(nextColor).getKey()) {
                pointModel.setFillColor(changeColor.get(nextColor).getValue());
                nextColor++;
            }
            pointModel.setPosition((float)(plotArea.getX()) + currentPoint.x*oneSecondInPixels,
                    (float)(plotArea.getY() + plotArea.getHeight() - currentPoint.y*oneSecondInPixels));
            window.draw(pointModel);
        }
    }
    private void drawGrid(float space){
        RectangleShape rect = new RectangleShape();
		rect.setSize(new Vector2f((float)plotArea.getHeight(), 1));
		rect.setFillColor(new Color(124,185,185,100));
		rect.setPosition((float)plotArea.getX(),(float)plotArea.getY());

		rect.setRotation(90);
		boolean odd = false;
		//w.draw(textLabel);
		while(rect.getPosition().x < plotArea.getWidth() + plotArea.getX()){
			window.draw(rect);
			rect.setPosition(new Vector2f(rect.getPosition().x + space,rect.getPosition().y));

//			if(!odd){
//				textLabel.setPosition(rect.getPosition().x + textLabel.getGlobalBounds().width/2 - 4, w.getSize().y - 20);
//				textLabel.setString(
//						Float.toString((float)rect.getPosition().x/30));
//			}
			odd = !odd;
//			window.draw(textLabel);
		}
		rect.setPosition((float)plotArea.getX(),(float)plotArea.getY() + (float)plotArea.getHeight());
		rect.setSize(new Vector2f((float)plotArea.getWidth(),1));
		rect.setRotation(0);

		while(rect.getPosition().y > plotArea.getY() ){
			window.draw(rect);
			rect.setPosition(new Vector2f(rect.getPosition().x, rect.getPosition().y-space));
			// Каждую вторую
//			if(!odd){
//				// Ставим туда же, где лини (это вверх)
//				textLabel.setPosition(8 ,  rect.getPosition().y-space - textLabel.getGlobalBounds().width/2);
//				// Устанавливаем значение
//				textLabel.setString(
//						Float.toString(
//								(w.getSize().y - (float)rect.getPosition().y)/150 ));
//			}
//
			odd = !odd;
//			w.draw(textLabel);
		}
//		rect.setPosition(new Vector2f((float)plotArea.getX(),(float)plotArea.getY() + (float)plotArea.getHeight()-space));
//		rect.setFillColor(Color.BLUE);
//		window.draw(rect);
//		//rect.setSize(new Vector2f((int)plotArea.getWidth() , 1));
//        rect.setSize(new Vector2f((int)plotArea.getHeight(),1));
//        rect.setPosition(new Vector2f(space + (float)plotArea.getX(),(float)plotArea.getY()));
//		rect.setRotation(90);
//		window.draw(rect);
    }
}
