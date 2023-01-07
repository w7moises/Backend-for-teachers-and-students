@tag
Feature: FiltrarTutoria
  COMO padre de familia
  QUIERO filtrar la lista de tutorias
  PARA una óptima búsqueda con respecto a mis requerimientos


  Scenario: Buscar docente
    Given el padre ha iniciado sesión en la plataforma web
    When seleccione la opción “Buscar” y redacte el nombre de un docente
    Then se mostrara el docente solicitado, donde al seleccionarlo se mostrara una interfaz con sus detalles

  Scenario: Buscar por curso
    Given el usuario padre se encuentra en la sección “Mis clases”
    When ingrese el nombre del curso en el formulario de búsqueda y presione en buscar
    Then se listaran los docentes que enseñen el curso ingresado


  Scenario: Buscar por costo
    Given el usuario padre se encuentra en la sección “Mis clases”
    When ingrese el rango de un costo deseado en el formulario de búsqueda y seleccione la opción “Buscar”
    Then se listaran solo los docentes que cobren esa cantidad


  Scenario: Buscar por tiempo
    Given el usuario padre se encuentra en la sección “Mis clases”
    When ingrese una cantidad de horas en el formulario de búsqueda y seleccione “Buscar”
    Then se mostraran los docentes que estén enseñando la cantidad de horas deseadas