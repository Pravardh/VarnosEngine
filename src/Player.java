

public class Player extends GameObject {
    private int score = 0;
    public Player(ID id, int width, int height, String name) {
        super(id, width, height, name);

        this.addComponent(new CollisionComponent(this, false, false));
        this.addComponent(new PlayerMovementComponent(this, 0.5f, 10f));
    }
    @Override
    public void start() {
        System.out.println("Player started");

    }

    public void tick() {
        CollisionComponent collision = getComponent(CollisionComponent.class);

        if (collision != null && collision.isOnGround()) {
            GameObject floor = collision.getGroundObject();
            if (floor instanceof MovingPlatform) {
                MovingPlatform platform = (MovingPlatform) floor;

                this.transform.getPosition().x += platform.getVelocity().x;
                this.transform.getPosition().y += platform.getVelocity().y;
            }

            this.transform.getPosition().y += 1;
        }

        super.tick();

        // System.out.println("Collected coins: " + score);
    }
    @Override
    public void end(){
        System.out.println("Player ended");

    }

    public void addScore(){
        score++;
    }

}
