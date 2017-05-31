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

//    protected float a;

    //  Скорость вдоль направления
    protected double u = 0;
    protected float weight;
    protected float frictionCoefficient = 0;

    protected Vector2f startPosition;
    PhysicalObject(Vector2f position, int width, int height,float angle){
        startPosition = position;
        loadTextureImage(width,height);
        this.sprite = new Sprite(texture,new IntRect(new Vector2i(0,0),new Vector2i(width,height)));
        this.sprite.setPosition(position);
        this.sprite.setRotation(angle);
    }
    public Sprite getSprite(){
        return sprite;
    }
    public void setSpeed(double u){
        this.u = u;
    }
    public double getSpeed(){
        return u;
    }
    public void update(float deltaTime){
//        sprite.rotate(1.4f);
        sprite.setPosition(sprite.getPosition().x +(float)(deltaTime*u*Math.sin(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR,
                + sprite.getPosition().y + (float)(deltaTime*u*Math.cos(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR );
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getFrictionCoefficient() {
        return frictionCoefficient;
    }

    public void setFrictionCoefficient(float frictionCoefficient) {
        this.frictionCoefficient = frictionCoefficient;
    }

    public void render(RenderWindow window){
        window.draw(sprite);
    }
    protected abstract void loadTextureImage(int width,int height);
}
