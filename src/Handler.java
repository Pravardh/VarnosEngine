import java.awt.Graphics;
import java.util.LinkedList;
import Math.*;
public class Handler {

    public static final LinkedList<GameObject> objects = new LinkedList<>();
    private static final LinkedList<GameObject> objectsToDestroy = new LinkedList<>();
    private CollisionSystem collisionSystem = new CollisionSystem();
    private PlayerMovementComponent player;

    public void tick() {
        for (GameObject temp : objects) {
            temp.tick();
        }

        collisionSystem.update(objects);

        for (GameObject temp : objectsToDestroy) {
            objects.remove(temp);
        }
        objectsToDestroy.clear();
    }



    public void render(Graphics g) {
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
