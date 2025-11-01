import Math.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CollisionComponent implements GameObjectComponent {

    public GameObject gameObject;
    private Transform parentTransform;

    private boolean onGround = false;
    private boolean atCieling = false;
    private boolean atWall = false;
    private List<GameObject> colliders = new LinkedList<>();
    private boolean shouldDebug = false;

    public CollisionComponent(GameObject gameObject){
        this.gameObject = gameObject;

    }

    public CollisionComponent(GameObject gameObject, boolean shouldDebug){
        this.gameObject = gameObject;
        this.shouldDebug = shouldDebug;
    }

    @Override
    public void start() {
        Transform transform = gameObject.getTransform();

        if(transform != null){
            parentTransform = transform;
        }
    }

    public void clearCollisionState(){
        onGround = false;
        atCieling = false;
        atWall = false;
        colliders.clear();
    }

    public void registerCollision(GameObject other, Vector2 mtv){
        colliders.add(other);
        if(shouldDebug){
            System.out.println("alo");

            System.out.println(mtv.y);
        }
        if(mtv.y < 0){
            onGround = true;
        }if(mtv.y > 0){
            atCieling = true;
        }
        if(mtv.x != 0){
            atWall = true;
        }
    }
    public boolean isOnGround(){
        return onGround;
    }
    public boolean isAtCieling(){
        return atCieling;
    }
    public boolean isAtWall(){
        return atWall;
    }
    public List<GameObject> getColliders(){return colliders;}

    @Override
    public void tick() {

    }

    @Override
    public void end() {

    }

    public Rectangle getBounds(){
        return new Rectangle((int) parentTransform.getPosition().x, (int)parentTransform.getPosition().y,
                (int)parentTransform.getScale().x, (int)parentTransform.getScale().y);
    }
}
