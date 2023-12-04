const int pin1 = 2;
const int pin2 = 3;
const int pin3 = 4;
const int pin4 = 5;

int estadoBoton = LOW;        // Estado actual del botón
int ultimoEstadoBoton = LOW;  // Estado anterior del botón

int estadoBoton2 = LOW;        // Estado actual del botón
int ultimoEstadoBoton2 = LOW;  // Estado anterior del botón

int estadoBoton3 = LOW;        // Estado actual del botón
int ultimoEstadoBoton3 = LOW;  // Estado anterior del botón

int estadoBoton4 = LOW;        // Estado actual del botón
int ultimoEstadoBoton4 = LOW;  // Estado anterior del botón

void setup() {
  pinMode(pin1, INPUT);
  pinMode(pin2, INPUT);
  pinMode(pin3, INPUT);
  pinMode(pin4, INPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  digitalWrite(7, HIGH);
  digitalWrite(8, HIGH);
  digitalWrite(9, HIGH);

  Serial.begin(9600);
  Serial.println(0);
}

void loop() {
  estadoBoton = digitalRead(pin1);
  estadoBoton2 = digitalRead(pin2);
  estadoBoton3 = digitalRead(pin3);
  estadoBoton4 = digitalRead(pin4);

  if (estadoBoton != ultimoEstadoBoton) {
    // Ha ocurrido un cambio en el estado del botón
    if (estadoBoton == HIGH) {
      // El botón naranja se ha presionado
      Serial.println(2);  // Enviar la señal
      delay(1000);
    }
  }

  ultimoEstadoBoton = estadoBoton;

  if (estadoBoton2 != ultimoEstadoBoton2) {
    // Ha ocurrido un cambio en el estado del botón
    if (estadoBoton2 == HIGH) {
      // El botón azul se ha presionado
      Serial.println(9);  // Enviar la señal
      delay(1000);
    }
  }

  ultimoEstadoBoton2 = estadoBoton2;

  if (estadoBoton3 != ultimoEstadoBoton3) {
    // Ha ocurrido un cambio en el estado del botón
    if (estadoBoton3 == HIGH) {
      // El botón verde se ha presionado
      Serial.println(6);  // Enviar la señal
      delay(1000);
    }
  }

  ultimoEstadoBoton3 = estadoBoton3;


  if (estadoBoton4 != ultimoEstadoBoton4) {
    // Ha ocurrido un cambio en el estado del botón
    if (estadoBoton4 == HIGH) {
      // El botón rojo se ha presionado
      Serial.println(8);  // Enviar la señal
      delay(1000);
    }
  }

  ultimoEstadoBoton4 = estadoBoton4;


  //MÉTODO PARA RECIBIR LOS DATOS.
  if (Serial.available() > 0) {
    char receivedChar = Serial.read();
    if (receivedChar == '7') {
      digitalWrite(7, LOW);
    }

    if (receivedChar == '8') {
      digitalWrite(8, LOW);
    }
    if (receivedChar == '9') {
      digitalWrite(9, LOW);
    }

    if (receivedChar == '1') {
      digitalWrite(7, HIGH);
      digitalWrite(8, HIGH);
      digitalWrite(9, HIGH);
      Serial.println(-1);
    }
  }
}
