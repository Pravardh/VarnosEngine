import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Math.Transform;
import Math.Vector2;

public class Player extends GameObject {

    public Player(ID id, int width, int height) {
        super(id, width, height);

        this.addComponent(new PlayerMovementComponent(this, 0.5f, 10f));
    }
    @Override
    public void start() {
        System.out.println("Player started");

    }


    @Override
    public void end(){
        System.out.println("Player ended");

    }

}
