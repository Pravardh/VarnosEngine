import java.awt.Graphics;
import java.util.LinkedList;
import Math.*;
public class Handler {

    public static final LinkedList<GameObject> objects = new LinkedList<>();
    private static final LinkedList<GameObject> objectsToDestroy = new LinkedList<>();

    private PlayerMovementComponent player;

    public void tick(){

        for(GameObject temp : objects){
            CollisionComponent comp = temp.getComponent(CollisionComponent.class);
            if(comp != null){
                comp.clearCollisionState();
            }
        }

        for (GameObject temp : objects) {
                CollisionComponent compA = temp.getComponent(CollisionComponent.class);

                if(compA != null){
                for(int j = objects.indexOf(temp) + 1; j < objects.size(); j++){
                    GameObject obj = objects.get(j);
                    CollisionComponent compB = obj.getComponent(CollisionComponent.class);

                    if(compB != null){
                        if(compA.getBounds().intersects(compB.getBounds())){

                            resolveCollision(compA, compB);
                        } 
                    }
                }

            }
            temp.tick();

        }



        for (GameObject temp : objectsToDestroy) {
            objects.remove(temp);
        }

        objectsToDestroy.clear();
    }
    private void resolveCollision(CollisionComponent compA, CollisionComponent compB) {

        if (compA == null || compB == null) return;

        GameObject objA = compA.gameObject;
        GameObject objB = compB.gameObject;
        //System.out.println("Obj A: " + objA.name +  " Obj B: "  + objB.name);
        java.awt.Rectangle rectA = compA.getBounds();
        java.awt.Rectangle rectB = compB.getBounds();

        float centerDistX = (float) Math.abs(rectA.getCenterX() - rectB.getCenterX());
        float centerDistY = (float) Math.abs(rectA.getCenterY() - rectB.getCenterY());

        float halfWidths = (float) (rectA.getWidth() / 2 + rectB.getWidth() / 2);
        float halfHeights = (float) (rectA.getHeight() / 2 + rectB.getHeight() / 2);

        float overlapX = halfWidths - centerDistX;
        float overlapY = halfHeights - centerDistY;

        Vector2 mtv = new Vector2(0, 0);

        if (overlapX < overlapY) {
            float sign = (rectA.getCenterX() < rectB.getCenterX()) ? -1 : 1;
            mtv.x = overlapX * sign;

        } else {
            float sign = (rectA.getCenterY() < rectB.getCenterY()) ? -1 : 1;
            mtv.y = overlapY * sign;
        }

        objA.getTransform().getPosition().x += mtv.x;
        objA.getTransform().getPosition().y += mtv.y;

        compA.registerCollision(objB, mtv);

        Vector2 invertedMtv = new Vector2(-mtv.x, -mtv.y);

        compB.registerCollision(objA, invertedMtv);
    }

    public void render(Graphics g){

        for (GameObject temp : objects) {
            temp.render(g);
        }

    }

    public static void addObject(GameObject object){

        objects.add(object);
    }

    public static void removeObject(GameObject object){

        objects.remove(object);
    }

    public static void safeDestroy(GameObject object){
        objectsToDestroy.add(object);
    }
}
