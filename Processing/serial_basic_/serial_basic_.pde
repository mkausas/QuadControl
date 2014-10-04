import processing.serial.*;

Serial myPort;

void setup() {
  String portName = Serial.list()[7];
  println(portName);
  myPort = new Serial(this, portName, 115200);
  myPort.bufferUntil('\n'); 
  }
int x = 0;
int y = 0;
void draw() {
  delay(10);
  x++;
//  y--;

//  println(x);
  myPort.clear();
  myPort.write(10);
  myPort.write(20);
  myPort.write(30);
  
 }
