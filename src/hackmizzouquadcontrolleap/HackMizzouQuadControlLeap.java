package hackmizzouquadcontrolleap;

import com.leapmotion.leap.Controller;
import hackmizzouquadcontrolleap.leap.LeapListener;
import hackmizzouquadcontrolleap.prototype.Panel;
import hackmizzouquadcontrolleap.prototype.Window;
import hackmizzouquadcontrolleap.serial.SerialCommunicator;

/**
 * Project for HackMizzou 2014: QuadControl
 * Leap Motion controller will control a
 * Nano QX Quadcopter.
 *
 * @author Marty Kausas
 */
public class HackMizzouQuadControlLeap {


    private Window window;
    private Panel panel;

    private Controller leap;
    private LeapListener listener;

    public HackMizzouQuadControlLeap() {
        leap = new Controller();
        listener = new LeapListener();
        leap.addListener(listener);

        SerialCommunicator serialCom = new SerialCommunicator(listener);
        serialCom.start();

        while (true) {
            try {
                leap.frame();
                Thread.sleep(10);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
//        panel = new Panel();
//        window = new Window(panel);
    }

    //main
    public static void main(String[] args) {
        new HackMizzouQuadControlLeap();
    }

}
