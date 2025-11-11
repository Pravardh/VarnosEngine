import Events.EventListener;

import java.awt.*;

public class CoinItem extends GameObject implements EventListener {
   private CollisionComponent collisionComponent;

    public CoinItem(ID id, int width, int height, String name) {
        super(id, width, height, name);
        collisionComponent = (CollisionComponent)this.addComponent(new CollisionComponent(this, false, true));
        if(collisionComponent == null){
            System.out.println("Collision component is null");
        }
    }

    @Override
    public void onEvent() {
        System.out.println("Overlapping!!!");
        Player player = GameObject.getPlayer();
        if(player != null){
            player.addScore();
        }
        GameObject.destroy(this);

    }




    @Override
    public void start(){
        collisionComponent.eventPublisher.addListener(this);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }

}

