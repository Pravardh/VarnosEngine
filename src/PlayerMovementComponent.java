import Math.*;
import java.awt.event.KeyEvent;

public class PlayerMovementComponent implements GameObjectComponent {

    private final GameObject parent;
    private Transform transform;


    public float velX = 0, velY = 0;
    public float speed = 5.0f;
    public float jumpForce = 10.0f;
    public float gravity = 0.5f;
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

        if (pressedKey == KeyEvent.VK_D || pressedKey == KeyEvent.VK_RIGHT){
            velX = speed;
        }
        else if (pressedKey == KeyEvent.VK_A || pressedKey == KeyEvent.VK_LEFT){
            velX = -speed;
        }


        if ((pressedKey == KeyEvent.VK_SPACE || pressedKey == KeyEvent.VK_W) && !falling)
        {
            velY = -jumpForce;
            jumping = true;
            falling = true;
        }


        if ((releasedKey == KeyEvent.VK_D || releasedKey == KeyEvent.VK_RIGHT) ||
                (releasedKey == KeyEvent.VK_A || releasedKey == KeyEvent.VK_LEFT))
        {
            velX = 0;
        }


        if (falling) {
            velY += gravity;
            if (velY > max_vel_y) {
                velY = max_vel_y;
            }
        }

        Vector2 position = transform.getPosition();

        position.x += velX;
        position.y += velY;

        if (position.x < 0) position.x = 0;
        if (position.x + parent.width > Game.WIDTH) position.x = Game.WIDTH - parent.width;

        transform.setPosition(position);

        System.out.println("Position: " + position.x + ", " + position.y);
    }

    @Override
    public void end() {
    }
}