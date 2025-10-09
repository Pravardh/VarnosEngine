import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private final Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){

            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.Player){

                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
                    tempObject.setVelX(5);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
                    tempObject.setVelX(-5);

                if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) && !tempObject.isJumping()) {
                    tempObject.setJumping(true);
                    tempObject.setVelY(-10); // Initial upward velocity
                }
            }

            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(1); // Simple exit
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Player) {
                // Stop horizontal movement when keys are released
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
            }
        }


    }
}
