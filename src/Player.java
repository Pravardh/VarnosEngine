import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Math.Transform;
import Math.Vector2;

public class Player extends GameObject {

    public Player(ID id, int width, int height, String name) {
        super(id, width, height, name);

        this.addComponent(new CollisionComponent(this, true));
        this.addComponent(new PlayerMovementComponent(this, 0.5f, 10f));
    }
    @Override
    public void start() {
        System.out.println("Player started");

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void end(){
        System.out.println("Player ended");

    }

}
