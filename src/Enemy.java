public class Enemy extends GameObject{

    public Enemy(ID id, int width, int height, String name) {
        super(id, width, height, name);
        this.id = ID.Enemy;
        this.addComponent(new CollisionComponent(this, false, false));

    }

    @Override
    public void start(){


    }


}
