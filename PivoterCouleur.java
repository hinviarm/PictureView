
package effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class PivoterCouleur extends abstractEffect {

    public PivoterCouleur(String title) {
        super(title);
    }

    @Override
    public BufferedImage actionEffect(BufferedImage img) {
       int h=img.getHeight();
       int w=img.getWidth();
        BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB );
        float[ ] masqueFlou =
        {
            5.2f, 0.1f, 5.2f,
            0.1f, 0.1f, 0.1f,
            5.2f, 0.1f, 5.2f
        };
        Kernel masque = new Kernel(1, 3, masqueFlou);
        ConvolveOp opération = new ConvolveOp(masque);
        opération.filter(img, tmp);
        img = tmp;

        return img;
}
}