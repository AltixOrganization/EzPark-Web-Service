Feature: US20 Detalles del estacionamiento
  Como conductor quiero ver los detalles de un estacionamiento para seleccionar la mejor opción.

  Scenario Outline: Visualizar detalles de un estacionamiento
    Given el <conductor> busca estacionamientos disponibles
    When selecciona un <estacionamiento>
    Then el sistema muestra los <detalles> del estacionamiento

    Examples:
      | conductor | estacionamiento | detalles                                 |
      | Ana       | Cochera1        | dirección, precio, dimensiones, fotos    |

  Scenario Outline: Fallar al visualizar detalles inexistentes
    Given el <conductor> busca estacionamientos disponibles
    When selecciona un <estacionamiento inexistente>
    Then el sistema muestra un <mensaje de error>

    Examples:
      | conductor | estacionamiento inexistente | mensaje de error                  |
      | Ana       | CocheraX                   | "El estacionamiento no existe"    |