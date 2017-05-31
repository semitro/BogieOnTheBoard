package vt.smt.lab;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class PlotWindow {
	PlotWindow(){
		Texture t = new Texture();
		try{
			t.loadFromFile(Paths.get("img/plotFone1.jpg"));
		}catch(IOException e){
			System.out.println("Не удалось загрузить Img/plotFone1.jpg");
			e.printStackTrace();
		}
		fone = new Sprite(t);
		
		 font = new Font();
		 
	        try {
	            font.loadFromFile(Paths.get("img/font.ttf"));
	        }catch (Exception e){
	            e.printStackTrace();
	            System.out.println("Шрифт для подписи не загрузился");
	        }
	        textLabel = new Text("0",font);
	        textLabel.scale(0.5f,0.5f);
	}
	private Sprite fone;
	private static final int height_ = 400;
	private static final float space = 30f;
	public static final String Rectangle2D = null;
	public void start(){
		RenderWindow w = new RenderWindow(new VideoMode(600,400),"График");
		//Random rand = new Random(Thread.currentThread().getPriority());

		//mainLoop
		while(w.isOpen()){
			Event event = w.pollEvent();
			if(event != null && event.type.equals(Event.Type.CLOSED))
				w.close();
			
			w.clear();
			w.draw(fone);
			drawGrid(w,space);
			drawLinesAlias(w,"c*c","м");
			w.draw(line);
			w.display();
		}
	}
	private RectangleShape line = new RectangleShape();
	public void initLine(float x1,float y1, float x2,float y2){
		// Cоответствие в пикселях ставится только для одного игрека, поэтому только для него слетует перевернуть оську
		line.setPosition(x1 + space-1,height_ - y1 - space-3);
		line.setRotation(- (float)Math.toDegrees(Math.atan((y2-y1)/(x2-x1))) );
		line.setSize(new Vector2f(1000f,5f));
		line.setFillColor(Color.WHITE);
	}
	// Разметка сетки
	private Text textLabel;
	private Font font;
	
	private void drawGrid(RenderWindow w, float space){
		RectangleShape rect = new RectangleShape();
		rect.setSize(new Vector2f(1000,1));
		rect.setFillColor(new Color(124,185,185,228));
		rect.setPosition(0,0);
		rect.setRotation(90);
		boolean odd = false;
		//w.draw(textLabel);
		while(rect.getPosition().x < w.getSize().x){
			w.draw(rect);
	
			rect.setPosition(new Vector2f(rect.getPosition().x + space,rect.getPosition().y));
			if(!odd){
				textLabel.setPosition(rect.getPosition().x + textLabel.getGlobalBounds().width/2 - 4, w.getSize().y - 20);
				textLabel.setString(
						Float.toString((float)rect.getPosition().x/30));
			}
			odd = !odd;
			w.draw(textLabel);
		}
		rect.setPosition(0,w.getSize().y);
		rect.setRotation(0);
		
		while(rect.getPosition().y >= 0 ){
			w.draw(rect);
			rect.setPosition(new Vector2f(rect.getPosition().x, rect.getPosition().y-space));
			// Каждую вторую
			if(!odd){
				// Ставим туда же, где лини (это вверх)
				textLabel.setPosition(8 ,  rect.getPosition().y-space - textLabel.getGlobalBounds().width/2);
				// Устанавливаем значение
				textLabel.setString(
						Float.toString(
								(w.getSize().y - (float)rect.getPosition().y)/150 ));
			}
			
			odd = !odd;
			w.draw(textLabel);
		}
		
		rect.setPosition(new Vector2f(0,w.getSize().y-space));
		rect.setFillColor(Color.BLUE);
		w.draw(rect);
		rect.setPosition(new Vector2f(space,0));
		rect.setRotation(90);
		w.draw(rect);
	}
	private void drawLinesAlias(RenderWindow w, String xAlias, String yAlias){
		Text xText = new Text();
		xText.setFont(font);
		xText.setString(xAlias);
		xText.setScale(0.75f, 0.75f);
		xText.setPosition(w.getSize().x - 10 - xText.getGlobalBounds().width, w.getSize().y - space - xText.getGlobalBounds().height);
		w.draw(xText);
		
		xText.setString(yAlias);
		xText.setPosition(space + 2, xText.getGlobalBounds().height - 4);
		w.draw(xText);
		
		xText.setString("График зависимости \n\n 2(x2 - x1) от (t2^2 - t1^2)");
		xText.setPosition(3*space ,space);
		w.draw(xText);
	}
	
}
