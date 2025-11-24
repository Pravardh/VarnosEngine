import java.awt.Color;
import java.awt.Graphics;
import Math.Vector2;

public class MovingPlatform extends GameObject {

    private Vector2 velocity;
    private float range;
    private float startX;

    public MovingPlatform(int width, int height, String name, Vector2 velocity, float range) {
        super(ID.Block, width, height, name);
        this.velocity = velocity;
        this.range = range;

        this.addComponent(new CollisionComponent(this, false, false, true));
    }

    @Override
    public void start() {
        super.start();
        this.startX = transform.getPosition().x;
    }

    @Override
    public void tick() {
        super.tick();

        transform.getPosition().x += velocity.x;
        transform.getPosition().y += velocity.y;

        if (Math.abs(transform.getPosition().x - startX) > range) {
            velocity.x *= -1;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }

    public Vector2 getVelocity() {
        return velocity;
    }
}