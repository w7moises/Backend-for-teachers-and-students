@tag
Feature: RealizarPago
  COMO padre
  QUIERO realizar el pago de una de las tutorias que mi hijo se inscribio
  PARA culminar el servicio de la tutoria


  Scenario: Realizar Pago
    Given un padre desea realizar el pago de una tutoria inscrita pro su hijo en la pagina web
    When seleccione el usuario de su hijo, verifica las tutorias que realizo y seleccione la opcion “Realizar pago”
    Then se mostrará una interfaz donde se le pedirá al padre que seleccione la tarjeta registrada anteriormente
    And  seleccionar la opción “Pagar”


  Scenario: Comprobante
    Given el padre ya realizó el pago
    When el docente solicite un comprobante para inciar la tutoria
    Then el padre podrá enviar el recibo para que no exista ningun inconveniente


  Scenario: Cancelar el pago
    Given el padre ya no quiere realizar el pago
    When este se encuentre en la seccion de pago
    Then se le mostrará la opcion “Cancelar” si es que el padre no se encuentra conforme con algun dato de pago