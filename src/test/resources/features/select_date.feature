# language: es
Característica: HU-001 - Selección de fecha en un campo de formulario
  Como usuario del sistema
  Quiero seleccionar una fecha específica en el datepicker
  Para poder ingresar fechas de manera visual e intuitiva

  Antecedentes:
    Dado que el usuario navega a la página del datepicker

  @smoke @datepicker
  Escenario: Seleccionar una fecha específica en un mes diferente al actual
    Cuando el usuario selecciona la fecha "10" del mes "6" del año "2025"
    Entonces la fecha seleccionada no debe estar vacía
    Y la fecha seleccionada debe tener el formato correcto "MM/DD/YYYY"

  @smoke @datepicker
  Escenario: Validación del formato de fecha seleccionada
    Cuando el usuario selecciona la fecha "25" del mes "6" del año "2025"
    Entonces la fecha seleccionada debe ser "06/25/2025"
    Y la fecha debe tener el formato correcto

  @datepicker @regression
  Esquema del escenario: Seleccionar múltiples fechas
    Cuando el usuario selecciona la fecha "<dia>" del mes "<mes>" del año "<año>"
    Entonces la fecha seleccionada debe ser "<fecha_esperada>"

    Ejemplos:
      | dia | mes | año  | fecha_esperada |
      | 15  | 8   | 2025 | 08/15/2025     |
      | 1   | 1   | 2026 | 01/01/2026     |
      | 31  | 12  | 2024 | 12/31/2024     |
      | 14  | 2   | 2025 | 02/14/2025     |