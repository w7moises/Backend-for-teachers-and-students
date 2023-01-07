@tag
Feature: ActualizarContraseña

  COMO usuario
  QUIERO actualizar mi contraseña
  PARA que sea segura que la anterior y estar seguro de que mi cuenta solo la manejo yo


  Scenario: Cambiar mi contraseña
    Given quiero actualizar mi contraseña
    When  quiero introducir una nueva contraseña
    Then la aplicación me indica que la contraseña debe tener más de ocho caracteres, no debe contener especiales
    And esta no podrá ser la misma que la actual u otras anteriores


  Scenario: Confirmación del cambio por medio del correo
    Given ya actualicé mi contraseña
    When  esta se ha confirmado
    Then la aplicación me mandará un email a mi correo registrado con la confirmación


  Scenario: Autenticación
    Given la aplicación debe confirmar que soy yo
    When  quiero actualizar mi contraseña
    Then este me pedirá introducir mi contraseña actual para asegurarse de ello