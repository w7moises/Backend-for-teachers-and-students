@tag
Feature: CalificacionServicio
  COMO docente
  QUIERO que mis clientes comenten de mis servicios
  PARA que los que aún no son mis clientes tengan una idea del cómo es mi servicio

  Scenario: Lista de calificaciones
    Given el docente desea que los clientes califiquen su desempeño laboral
    When el usuario inicie sesión en la aplicación web y vaya a la opción “Mi perfil”
    Then se mostrará una interfaz donde se seleccionará la opción “calificación”
    And se podrá visualizar los comentarios hechos por los clientes respecto al desempeño del usuario


  Scenario: Vista de calificación
    Given el docente desea visualizar el detalle del comentario hecho por el cliente
    When se encuentre en la sección “Calificaciones” de su perfil
    Then se le mostrará la opción para clickear el comentario del cliente


  Scenario: Reportar mala calificación
    Given el usuario desea reportar una calificación negativa errónea respecto a su desempeño laboral
    When se encuentra en la su perfil y en la sección comentarios
    Then se mostrará una interfaz donde se seleccionará la opción “calificación”
    And podrá visualizar los comentarios, luego de ello seleccionará el comentario erróneo registrado
    And finalmente seleccionará la opción “reportar”