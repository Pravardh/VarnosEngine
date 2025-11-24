import Events.EventPublisher;
import Math.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CollisionComponent implements GameObjectComponent {

    //REMEMEBER TO CLEAN THIS UP!!!!!!!!!!!!!!!!!!!!!!

    public GameObject gameObject;
    public EventPublisher eventPublisher;
    private Transform parentTransform;

    private boolean onGround = false;
    private boolean canOverlap = false;
    private boolean isOverlapping = false;
    private boolean atCieling = false;
    private boolean atWall = false;
    private boolean isStatic = false;

    private GameObject groundObject = null;

    private List<GameObject> colliders = new LinkedList<>();
    private boolean shouldDebug;

    public CollisionComponent(GameObject gameObject){
        this(gameObject, false, false, false);
    }

    public CollisionComponent(GameObject gameObject, boolean shouldDebug, boolean canOverlap){
        this(gameObject, shouldDebug, canOverlap, false);
    }

    public CollisionComponent(GameObject gameObject, boolean shouldDebug, boolean canOverlap, boolean isStatic){
        this.eventPublisher = new EventPublisher();
        this.gameObject = gameObject;
        this.shouldDebug = shouldDebug;
        this.canOverlap = canOverlap;
        this.isStatic = isStatic;
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
        groundObject = null;
    }

    public void registerCollision(GameObject other, Vector2 mtv){
        colliders.add(other);

        if(mtv.y < 0) {
            onGround = true;
            groundObject = other;
        }
        if(mtv.y > 0) atCieling = true;
        if(mtv.x != 0) atWall = true;
    }

    public void resolve(CollisionComponent otherComp, Vector2 mtv) {
        if (this.isStatic) return;
        if (!this.canOverlap && !otherComp.canOverlap()) {
            this.parentTransform.getPosition().x += mtv.x;
            this.parentTransform.getPosition().y += mtv.y;
            registerCollision(otherComp.gameObject, mtv);
        }
        else {
            registerOverlap(otherComp.gameObject, mtv);
        }
    }

    public void registerOverlap(GameObject other, Vector2 mtv){
        eventPublisher.triggerEvent();
    }

    public boolean isOnGround(){ return onGround; }
    public boolean isAtCieling(){ return atCieling; }
    public boolean isOverlapping() { return isOverlapping; }
    public boolean canOverlap() { return canOverlap; }
    public boolean isAtWall(){ return atWall; }
    public List<GameObject> getColliders(){ return colliders; }

    public GameObject getGroundObject() { return groundObject; }

    @Override
    public void tick() {}

    @Override
    public void end() {}

    public Rectangle getBounds(){
        return new Rectangle((int) parentTransform.getPosition().x, (int)parentTransform.getPosition().y,
                (int)parentTransform.getScale().x, (int)parentTransform.getScale().y);
    }
}