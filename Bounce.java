import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Bounce extends JComponent {

    private double xPoint;
    private double yPoint;
    private static int width;
    private static int height;
    private Circle circle;

    private boolean isCollided;

    public Bounce(int w, int h) {
        xPoint = w/2;
        yPoint = h/2;
        width = w;
        height = h;

        circle  = new Circle(xPoint,yPoint,25);

        circle.setBounds(25,25,width-25,height-25);

        this.setFocusable(true);
        this.addKeyListener(new CircleListener());

        class a implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                circle.checkCollision(circle.getX(),25);
                circle.checkCollision(circle.getX(),height-25);
                circle.checkCollision(25,circle.getY());
                circle.checkCollision(width-25,circle.getY());
                circle.animate();
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

        circle.draw(g2d);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        Bounce sb = new Bounce(400,400);

        f.getContentPane().setPreferredSize( new Dimension(width,height) );
        f.setTitle( "Key Listener" );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
        f.add(sb);
        f.pack();
        f.setVisible( true );

    }

    public class CircleListener implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                circle.setUP();
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                circle.setDOWN();
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                circle.setLEFT();
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                circle.setRIGHT();
            }
        }

        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_S)
                circle.restart();
        }

        public void keyTyped(KeyEvent e)
        {

        }
    }

}