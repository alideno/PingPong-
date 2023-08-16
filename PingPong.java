import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingPong extends JPanel {

    private Rectangle p1,p2,ball;
    private Timer timer;
    enum Direction {LEFT,RIGHT,UP,DOWN};
    private PingPong.Direction directionVertical, directionHorizontal;
    private int speed = 5;
    private int score1,score2;
    


    public PingPong(){
        directionVertical = Direction.LEFT;
        directionHorizontal = Direction.UP;
        timer = new Timer(20, new updateListener());
        setPreferredSize(new Dimension(800,500));
        p1 = new Rectangle(25,220,20,60);  
        p2 = new Rectangle(740,220,20,60);
        ball = new Rectangle(380,240,20,20);
        score1 = 0;
        score2 = 0;
        setFocusable(true);
        addKeyListener(new KeyboardClick());
        timer.start();

         

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D screen = (Graphics2D) g;

        if(ball.getX() < 0){
            directionVertical = Direction.RIGHT;
            ball.setLocation(380,240);
            speed = 5;
            score2++;
            timer.stop();

        }else if(ball.getX() + ball.getWidth()*2 > 800){
            directionVertical = Direction.LEFT;
            ball.setLocation(380,240);
            speed = 5;
            score1++;
            timer.stop();
        }
        g.setFont(new Font("serif", Font.PLAIN, 30));
        g.drawString(score1 + " - " + score2, 350, 30);
        g.setColor(Color.BLACK);
        screen.fillRect((int) p1.getX(),(int) p1.getY(), (int) p1.getWidth(), (int) p1.getHeight());
        screen.fillRect((int) p2.getX(),(int) p2.getY(), (int) p2.getWidth(), (int) p2.getHeight());
        g.setColor(Color.RED);
        screen.fillRect((int) ball.getX(),(int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight());
    }

    public void repaint() {
        super.repaint();
    }

    private class KeyboardClick implements KeyListener{


        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'l' && p2.y < 405){
                p2.y = p2.y + 20;
            }else if(e.getKeyChar() == 'o' && p2.y > 0){
                p2.y = p2.y - 20;   
            }else if(e.getKeyChar() == 's' && p1.y < 405){
                p1.y = p1.y + 20;   
            }else if(e.getKeyChar() == 'w' && p1.y > 0){
                p1.y = p1.y - 20;   
            }else if (e.getKeyChar() == 'g'){
                timer.start();
            }else if(e.getKeyChar() == 'r'){

                ball.setLocation(380,240);
                score1 = 0;
                score2 = 0;
                repaint();
                timer.stop();
            }

        }


        public void keyTyped(KeyEvent e) {
            
        }

        public void keyReleased(KeyEvent e) {
            
        }

    
    }

    private class updateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(directionVertical == Direction.LEFT){
                ball.x = ball.x - speed;
                if (ball.getBounds2D().intersects(p1)) {
                    directionVertical = Direction.RIGHT;
                    speed++;
                }

            }else if(directionVertical == Direction.RIGHT){
                ball.x = ball.x + speed;
                if (ball.getBounds2D().intersects(p2)) {
                    directionVertical = Direction.LEFT;
                    speed++;
                }

            }

            if (directionHorizontal == Direction.UP) {
                ball.y = ball.y - speed;
                if (ball.y <= 0) {
                    directionHorizontal = Direction.DOWN;
                }
            }else if (directionHorizontal == Direction.DOWN) {
                ball.y = ball.y + speed;
                if (ball.y + ball.height*3 > 500) {
                    directionHorizontal = Direction.UP;
                }
            }
    
            repaint();
        }
    }   
}