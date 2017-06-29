package info.hylle.aistuff;

import java.awt.*;

public class GraphicsObject
{
       int x;
       int y;

       int originX;
       int originY;
       int width;
       int height;
       Color borderColor;
       Color backgroundColor;

       public GraphicsObject( Color aBorderColor, Color aBackgroundColor )
       {
               borderColor = aBorderColor;
               backgroundColor = aBackgroundColor;
       }

       public void paint( Graphics g )
       {
               Graphics2D g2 = ( Graphics2D ) g;

               g2.setColor( backgroundColor );
               g2.fillOval( x, y, width, height );
               g2.setColor( borderColor );
               g2.drawOval( x, y, width, height );
       }

}