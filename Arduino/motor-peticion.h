#include <Process.h>  //Process lib use to call Linux Commands in Yun Shield
#include <Console.h>  //Console lib, used to show debug info in Arduino IDE
#include <Servo.h>


Servo servoMotor;
// set up net client info:
const unsigned long postingInterval = 60000;  //delay de llamada al servicio
unsigned long lastRequest = 0;      // when you last made a request
String dataString = "";
volatile boolean flagSVR = true;

void setup(){
 // start console:
 Serial.begin(9600);
  Bridge.begin();
  Console.begin();
servoMotor.attach(7); //pin  7 para controlar al servo
servoMotor.write(0); //angulo inicial del servo
attachInterrupt(digitalPinToInterrupt(2),SVR,RISING);
//attachInterrupt(digitalPinToInterrupt(2),loop,FALLING);
//attachInterrupt(digitalPinToInterrupt(3),SVR,RISING);
  }

   void SVR(){
flagSVR=false;
  }

   void peticionHttp(){
      
      Serial.println('Hubo una interrupcion, se procede a realizar funcion: \n');
    // form the string for the API header parameter:
  String apiString = "Alarma de Robo: ";
 
  // form the string for the URL parameter:
  String url = "https://us-central1-arqui2umg.cloudfunctions.net/sendFancyMessage";

 
  // Send the HTTP PUT request, form the linux command and use Process Class to send this command to Yun Shield
 
  // Is better to declare the Process here, so when the
  // sendData function finishes the resources are immediately
  // released. Declaring it global works too, BTW.
  Process alarma;
  Console.print("\n\nEnviando peticion... ");
  alarma.begin("curl");
  alarma.addParameter("-k");
  alarma.addParameter(url);
  alarma.run();
  Console.println("done!");
  }
   // }

  
 

void loop(){

    for(int i = 0; i<=180; i++){
      if(!flagSVR){
        peticionHttp();
        flagSVR=true;
      }
      servoMotor.write(i);
      Serial.println('SERVO ANGULO');
      delay(25);
      
  }
  for(int i = 179; i>0;i--){
    if(!flagSVR){
        peticionHttp();
        flagSVR=true;
      }
    servoMotor.write(i);
    Serial.println('SERVO ANGULO');
    delay(25);
    break;
  }

 }


