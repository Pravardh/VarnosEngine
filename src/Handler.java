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

                            player = compA.gameObject.getComponent(PlayerMovementComponent.class);
                            if(player != null){
                                player.isColliding = true;
                                player.velX = 0;
                            }
                            System.out.println("Collision with " + compA.gameObject.name + " and " + compB.gameObject.name);
                        }else{
                            if(player != null){
                                player.isColliding = false;
                                player = null;
                            }
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
