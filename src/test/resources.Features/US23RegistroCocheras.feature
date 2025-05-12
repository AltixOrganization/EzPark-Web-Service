Feature: US23 Registro de cocheras
  Como propietario quiero registrar mi cochera en la aplicación para ofrecer como opción de estacionamiento.

  Scenario Outline: Registrar una nueva cochera
    Given el <propietario> desea registrar una nueva cochera
    And se encuentra en la sección de registro de cocheras
    When ingresa los <datos> de la cochera y confirma el registro
    Then el sistema almacena la cochera y la muestra en la lista de cocheras del propietario

    Examples:
      | propietario | datos                                 |
      | Juan        | Calle 123, techada, 2.5x5m, $100/día  |

  Scenario Outline: Fallar al registrar cochera por datos incompletos
    Given el <propietario> desea registrar una nueva cochera
    And se encuentra en la sección de registro de cocheras
    When ingresa <datos incompletos> y confirma el registro
    Then el sistema muestra un <mensaje de error>

    Examples:
      | propietario | datos incompletos         | mensaje de error                      |
      | Juan        | dirección faltante        | "Debe ingresar todos los datos requeridos" |