Feature: US24 Modificación de cochera
  Como propietario quiero modificar los datos de mi cochera para actualizar la información.

  Scenario Outline: Modificar datos de una cochera existente
    Given el <propietario> tiene una cochera registrada
    And accede a la opción de editar cochera
    When actualiza los <nuevos datos> y guarda los cambios
    Then el sistema actualiza la información de la cochera

    Examples:
      | propietario | nuevos datos                        |
      | Juan        | precio: $120/día, techada: sí       |

  Scenario Outline: Fallar al modificar cochera con datos inválidos
    Given el <propietario> tiene una cochera registrada
    And accede a la opción de editar cochera
    When ingresa <datos inválidos> y guarda los cambios
    Then el sistema muestra un <mensaje de error>

    Examples:
      | propietario | datos inválidos      | mensaje de error                  |
      | Juan        | precio negativo      | "El precio debe ser mayor a cero" |