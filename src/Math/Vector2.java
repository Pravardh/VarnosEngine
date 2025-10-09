package Math;

public class Vector2 {

    public float x, y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public static float distance(Vector2 first, Vector2 second){
        return (float) Math.sqrt(Math.pow(second.x - first.x, 2) + Math.pow(second.y - first.y, 2)); //Euclidian distance formula d = √[(x₂ - x₁)² + (y₂ - y₁)²]
    }

    public static Vector2 one(){
        return new Vector2(1,1);
    }

    public static Vector2 zero(){
        return new Vector2(0,0);
    }

    public float distance(Vector2 other){
        return (float) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2)); //Euclidian distance formula d = √[(x₂ - x₁)² + (y₂ - y₁)²]
    }

    public float magnitude(){
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public float dot(Vector2 other){
        return x * other.x + y * other.y;
    }
    public Vector2 normalized(){
        float mag = magnitude();
        return new Vector2(x / mag, y / mag);
    }

    public void add(Vector2 other){
        this.x += other.x;
        this.y += other.y;
    }
}
