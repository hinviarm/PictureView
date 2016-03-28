
package effect;

import java.awt.image.BufferedImage;
import javax.swing.JRadioButton;


public abstract class abstractEffect extends JRadioButton
{
    public abstractEffect(String title)
    {
        super(title);
    }
    public abstract BufferedImage actionEffect(BufferedImage img);
}
