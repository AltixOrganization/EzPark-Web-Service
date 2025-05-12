Feature: US32 Agregar vehículo al perfil
  Como conductor quiero añadir mi vehículo en la aplicación para tenerlo disponible en mis reservas.

  Scenario Outline: Agregar un nuevo vehículo al perfil
    Given el <conductor> accede a la sección de vehículos de su perfil
    When ingresa los <datos del vehículo> y confirma
    Then el sistema agrega el vehículo al perfil del conductor

    Examples:
      | conductor | datos del vehículo                |
      | Ana       | placa: ABC123, modelo: Sedan      |

  Scenario Outline: Fallar al agregar vehículo con datos inválidos
    Given el <conductor> accede a la sección de vehículos de su perfil
    When ingresa <datos inválidos> y confirma
    Then el sistema muestra un <mensaje de error>

    Examples:
      | conductor | datos inválidos      | mensaje de error                  |
      | Ana       | placa vacía          | "Debe ingresar la placa del vehículo" |