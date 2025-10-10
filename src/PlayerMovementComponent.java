
import Math.*;

import java.awt.event.KeyEvent;

public class PlayerMovementComponent implements GameObjectComponent {

    private GameObject parent;
    private Transform transform;

    public float velX = 0, velY = 0;
    public float gravity = 0.1f;
    public float max_vel_y = 10;

    public boolean falling = true;
    public boolean jumping = false;

    public PlayerMovementComponent(GameObject parent, float gravity, float max_vel_y){
        this.parent = parent;
        this.gravity = gravity;
        this.max_vel_y = max_vel_y;
    }

    @Override
    public void start() {

        if(parent != null){
            transform = parent.getTransform();
        }

        System.out.println("MovementComponent started");
    }

    @Override
    public void tick() {
        int pressedKey = InputReader.getLastKeyPressed();
        int releasedKey = InputReader.getLastKeyReleased();

        if (pressedKey == KeyEvent.VK_D || pressedKey == KeyEvent.VK_RIGHT)
            velX = 5;
        if (pressedKey == KeyEvent.VK_A || pressedKey == KeyEvent.VK_LEFT)
            velX = -5;

        if ((pressedKey == KeyEvent.VK_SPACE || pressedKey == KeyEvent.VK_W) && !jumping)
        {
            GameObject.destroy(parent);
            velY = -10; // Initial upward velocity
        }

        if (releasedKey == KeyEvent.VK_D || releasedKey == KeyEvent.VK_RIGHT)
            velX = 0;
        if (releasedKey == KeyEvent.VK_A || releasedKey == KeyEvent.VK_LEFT)
            velX = 0;

        Vector2 position = transform.getPosition();

        position.x += velX;
        position.y += velY;

        if (falling) {
            velY += gravity;
            if (velY > max_vel_y) {
                velY = max_vel_y;
            }
        }

        float groundY = Game.HEIGHT - 50;

        if (position.y + parent.height >= groundY) {
            position.y = groundY - parent.height;
            velY = 0;
            falling = false;
            jumping = false;
        } else {
            falling = true;
        }

        // Boundary checks (Horizontal)
        if (position.x < 0) position.x = 0;
        if (position.x + parent.height > Game.WIDTH) position.x = Game.WIDTH - parent.height;

        transform.setPosition(position);
    }

    @Override
    public void end() {

    }
}
