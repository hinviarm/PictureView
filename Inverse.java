
package effect;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Inverse extends abstractEffect {

    public Inverse(String title) {
        super(title);
    }

    @Override
    public BufferedImage actionEffect(BufferedImage img) {
      
        int h=img.getHeight();
       int w=img.getWidth();
        BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB );
        float[ ] masqueFlou =
        {
            0.03f, 0.1f, 0.03f,
            0.1f, 0.03f, 0.1f,
            0.03f, 0.1f, 0.03f
        };
        Kernel masque = new Kernel(3, 3, masqueFlou);
        ConvolveOp opération = new ConvolveOp(masque);
        opération.filter(img, tmp);
        img = tmp;

        return img;
    }
}
