import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public static LinkedList<GameObject> objects = new LinkedList<>();

    public void tick(){
        for (GameObject temp : objects) {
            temp.tick();
        }
    }

    public void render(Graphics g){

        for (GameObject temp : objects) {
            temp.render(g);
        }

    }

    public void addObject(GameObject object){

        objects.add(object);
    }

    public void removeObject(GameObject object){

        objects.remove(object);
    }

}
