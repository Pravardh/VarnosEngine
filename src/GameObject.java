import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;

import Math.Transform;
import Math.Vector2;

public abstract class GameObject {

    protected Transform transform;
    protected int width, height;
    protected ID id;


    protected LinkedList<GameObjectComponent> components = new LinkedList<>();

    public GameObject(ID id, int width, int height) {
        this.width = width;
        this.height = height;
        this.transform = new Transform(Vector2.zero(), Vector2.zero(), new Vector2(width, height));
        this.id = id;
    }

    public static void spawn(GameObject gameObject, Vector2 position, Vector2 rotation, Vector2 scale){
        gameObject.transform.setPosition(position);
        gameObject.transform.setRotation(rotation);
        gameObject.transform.setScale(scale);

        gameObject.start();
        Handler.objects.add(gameObject);
    }

    public static void destroy(GameObject gameObject){
        gameObject.end();
        Handler.objects.remove(gameObject);

    }

    public void addComponent(GameObjectComponent component){
        components.add(component);
        component.start();
    }

    public <T extends GameObjectComponent> T getComponent(Class<T> componentType) {
        for (GameObjectComponent component : components) {
            if (componentType.isInstance(component)) {
                return componentType.cast(component);
            }
        }
        return null;
    }

    public void start(){

    }

    public void tick(){

        for (GameObjectComponent component : components) {
            component.tick();

        }
    }
    public void end(){

    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)transform.getPosition().x, (int)transform.getPosition().y, width, height);
    }


    public int getX() { return (int)transform.getPosition().x; }
    public void setX(int x) { transform.getPosition().x = x; }

    public int getY() { return (int)transform.getPosition().y; }
    public void setY(int y) { transform.getPosition().y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }

    public Transform getTransform() { return transform; }


}
