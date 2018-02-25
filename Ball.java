import java.awt.*;
import java.awt.geom.*;

public class Ball{
    private double xPoint;
    private double yPoint;
    private double radius1;
    private double radius2;
    private Color color;

    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    private int timer;

    private boolean isUP;
    private boolean isDOWN;
    private boolean isLEFT;
    private boolean isRIGHT;

    protected boolean isColliding;
    private boolean isSquish;
    private boolean smaller;

    private double tempR;

    public Ball(double x, double y, double r){
        xPoint = x;
        yPoint = y;
        radius1 = r;
        radius2 = r;

        tempR = r;
        
        smaller = true;
        isSquish = false;
        isColliding = false;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform initTrans = g2d.getTransform();
        g2d.setColor(color);
        g2d.translate(xPoint,yPoint);
        Ellipse2D.Double e = new Ellipse2D.Double(-radius1,-radius2,radius1*2,radius2*2);
        g2d.fill(e);
        g2d.setTransform(initTrans);
    }

    public void setColor(Color c)
    {
        color = c;
    }

    public void moveX(double x)
    {
        xPoint += x;
    }

    public void moveY(double y)
    {
        yPoint += y;
    }
    
    public void setBounds(double x1, double y1, double x2, double y2)
    {
        xMin = x1;
        xMax = x2;
        yMin = y1;
        yMax = y2;
    }

    public void checkCollision(double x,double y)
    {
        double distance = Math.sqrt(Math.pow((xPoint-x),2) + Math.pow((yPoint-y),2));

        if(radius2 >= distance)
        {
            if(isColliding)
                isColliding = false;
            else
                isColliding = true;
        }

    }
   

    public void checkSquish( double y)
    {

        double distance = Math.abs((yPoint-y));

        if(radius2 >= distance)
        {
            isSquish = true;
        }
    }

    public double getX()
    {
        return xPoint;
    }

    public double getY()
    {
        return yPoint;
    }

    public void setUP()
    {
        isUP = true;
    }

    public void setDOWN()
    {
        isDOWN = true;
    }

    public void setLEFT()
    {
        isLEFT = true;
    }

    public void setRIGHT()
    {
        isRIGHT = true;
    }

    public void collision()
    {
        if(isUP)
        {
            isUP = false;
            isDOWN = true;
        }
        if(isDOWN)
        {
            isDOWN = false;
            isUP = true;
        }
        if(isLEFT)
        {
            isLEFT = false;
            isRIGHT = true;
        }
        if(isRIGHT)
        {
            isRIGHT = false;
            isLEFT = true;
        }

    }

    public void restart()
    {
        isColliding = false;
        isSquish = false;
        isUP = false;
        isDOWN = false;
        isLEFT = false;
        isRIGHT = false;
    }

    public void animate() {
        timer++;
        switch(timer)
        {
            case 1:
            color = Color.RED;
            break;
            case 2:
            color = Color.BLUE;
            break;
            case 3:
            color = Color.MAGENTA;
            break;
            case 4:
            color = Color.GREEN;
            break;
            case 5:
            color = Color.YELLOW;
            break;
        }

        if(timer > 5)
        {
            timer = 0;
        }

        if(isUP)
        {
            if(!isColliding){               
                if(isSquish)
                {
                    radius1--;
                    radius2++;
                    yPoint--;
                    if(radius2 > tempR){
                        radius2 =tempR;
                        radius1 = tempR;
                        yPoint-=5;
                        isSquish = false;
                        smaller = true;

                    }
                }
                else
                    yPoint-=5;

            }
            else{
                if(isSquish){
                    if(smaller){
                        radius1++;
                        radius2--;
                        yPoint++;
                        if((radius1 > (Math.abs(xPoint - yMin))) || radius1 > (Math.abs(xPoint - yMax)))
                        {
                            smaller = false;
                        }
                        else if(radius2 < tempR/2)
                        {
                            smaller = false;
                        }
                    }
                    else{
                        radius1--;
                        radius2++;
                        yPoint--;

                        if(radius2 == tempR)
                        {
                            yPoint-=5;
                            isColliding = false;
                            smaller = true;
                            isSquish = false; 
                        }

                    }
                }
                else
                    yPoint+=5;          

                isUP = false;
            }
        }
        if(isDOWN)
        {

            if(!isColliding){
                if(isSquish){
                    if(smaller){
                        radius1++;
                        radius2--;
                        yPoint++;         
                        if((radius1 > (Math.abs(xPoint - yMin))) || radius1 > (Math.abs(xPoint - yMax)))
                        {
                            smaller = false;
                        }
                        else if(radius2 < tempR/2)
                        {
                            smaller = false;
                        }
                    }
                    else{
                        radius1--;
                        radius2++;
                        yPoint--;

                        if(radius2 == tempR)
                        {
                            yPoint-=5;
                            isColliding = true;
                            smaller = true;
                            isSquish = false; 
                        }

                    }
                }
                else
                    yPoint+=5;

            }
            else{
                yPoint-=5;
            }
            isDOWN = false;

        }
        if(isLEFT)
        {

            if(!isColliding)
                xPoint-=5;
            else
                xPoint+=5;
            isLEFT = false;

        }
        if(isRIGHT)
        {

            if(!isColliding)
                xPoint+=5;
            else
                xPoint-=5;
            isRIGHT = false;

        }

    }
}
