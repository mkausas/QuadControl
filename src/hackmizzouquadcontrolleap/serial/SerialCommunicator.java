package hackmizzouquadcontrolleap.serial;

import hackmizzouquadcontrolleap.leap.LeapListener;
import org.zu.ardulink.Link;
import org.zu.ardulink.connection.usb.DigisparkUSBConnection;

/**
 *
 * @author Marty
 */
public class SerialCommunicator extends Thread {

    private LeapListener leap;

    public SerialCommunicator(LeapListener leap) {
        this.leap = leap;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
//                leap.frame();

//                Link link = Link.getDefaultInstance();
                Link link = Link.createInstance("digisparkConnection", new DigisparkUSBConnection());
                boolean connected = link.connect("/dev/tty.usbmodemf131", 115200); // comPort could be "COM19" for windows
                if (connected)
                    link.sendCustomMessage(leap.getFormattedReturnValue());
                Thread.sleep(10);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}
