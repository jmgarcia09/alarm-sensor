/*
GUATEMALA 09-10-2018
Universidad Mariano Galvez de Guatemala
PROGRAMA PARA CONTROLAR EL RADAR ULTRASONICO
*/

//Pines constantes utilizados para el sensor ultrasonico.
const int PIN_LED  = 13; //PIN DE LED
const int PIN_TRIG = 6; //PIN DE TRIGGER
const int PIN_ECHO = 5; // PIN ECHO
//Pin interrupcion
const int PIN_INTERRUPT = 3;


//Metodo de configuracion (Se definen el modo como interactuan los pines INPUT-OUTPUT).
void setup() {
  Serial.begin(9600);
  pinMode(PIN_LED,OUTPUT);
  pinMode(PIN_TRIG,OUTPUT);
  pinMode(PIN_ECHO,INPUT);
  pinMode(PIN_INTERRUPT,OUTPUT);
  digitalWrite(PIN_INTERRUPT,0);
}


//Metodo loop el cual estara ejecutandose.
void loop(){

//Se recibe la distancia sobre la cual se encuentra identificada por el sensor.
int cm = ping(PIN_TRIG,PIN_ECHO);
Serial.print("Distancia: ");
Serial.println(cm);

//Se toma una medida de 30 centimetros (Esto es configurable segun sea necesario)
if (cm <= 30)
{
	//Se detecta el objeto y se activa el interruptor.
  digitalWrite(PIN_INTERRUPT,0);
  delayMicroseconds(25);
  digitalWrite(PIN_INTERRUPT,5);
}else
{
	//Si no se detecta nada se limpia el pin de interrupcion
  digitalWrite(PIN_INTERRUPT,LOW);
  delayMicroseconds(4);
  }
delay(1000);
}


//Metodo el cual obtiene la distancia detectada por el sensor
int ping(int PIN_TRIG,int PIN_ECHO){
  long duration, distanceCm;

  digitalWrite(PIN_TRIG,LOW); //PARA GENERAR UN PULSO LIMPIO PNEMOS A LOW con 4 MICRO SEGUNDOS
  delayMicroseconds(4);

  digitalWrite(PIN_TRIG,HIGH);//GENERO UN TRIGGER (DISPARADOR) DE 10 MICROSEGUNDOS
  delayMicroseconds(10);

  digitalWrite(PIN_TRIG,LOW);

  duration = pulseIn(PIN_ECHO,HIGH); //MIDO EL TIEMPO ENTRE PULSOS, EN MICRO SEGUNDOS

  distanceCm = duration * 10 / 292/2; //CONVIERTO A DISTANCIA EN CM

  return distanceCm;
  }