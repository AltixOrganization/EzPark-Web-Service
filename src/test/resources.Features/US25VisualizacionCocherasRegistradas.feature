Feature: US25 Visualización de cocheras registradas
  Como propietario quiero ver las cocheras que tengo registradas en la aplicación para gestionarlas.

  Scenario Outline: Visualizar lista de cocheras registradas
    Given el <propietario> tiene cocheras registradas
    When accede a la sección de cocheras
    Then el sistema muestra la <lista de cocheras> del propietario

    Examples:
      | propietario | lista de cocheras                |
      | Juan        | Cochera1, Cochera2, Cochera3     |

  Scenario Outline: Visualizar mensaje si no tiene cocheras
    Given el <propietario> no tiene cocheras registradas
    When accede a la sección de cocheras
    Then el sistema muestra un <mensaje de aviso>

    Examples:
      | propietario | mensaje de aviso                        |
      | Juan        | "No tienes cocheras registradas aún."   |