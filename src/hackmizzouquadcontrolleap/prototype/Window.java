package hackmizzouquadcontrolleap.prototype;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Marty
 */
public class Window extends JFrame {

    // grab fullscreen dimensions
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Window(Panel panel) {
        super("QuadControl");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
//        setOpacity((float) 0.8); // configure for window transparency

        // to quickly exit the program click the escape button
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        add(panel);
        setVisible(true);
    }


}
