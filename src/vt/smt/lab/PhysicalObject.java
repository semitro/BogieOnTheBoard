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
    //protected float speedX = 0;
    //protected float speedY = 0;
//    protected float a;
//    protected float frictionCoefficient = 0;

    //  Скорость вдоль направления
    protected double u = 0;
    PhysicalObject(Vector2f position, int width, int height,float angle){
        loadTextureImage(width,height);
        this.sprite = new Sprite(texture,new IntRect(new Vector2i(0,0),new Vector2i(width,height)));
        this.sprite.setPosition(position);
        this.sprite.setRotation(angle);
        System.out.println(sprite.getRotation());
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
      //  sprite.rotate(0.01f);
        sprite.setPosition(sprite.getPosition().x +(float)(deltaTime*u*Math.sin(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR,
                + sprite.getPosition().y + (float)(deltaTime*u*Math.cos(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR );
//        sprite.move((float)(deltaTime*u*Math.sin(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR,
//                (float)(deltaTime*u*Math.cos(Math.toDegrees(180 - sprite.getRotation())))*Transformer.PIXEL_IN_METR);

    }
    public void render(RenderWindow window){
        window.draw(sprite);
    }
    protected abstract void loadTextureImage(int width,int height);
}
