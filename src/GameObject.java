import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import Math.Transform;
import Math.Vector2;
/**
 * GameObject.java
 *
 * The abstract base class for all entities in the game.
 * It holds fundamental properties like position, velocity, and identification.
 */
public abstract class GameObject {

    protected Transform transform;
    protected int width, height;
    protected ID id;


    protected float velX, velY;
    protected float gravity = 0.5f;
    protected final float MAX_VEL_Y = 10;
    protected boolean falling = true;
    protected boolean jumping = false;

    public GameObject(ID id) {
        this.transform = new Transform(Vector2.zero(), Vector2.zero(), Vector2.one());
        this.id = id;
    }

    public static void spawn(GameObject gameObject, Vector2 position, Vector2 rotation, Vector2 scale){
        gameObject.transform.setPosition(position);
        gameObject.transform.setRotation(rotation);
        gameObject.transform.setScale(scale);
    }

    /**
     * Updates the object's state (position, AI, animation frame).
     */
    public abstract void tick();

    // The render method is now abstract in the base class (assuming you removed the default implementation)
    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    // Keeping x and y here is redundant with Transform, but keeping the setters/getters
    // consistent with the old structure if other classes rely on them.
    public int getX() { return (int)transform.getPosition().x; }
    public void setX(int x) { transform.getPosition().x = x; }

    public int getY() { return (int)transform.getPosition().y; }
    public void setY(int y) { transform.getPosition().y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }

    public float getVelX() { return velX; }
    public void setVelX(float velX) { this.velX = velX; }

    public float getVelY() { return velY; }
    public void setVelY(float velY) {
        this.velY =  Math.min(velY, MAX_VEL_Y);
    }

    public boolean isFalling() { return falling; }
    public void setFalling(boolean falling) { this.falling = falling; }

    public boolean isJumping() { return jumping; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }

}
