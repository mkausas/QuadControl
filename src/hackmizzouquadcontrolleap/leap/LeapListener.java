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
            roll = 0,
            yaw = 0,
            y = 0;

    private Frame currentFrame;

    public void onInit(Controller cntrlr) {
        System.out.println("Init");
    }

    @Override
    public void onFrame(Controller paramController) {
        currentFrame = paramController.frame();

        if (!currentFrame.hands().isEmpty()) {
//            Vector palmPos = f.hands().frontmost().palmPosition();
//            System.out.printf("pitch: %f, roll: %f\n", palmPos.pitch(), palmPos.roll());
            Hand frontHand = currentFrame.hands().frontmost();
            Vector direction = frontHand.direction();
            Vector normalizedPalm = frontHand.palmNormal();

            // manual way of finding values
//            double pitch = Math.atan2(frontHand.direction().getY(), -frontHand.direction().getZ());
//            double rollAng = 0.12 * Math.atan2(frontHand.palmNormal().getX(), frontHand.palmNormal().getY());
            pitch = thresholdValue(direction.pitch() * 150);
            roll = thresholdValue(normalizedPalm.roll() * 150);
            yaw = thresholdValue(direction.yaw() * 150);
            y = thresholdValue(normalizedPalm.getY() * 100 + 100);
            System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw + " Y: " + y);
        }
    }

    /**
     * 
     * @param value
     * @return
     */
    public double thresholdValue(double value) {
        return value > 100 ? 100 : (value < -100 ? -100 : value);
    }

    /**
     * Returns values needed to control the quadcopter.
     * Order of values:
     * 0 - Pitch
     * 1 - Roll
     * 2 - yaw
     * 3 - hand height
     *
     * @return
     */
    public int[] getControlValues() {
        int[] controlValues = { (int) pitch, (int) roll, (int) yaw, (int) y };
        return controlValues;
    }
}
