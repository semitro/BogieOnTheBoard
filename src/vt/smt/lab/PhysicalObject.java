package vt.smt.lab;


import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public abstract class PhysicalObject {
    protected Sprite sprite;
    protected Texture texture = new Texture();
    // Скорость измеряется в МЕТРАХ в секунду, не пикселях!
    protected float speedX = 0;
    protected float speedY = 0;
    protected float frictionCoefficient = 0;

    PhysicalObject(Vector2f position, int width, int height){
        loadTextureImage(width,height);
        this.sprite = new Sprite(texture,new IntRect(new Vector2i(0,0),new Vector2i(width,height)));
        this.sprite.setPosition(position);
        speedY = 0f;
        speedX = 0f;
    }
    public void setSpeed(float speedX, float speedY){
        this.speedX = speedX;
        this.speedY = speedY;
    }
    public Vector2f getSpeed(){
        return new Vector2f(speedX,speedY);
    }
    public void update(float deltaTime){
        sprite.move(deltaTime*speedX*Transformer.PIXEL_IN_METR,-deltaTime*speedY*Transformer.PIXEL_IN_METR);
    }
    public void render(RenderWindow window){
        window.draw(sprite);
    }
    protected abstract void loadTextureImage(int width,int height);
}
