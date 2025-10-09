
import javax.swing.JFrame;

class Window extends JFrame {
    public Window(int width, int height, String title, Game game) {
        // Setup basic frame properties
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Center the window
        add(game); // Add the canvas (Game instance) to the frame
        setVisible(true);
        game.start(); // Start the game loop immediately
    }
}
