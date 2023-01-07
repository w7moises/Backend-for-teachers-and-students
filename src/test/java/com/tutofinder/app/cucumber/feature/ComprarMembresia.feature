@tag
Feature: ComprarMembresia
  COMO docente
  QUIERO realizar la compra de una membresía
  PARA poder tener una mayor demanda de clientes y darles un mejor servicio


  Scenario: Periodo de prueba
    Given la cuenta del docente es relativamente nueva
    When inicie sesión los primeros días
    Then  se le mostrará una promoción de prueba de la membresía


  Scenario: Aceptar periodo de prueba
    Given el docente está interesado en suscribirse al periodo de prueba
    When seleccione “Aceptar” en la promoción
    Then  se le pedirá sus datos personales y de su tarjeta para iniciar el periodo de prueba


  Scenario: Comprar Membresía
    Given un docente quiere realizar la compra de una membresía por medio de la página web
    When El docente iniciará sesión a la web, luego seleccionará la opción “Obtener membresía”
    Then  e mostrará una interfaz donde se le pedirá al docente que seleccione la tarjeta anteriormente registrada
    And luego de ello colocara el cvc, asimismo escogerá el plan de membresía que más le convenga
    And finalmente seleccionará la opción “comprar”


  Scenario: Prolongar Membresía
    Given el docente desea continuar con la membresía activa
    When este quiera prolongar su suscripción, se dirigirá a la opción “Mi Perfil”
    Then  en la sección de status de la membresía, el docente podrá seleccionar la opción “Renovar Membresía”

  @mytag
  Scenario: Cancelar Membresía
    Given Un docente ya no quiere contar con la membresía
    When el docente iniciará sesión en la web, entonces se dirigirá a su perfil de docente
    Then  se le mostrará el status de su membresía con la opción de “Cancelar Membresía”