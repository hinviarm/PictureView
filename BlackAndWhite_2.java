
package effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class BlackAndWhite_2 extends abstractEffect {

    public BlackAndWhite_2(String title) {
        super(title);
    }

    @Override
    public BufferedImage actionEffect(BufferedImage img) {
        int h=img.getHeight();
        int w=img.getWidth();
        BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        Graphics surfaceImg = tmp.createGraphics();
        surfaceImg.drawImage(img, 0,1 , this);
        img = tmp;
        return img;
        //BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

    }
}
