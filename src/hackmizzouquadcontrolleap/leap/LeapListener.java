package hackmizzouquadcontrolleap.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

/**
 *
 * @author Marty
 */
public class LeapListener extends Listener {

    private double
            pitch = 0,
            roll = 0;

    public void onInit(Controller cntrlr) {
        System.out.println("Init");
    }

    public void onFrame(Controller paramController) {
        Frame f = paramController.frame();
        System.out.println("On frame");
        if (!f.hands().isEmpty()) {
//            Vector palmPos = f.hands().frontmost().palmPosition();
//            System.out.printf("pitch: %f, roll: %f\n", palmPos.pitch(), palmPos.roll());
            Hand frontHand = f.hands().frontmost();
            Vector direction = frontHand.direction();
            Vector normalizedPalm = frontHand.palmNormal();

//            double pitch = Math.atan2(frontHand.direction().getY(), -frontHand.direction().getZ());
//            double rollAng = 0.12 * Math.atan2(frontHand.palmNormal().getX(), frontHand.palmNormal().getY());
            double pitch = direction.pitch() * 150;
            double rollAng = normalizedPalm.roll() * 150;
            double yaw = direction.yaw() * 150;
            double y = normalizedPalm.getY() * 100;
            System.out.println("Pitch: " + pitch + " Roll: " + rollAng + " Yaw: " + yaw + " Y: " + y);
        }
    }


}
