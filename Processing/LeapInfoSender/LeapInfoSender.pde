import de.voidplus.leapmotion.*;
import development.*;
import processing.serial.*;

LeapMotion leap;
Serial myPort;
int pitchSend = 0;
int rollSend = 0;
int yawSend = 0;
int ySend = 0;
int threshold = 35;

void setup() {
  String portName = Serial.list()[7];
  println(portName);
  myPort = new Serial(this, portName, 115200);
  myPort.bufferUntil('\n'); 
  leap = new LeapMotion(this); 

}

void draw() {
//  num++;
  
  // HANDS
  for(Hand hand : leap.getHands()){
//    if (leap.getHands().length == 2) 
//      println(
      
      
//      if (hand == null) {
//        setValuesToZero();
//        break; 
//      }
        PVector hand_position    = hand.getPosition();
        float hand_y = 2.5 * (100 - hand_position.y);
//        PVector hand_stabilized  = hand.getStabilizedPosition();
        float   hand_pitch        = -hand.getRoll();
        float   hand_roll       = -hand.getPitch();
        float   hand_yaw         = 0;//-hand.getYaw();

        hand_pitch = Math.abs(hand_pitch) < threshold ? 0 : hand_pitch; 
        hand_roll = Math.abs(hand_roll) < threshold ? 0 : hand_roll; 
        hand_yaw = Math.abs(hand_yaw) < threshold ? 0 : hand_yaw; 
        hand_y = Math.abs(hand_y) < threshold ? 0 : hand_y; 

        println(
        "roll " + hand_roll + 
        " pitch " + hand_pitch + 
        " yaw " + hand_yaw + 
        " position " + hand_y);
        
        convertToSendableData(
        hand_pitch, 
        hand_yaw, 
        hand_roll, 
        hand_y);
        println(
        "send: roll " + rollSend + 
        " pitch " + pitchSend + 
        " yaw " + yawSend + 
        " position " + ySend);
          myPort.write(pitchSend);
          delay(2); 
          myPort.write(rollSend);
          delay(2);
          myPort.write(yawSend);
          delay(2);
          myPort.write(ySend);
          delay(2);
  }
  delay(20);
}

void convertToSendableData(float pitch, float roll, float yaw, float y) {
  pitchSend = (int) ((pitch + 100) / 3.125);

  rollSend = (int) (((roll + 100) / 3.125) + 65);
  rollSend = rollSend == 129 ? 128 : rollSend;
  println(rollSend);

  yawSend = (int) (((yaw + 100) / 3.125) + 128);


  ySend = (int) ((y / 3.225) + 193);  

}

void setValuesToZero() {
int pitchSend = 0;
int rollSend = 0;
int yawSend = 0;
int ySend = 0;

}


