import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public static final LinkedList<GameObject> objects = new LinkedList<>();
    private static final LinkedList<GameObject> objectsToDestroy = new LinkedList<>();

    private PlayerMovementComponent player;

    public void tick(){
        for (GameObject temp : objects) {
            temp.tick();

            CollisionComponent compA = temp.getComponent(CollisionComponent.class);

            if(compA != null){
                for(int j = objects.indexOf(temp) + 1; j < objects.size(); j++){
                    GameObject obj = objects.get(j);
                    CollisionComponent compB = obj.getComponent(CollisionComponent.class);

                    if(compB != null){
                        if(compA.getBounds().intersects(compB.getBounds())){

                            resolveCollision(compA.gameObject, compB.gameObject);

                        } else {

                        }
                    }
                }

            }
        }

        for (GameObject temp : objectsToDestroy) {
            objects.remove(temp);
        }

        objectsToDestroy.clear();
    }
    private void resolveCollision(GameObject objA, GameObject objB) {
        CollisionComponent compA = objA.getComponent(CollisionComponent.class);
        CollisionComponent compB = objB.getComponent(CollisionComponent.class);

        if (compA == null || compB == null) return;

        java.awt.Rectangle rectA = compA.getBounds();
        java.awt.Rectangle rectB = compB.getBounds();

        float centerDistX = (float) Math.abs(rectA.getCenterX() - rectB.getCenterX());
        float centerDistY = (float) Math.abs(rectA.getCenterY() - rectB.getCenterY());

        float halfWidths = (float) (rectA.getWidth() / 2 + rectB.getWidth() / 2);
        float halfHeights = (float) (rectA.getHeight() / 2 + rectB.getHeight() / 2);

        float overlapX = halfWidths - centerDistX;
        float overlapY = halfHeights - centerDistY;

        if (overlapX < overlapY) {
            float sign = (rectA.getCenterX() < rectB.getCenterX()) ? -1 : 1;
            float mtvX = overlapX * sign;

            objA.getTransform().getPosition().x += mtvX;

            PlayerMovementComponent playerA = objA.getComponent(PlayerMovementComponent.class);
            if (playerA != null) {
                playerA.velX = 0;

            }

        } else {
            float sign = (rectA.getCenterY() < rectB.getCenterY()) ? -1 : 1;
            float mtvY = overlapY * sign;

            objA.getTransform().getPosition().y += mtvY;

            PlayerMovementComponent playerA = objA.getComponent(PlayerMovementComponent.class);
            if (playerA != null) {
                playerA.velY = 0;

                if (sign < 0) {
                    playerA.falling = false;
                    playerA.jumping = false;
                } else { /
                    playerA.velY = 0;
                }
            }
        }
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
