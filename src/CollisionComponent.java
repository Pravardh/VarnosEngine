import Events.EventPublisher;
import Math.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CollisionComponent implements GameObjectComponent {

    public GameObject gameObject;

    public EventPublisher eventPublisher;


    private Transform parentTransform;

    private boolean onGround = false;
    private boolean canOverlap = false;
    private boolean isOverlapping = false;
    private boolean atCieling = false;
    private boolean atWall = false;
    private List<GameObject> colliders = new LinkedList<>();
    private boolean shouldDebug;

    public CollisionComponent(GameObject gameObject){
        this.eventPublisher = new EventPublisher();
        this.gameObject = gameObject;

    }

    public CollisionComponent(GameObject gameObject, boolean shouldDebug, boolean canOverlap){
        this.eventPublisher = new EventPublisher();
        this.gameObject = gameObject;
        this.shouldDebug = shouldDebug;
        this.canOverlap = canOverlap;
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

        if(!canOverlap){
            colliders.add(other);
            if(shouldDebug){
                System.out.println(gameObject.getTransform().getPosition());
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
        else{
            if(mtv.y != 0 || mtv.x != 0){
                isOverlapping = true;

                if(shouldDebug){
                    System.out.println("Overlapping rn");
                }

                eventPublisher.triggerEvent();

            }
        }

        if(mtv.y == 0 && mtv.x == 0){
            onGround = false;
            atCieling = false;
            atWall = false;
            isOverlapping = false;
            return;
        }



    }

    public void registerOverlap(GameObject other, Vector2 mtv){

        eventPublisher.triggerEvent();

        }




        public boolean isOnGround(){
        return onGround;
    }
    public boolean isAtCieling(){
        return atCieling;
    }
    public boolean isOverlapping() { return isOverlapping; }
    public boolean canOverlap() { return canOverlap; }

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
