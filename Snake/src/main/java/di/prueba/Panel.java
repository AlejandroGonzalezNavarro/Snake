package di.prueba;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Alejandro González Navarro
 */
public class Panel extends JPanel{
    public Panel(Color color, int x, int y,int width,int height) {
        setBounds(x, y, width, height);
        setBackground(color);
        setVisible(true);
    }
}