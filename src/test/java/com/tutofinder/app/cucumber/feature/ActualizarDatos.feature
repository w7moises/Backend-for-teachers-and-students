@tag
Feature: ActualizarDatos
  COMO usuario
  QUIERO poder actualizar mis datos
  PARA que la información mostrada sea reciente o corregir algún dato


  Scenario: Sobreescritura de datos
    Given el usuario tiene datos existentes
    When quiera actualizar sus datos
    Then la aplicación le permitirá escribir sobre estos


  Scenario: Confirmación mediante contraseña
    Given que ya actualicé mis datos
    When quiera confirmar estos cambios
    Then la aplicación me pedirá ingresar mi contrasena actual


  Scenario: Confirmación por correo
    Given la contraseña de ingreso
    When se da por terminado la actualización
    Then me enviará un correo para informarme de los cambios realizados para que como docente pueda verificar si hice yo este cambio