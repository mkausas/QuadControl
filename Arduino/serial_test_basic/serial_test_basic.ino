int val = 0;
int pitch = 0;
int roll = 0;
int yaw = 0;
int y = 0;

 void setup() {
 //  pinMode(ledPin13, OUTPUT); // Set pin as OUTPUT
   Serial.begin(115200); // Start serial communication at 9600 bps
 }

 void loop() {
   delay(10);
   if (Serial.available()){ 
//     Serial.println("serial available");
     val = Serial.read(); // read it and store it in val

   }
   Serial.println(val);
//  setValues(
 }


void setValues(int x) {
  if (x < 0)
    x = 0;
  if (x > 224)
    x = 224;
    
  if (x >= 0 && x <= 64) {
    x -= 32;
    pitch = x * 3;
  } else if (x >= 65 && x <= 128) {
    x -= 96;
    roll = x * 3;
  } else if (x >= 129 && x <= 192) {
    x -= 160;
    yaw = x * 3;
  } else if (x >= 193 && x <= 224) {
    x -= 193;
    y = x * 3;
  }
}
