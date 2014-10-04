package hackmizzouquadcontrolleap.threads;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import hackmizzouquadcontrolleap.leap.LeapListener;
import hackmizzouquadcontrolleap.prototype.Window;

/**
 *
 * @author Marty
 */
public class AnimationManager extends Thread {


    private Window frame;
    private Controller leap;

    public AnimationManager(Window frame) {
        this.frame = frame;
        leap = new Controller(new LeapListener());
    }

    @Override
    public void run() {
//        while (true) {
            try {

                Frame f = leap.frame();

                frame.repaint();
                Thread.sleep(100); // TODO: Figure out a non-laggy time...
            } catch (Exception ex) { ex.printStackTrace(); }
//        }
    }

}

