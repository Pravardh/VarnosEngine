import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Math.Transform;
import Math.Vector2;
/**
 * Player.java
 *
 * Implements the player character with platformer physics (gravity, jumping)
 * and handles movement based on the KeyInput.
 */
public class Player extends GameObject {

    private Handler handler;
    public Player(ID id, Handler handler) {
        super(id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        Vector2 position = transform.getPosition();

        position.x += velX;
        position.y += velY;

        transform.setPosition(position);

        // 2. Apply Gravity
        if (falling) {
            velY += gravity;  // Remove the (int) cast here!
            // Limit falling speed
            if (velY > MAX_VEL_Y) {
                velY = (int) MAX_VEL_Y;
            }
        }

        // Check if player hits ground
        if (y >= Game.HEIGHT - 50 - height) {
            y = Game.HEIGHT - 50 - height;
            velY = 0;
            falling = false;
            jumping = false;
        } else {
            falling = true;
        }

        // Boundary checks
        if (x < 0) x = 0;
        if (x + width > Game.WIDTH) x = Game.WIDTH - width;
    }

    @Override
    public void render(Graphics g) {
        // Placeholder rendering: a blue square
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
