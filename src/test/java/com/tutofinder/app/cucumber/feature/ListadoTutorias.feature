@tag
Feature: ListadoTutorias
  COMO padre
  QUIERO ver la lista de tutorías con datos relevantes
  PARA que me sean de ayuda para escoger al indicado


  Scenario: Lista de tutorias totales
    Given debo tener la mayor cantidad de opciones posibles respecto a las preferencias
    When busque una tutoria
    Then la aplicacion me mostrará un listado de todas las publicaciones de tutorias


  Scenario: Tutorias por ubicacion mas cercana
    Given las tutorias se dictan en un área especifica del lugar
    When quiera buscar un servicio de tutoria
    Then la aplicacion me habilitara aquellas que esten mas cercanas a mi ubicacion


  Scenario: Tutorias por preferencia
    Given hay docentes con membresia en la aplicacion
    When se visualice la lista de tutorias
    Then la aplicacion priorizará a aquellas con membresia