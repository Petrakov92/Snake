import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 *
 * @author invis
 */
public class Game extends JApplet implements Runnable{
    private Direction direction = new Direction();
    Snake our_snake = new Snake();
    /**
     * Флаг нужнг для передачи фокуса во время первого рисования.
     */
    boolean mFirstPaint = true;

    public Game(){
        setSize(480, 480);
        setBackground(Color.white);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_DOWN:
                        direction.turn(Direction.DOWN);break;
                    case KeyEvent.VK_UP:
                        direction.turn(Direction.UP);break;
                    case KeyEvent.VK_LEFT:
                        direction.turn(Direction.LEFT);break;
                    case KeyEvent.VK_RIGHT:
                        direction.turn(Direction.RIGHT);break;
                }
            }
        });
    }
    /**
     * Выводит сообщение о поражении.
     */
    public void loose(){
        getGraphics().drawString("LOOSER", 220, 230);
    }

    @Override
    public void paint(Graphics g){
        // передаём фокус апплету
        if (mFirstPaint){
            mFirstPaint = false;
            requestFocus();
        }
        showStatus("X - " + Integer.toString(our_snake.gdeX()) + "; Y - " + Integer.toString(our_snake.gdeY()));
        g.setColor(Color.black);
        g.drawRect(4, 4, 471, 471);
        our_snake.step(direction);
        our_snake.paint(g);
        our_snake.food(g);
    }

    public void run() {
        while(our_snake.alive){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
            repaint();
        }
        if(!our_snake.alive){loose();return;}
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }
}