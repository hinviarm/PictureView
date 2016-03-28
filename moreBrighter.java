
package effect;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class moreBrighter extends abstractEffect
{
    public moreBrighter(String title)
    {
        super(title);
    }
    @Override
    public BufferedImage actionEffect(BufferedImage img) {
       int h=img.getHeight();
       int w=img.getWidth();
      
       BufferedImage tmp=new BufferedImage(w,h,img.getType());

       return tmp;
    }
    
}
