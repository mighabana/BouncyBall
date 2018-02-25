import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class SquishyBall extends JComponent {

    private double xPoint;
    private double yPoint;
    private static int width;
    private static int height;
    private Ball ball;

    private boolean isCollided;

    public SquishyBall(int w, int h) {
        xPoint = w/2;
        yPoint = h/2;
        width = w;
        height = h;

        ball  = new Ball(xPoint,yPoint,25);

        ball.setBounds(25,25,width-25,height-25);
        
        this.setFocusable(true);
        this.addKeyListener(new BallListener());

        class a implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                ball.checkCollision(ball.getX(),25);
                ball.checkSquish(height-25);
                ball.checkCollision(25,ball.getY());
                ball.checkCollision(width-25,ball.getY());
                ball.animate();
                repaint();
            }
        }
        Timer t = new Timer(100,new a());
        t.start();

    }

    protected void paintComponent( Graphics g ) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double border = new Rectangle2D.Double(25,25,width-50,height-50);
        g2d.fill(border);

        ball.draw(g2d);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        SquishyBall sb = new SquishyBall(400,400);

        f.getContentPane().setPreferredSize( new Dimension(width,height) );
        f.setTitle( "Key Listener" );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
        f.add(sb);
        f.pack();
        f.setVisible( true );

    }

    public class BallListener implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                ball.setUP();
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                ball.setDOWN();
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                ball.setLEFT();
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                ball.setRIGHT();
            }
        }

        public void keyReleased(KeyEvent e)
        {
                ball.restart();
        }

        public void keyTyped(KeyEvent e)
        {

        }
    }

}
