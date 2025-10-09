package Math;

public class Transform {

    private Vector2 position;
    private Vector2 rotation;
    private Vector2 scale;

    public Transform(Vector2 position, Vector2 rotation, Vector2 scale){
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Vector2 getPosition() { return position; }
    public void setPosition(Vector2 position) { this.position = position; }

    public Vector2 getRotation() { return rotation; }
    public void setRotation(Vector2 rotation) { this.rotation = rotation; }

    public Vector2 getScale() { return scale; }
    public void setScale(Vector2 scale) { this.scale = scale; }


    public void translate(Vector2 translation){
        this.position.add(translation);
    }


}

