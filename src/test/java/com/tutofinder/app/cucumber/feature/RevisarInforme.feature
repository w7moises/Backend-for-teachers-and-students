@tag
Feature: RevisarInformes
  COMO padre de familia
  QUIERO poder revisar los informes de mi hijo
  PARA poder conocer detalladamente su desempeño académico


  Scenario: El informe no esta subido
    Given el padre de familia se encuentra en la sección “Mis clases”
    When se dirija a los detalles de una clase y no hay informacion
    Then no podra descargar el informe del alumno y se mostrara el mensaje “informe no disponible”

  Scenario: El informe ya esta subido
    Given el padre de familia se encuentra en la sección “Mis clases”
    When se dirija a los detalles de la clase y el informe ha sido subido
    Then podra visualizar el nombre del informe y al lado un botón para descargarlo

  Scenario: El informe ya esta subido pero no es correcto
    Given el padre de familia se encuentra en la sección “Mis clases”
    And el informe ya está disponible
    When el padre determine que el archivo no cumple con lo esperado y seleccione la opción notificar docente
    Then se enviara una notificación al docente solicitando la corrección del informe.