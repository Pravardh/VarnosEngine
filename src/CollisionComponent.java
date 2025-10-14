import Math.*;

import java.awt.*;

public class CollisionComponent implements GameObjectComponent {

    public GameObject gameObject;
    private Vector2 scale;
    private Transform parentTransform;
    private Rectangle rectangle;

    public CollisionComponent(GameObject gameObject){
        this.gameObject = gameObject;

    }

    @Override
    public void start() {
        Transform transform = gameObject.getTransform();
        if(transform != null){
            parentTransform = transform;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void end() {

    }

    public Rectangle getBounds(){
        return new Rectangle((int) parentTransform.getPosition().x, (int)parentTransform.getPosition().y, (int)parentTransform.getScale().x, (int)parentTransform.getScale().y);
    }
}
