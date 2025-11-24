import java.awt.Rectangle;
import java.util.List;
import Math.Vector2;

public class CollisionSystem {

    public void update(List<GameObject> objects) {
        for (GameObject obj : objects) {
            CollisionComponent comp = obj.getComponent(CollisionComponent.class);
            if (comp != null) comp.clearCollisionState();
        }

        for (int i = 0; i < objects.size(); i++) {
            GameObject objA = objects.get(i);
            CollisionComponent compA = objA.getComponent(CollisionComponent.class);

            if (compA == null) continue;

            for (int j = i + 1; j < objects.size(); j++) {
                GameObject objB = objects.get(j);
                CollisionComponent compB = objB.getComponent(CollisionComponent.class);

                if (compB == null) continue;

                if (compA.getBounds().intersects(compB.getBounds())) {
                    handleIntersection(compA, compB);
                }
            }
        }
    }

    private void handleIntersection(CollisionComponent compA, CollisionComponent compB) {
        Rectangle rectA = compA.getBounds();
        Rectangle rectB = compB.getBounds();

        float centerDistX = Math.abs((float) rectA.getCenterX() - (float) rectB.getCenterX());
        float centerDistY = Math.abs((float) rectA.getCenterY() - (float) rectB.getCenterY());

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

        compA.resolve(compB, mtv);

        Vector2 inverseMtv = new Vector2(-mtv.x, -mtv.y);
        compB.resolve(compA, inverseMtv);
    }
}