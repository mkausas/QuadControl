import processing.serial.*;

Serial myPort;
int num = 1000;
void setup() {
  String portName = Serial.list()[7];
  println(portName);
  myPort = new Serial(this, portName, 115200);
  myPort.bufferUntil('\n'); 
}

void draw() {
//  num++;
  myPort.write(60);
  myPort.write(90);
  myPort.write(150);
  myPort.write(200);
  
  println(num);
  delay(100);
}
