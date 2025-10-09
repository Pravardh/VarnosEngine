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

    // Define player dimensions
    private static final int PLAYER_WIDTH = 32;
    private static final int PLAYER_HEIGHT = 64;

    public Player(ID id, Handler handler, int startX, int startY) {
        super(id);
        this.handler = handler;

        // --- Setting Dimensions and Initial Position ---
        this.width = PLAYER_WIDTH;
        this.height = PLAYER_HEIGHT;
        GameObject.spawn(this, new Vector2(startX, startY), Vector2.zero(), Vector2.one());
    }

    @Override
    public void tick() {
        Vector2 position = transform.getPosition();

        position.x += velX;
        position.y += velY;

        // 2. Apply Gravity
        if (falling) {
            velY += gravity;
            // Limit falling speed
            if (velY > MAX_VEL_Y) {
                velY = MAX_VEL_Y; // Use MAX_VEL_Y (float) directly
            }
        }

        // Check if player hits ground
        // Ground is drawn at Game.HEIGHT - 50. Collision happens when
        // the player's bottom edge (position.y + height) hits the ground's top edge (Game.HEIGHT - 50).
        // The current check is: position.y >= Game.HEIGHT - 50 - height
        float groundY = Game.HEIGHT - 50;

        if (position.y + height >= groundY) {
            // Snap player position exactly to the ground line
            position.y = groundY - height;
            velY = 0;
            falling = false;
            jumping = false;
        } else {
            // The character is in the air
            falling = true;
        }

        // Boundary checks (Horizontal)
        if (position.x < 0) position.x = 0;
        if (position.x + width > Game.WIDTH) position.x = Game.WIDTH - width;

        transform.setPosition(position);
    }

    void collisionCheck(){

    }

    @Override
    public void render(Graphics g) {
        // Placeholder rendering: a blue square
        g.setColor(Color.BLUE);
        // Use the defined width and height properties
        g.fillRect((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        // Use the defined width and height properties
        return new Rectangle((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }
}

class Collider{


}
