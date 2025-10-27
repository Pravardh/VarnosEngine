import java.awt.Color;
import java.awt.Graphics;
import Math.Vector2;

public class Block extends GameObject {

    public Block(int width, int height, String name) {
        super(ID.Block, width, height, name);

        this.addComponent(new CollisionComponent(this));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }
}