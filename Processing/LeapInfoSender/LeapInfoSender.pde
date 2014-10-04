import de.voidplus.leapmotion.*;
import development.*;
import processing.serial.*;

Serial myPort;
int pitchSend = 0;
int rollSend = 0;
int yawSend = 0;
int ySend = 0;

void setup() {
  String portName = Serial.list()[7];
  println(portName);
  myPort = new Serial(this, portName, 115200);
  myPort.bufferUntil('\n'); 
}

void draw() {
//  num++;
  myPort.write(pitchSend);
  myPort.write(rollSend);
  myPort.write(yawSend);
  myPort.write(ySend);
  
      // HANDS
  for(Hand hand : leap.getHands()){
//      if (hand == null)
//        break;
        PVector hand_position    = hand.getPosition();
//        PVector hand_stabilized  = hand.getStabilizedPosition();
        float   hand_roll        = hand.getRoll();
        float   hand_pitch       = hand.getPitch();
        float   hand_yaw         = hand.getYaw();
//        println(
//        "roll " + hand_roll + 
//        " pitch " + hand_pitch + 
//        " yaw " + hand_yaw + 
//        " position " + (100 - hand_position.y));
        convertToSendableData(hand_pitch, hand_roll, hand_yaw, (100 - hand_position.y));
        println(
        "roll " + rollSend + 
        " pitch " + pitchSend + 
        " yaw " + yawSend + 
        " position " + ySend);
  } 
}

void convertToSendableData(float pitch, float roll, float yaw, float y) {
  pitchSend = (int) ((pitch + 100) / 3.125);

  rollSend = (int) (((roll + 100) / 3.125) + 65);
  rollSend = rollSend == 129 ? 128 : rollSend;

  yawSend = (int) (((yaw + 100) / 3.125) + 128);

  ySend = (int) (y / 3.225);  
}


