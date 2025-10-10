import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    private static LinkedList<GameObject> objects = new LinkedList<>();
    private static LinkedList<GameObject> objectsToDestroy = new LinkedList<>();

    public void tick(){
        for (GameObject temp : objects) {
            temp.tick();
        }

        for (GameObject temp : objectsToDestroy) {
            objects.remove(temp);
        }

        objectsToDestroy.clear();
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
