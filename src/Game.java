import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 * Game.java
 *
 * The central class that initializes the game, manages the window,
 * and contains the main game loop (the heart of the engine).
 * This class extends Canvas to allow for custom rendering.
 */
public class Game extends Canvas implements Runnable {

    // Engine Constants
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 12 * 9; // Standard 4:3 aspect ratio
    private static final String TITLE = "Varnos";

    private Thread thread;
    private boolean running = false;
    private Handler handler; // The object manager

    public Game() {
        // Initialize the entity manager
        handler = new Handler();

        // Add input listeners
        this.addKeyListener(new KeyInput(handler));

        // Create the window (Frame)
        new Window(WIDTH, HEIGHT, TITLE, this);

        // --- Instantiate initial game objects ---
        // Player (x, y, w, h, ID, handler)
        handler.addObject(new Player(ID.Player, handler, 0, 0));

        // Enemy (x, y, w, h, ID, handler)

        // Note: We need 'Block' objects for proper platformer collision.
        // For simplicity, this example uses a temporary ground check in Player/Enemy.
        // You would add Block objects here later.
    }

    // --- Game Loop Management ---

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join(); // Waits for the thread to die
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main game loop (derived from the classic 'run' method in Java).
     * This implements a fixed-time step or variable-time step loop.
     */
    @Override
    public void run() {
        // Request focus so key input is registered immediately
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; // Target ticks per second (logic updates)
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                // Ticking/Logic updates (60 times per second)
                tick();
                delta--;
            }

            if (running) {
                // Rendering updates (as fast as possible)
                render();
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    // --- Core Engine Methods ---

    /**
     * Logic update (e.g., movement, AI, collision checks).
     */
    private void tick() {
        handler.tick();
    }

    /**
     * Graphics rendering.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            // Create 3 buffers (standard for smooth rendering)
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Draw the background
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw a placeholder ground platform
        g.setColor(Color.GREEN);
        g.fillRect(0, HEIGHT - 50, WIDTH, 50);

        // Render all game objects
        handler.render(g);

        // Clean up graphics context and display buffer
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}

