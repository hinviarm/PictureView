
package effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class BlackAndWhite_1 extends abstractEffect {

    public BlackAndWhite_1(String title) {
        super(title);
    }

    @Override
    public BufferedImage actionEffect(BufferedImage img) {
        int h=img.getHeight();
        int w=img.getWidth();
        BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY );
        Graphics surfaceImg = tmp.createGraphics();
        surfaceImg.drawImage(img, 1,0 , this);
        img = tmp;
        return img;
    }
}
