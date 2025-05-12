Feature: US31 Visualización de vehículo
  Como conductor quiero visualizar mi vehículo para ver mi información personal y de uso en la plataforma.

  Scenario Outline: Visualizar información de vehículo registrado
    Given el <conductor> tiene vehículos registrados en su perfil
    When accede a la sección de vehículos
    Then el sistema muestra la <información del vehículo>

    Examples:
      | conductor | información del vehículo           |
      | Ana       | placa: ABC123, modelo: Sedan      |

  Scenario Outline: Visualizar mensaje si no tiene vehículos
    Given el <conductor> no tiene vehículos registrados en su perfil
    When accede a la sección de vehículos
    Then el sistema muestra un <mensaje de aviso>

    Examples:
      | conductor | mensaje de aviso                        |
      | Ana       | "No tienes vehículos registrados aún."  |