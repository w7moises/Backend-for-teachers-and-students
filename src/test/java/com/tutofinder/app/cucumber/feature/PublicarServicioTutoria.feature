@tag
Feature: PublicarServicioTutoria
  COMO docente
  QUIERO publicar mi servicio de tutoría en la aplicación
  PARA tener más oportunidad laboral


  Scenario: Registrar tutoría
    Given el docente desea registrar la tutoría a brindar
    When el docente iniciara sesión en la web y se dirigirá a la opción “Registrar Tutoría”
    Then se le mostrará una interfaz donde deberá escoger el nivel académico
    And tendrá que ingresar los datos de la tutoría (Fecha, costo, horas, tema)


  Scenario: Publicar tutoría
    Given el docente desea publicar la tutoría a brindar
    When ya haya ingresado todos los datos de la tutoría, este se dirigirá a la opción “Siguiente”
    Then se le mostrará una interfaz con los datos de la tutoría para que pueda revisar si están correctos
    And es así seleccionará la opción “Publicar”


  Scenario: Editar tutoría
    Given el docente desea editar información errónea respecto al servicio de tutoría que quiere brindar
    When este se diriga a la opción “anterior”
    Then se le mostrará la interfaz de registro con sus datos anteriores


  Scenario: Eliminar tutoría publicada
    Given el Docente desea eliminar la tutoría publicada en la aplicación web
    When el docente iniciara sesión en la web y se dirigirá a la opción “Mis tutorías”
    Then se mostrará un interfaz  donde el docente escogerá la tutoría publicada
    And finalmente seleccionará la opción “eliminar”




