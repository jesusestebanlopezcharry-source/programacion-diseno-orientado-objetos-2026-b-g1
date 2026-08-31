## Design decisions
The `CuentaBancaria` class uses encapsulation by keeping its attributes private and providing public methods to access or modify the account data. The `saldo` attribute follows an invariant that it can never be negative, so the constructors and withdrawal method validate the balance before making changes. The `depositar` and `retirar` methods also validate that the amounts are greater than zero to prevent invalid transactions. The `equals()` method considers two bank accounts equal when they have the same account number, regardless of the account holder or balance. The `hashCode()` method is implemented using the same account number used by `equals()`, maintaining the required equals/hashCode contract. The `toString()` method provides a readable representation of the account and its current information.

# Cuenta Bancaria - Programación Orientada a Objetos

## Descripción

Este proyecto implementa una clase `CuentaBancaria` utilizando conceptos fundamentales de Programación Orientada a Objetos en Java.

La clase permite crear cuentas bancarias, realizar depósitos y retiros, consultar información de la cuenta y comparar cuentas mediante su número de cuenta.

El proyecto también incluye validaciones para garantizar que el saldo de una cuenta nunca sea negativo y que no se puedan realizar operaciones con montos menores o iguales a cero.

---

## Estructura del proyecto

```text
CuentaBancaria/
│
├── Main.java
└── README.md
```

---

## Funcionalidades

El programa implementa las siguientes funcionalidades:

* Crear una cuenta bancaria.
* Registrar el nombre del titular.
* Registrar un número de cuenta.
* Establecer un saldo inicial.
* Validar que el saldo inicial no sea negativo.
* Depositar dinero.
* Validar que el depósito sea mayor que cero.
* Retirar dinero.
* Validar que el retiro sea mayor que cero.
* Evitar retiros superiores al saldo disponible.
* Consultar el titular de la cuenta.
* Consultar el saldo.
* Consultar el número de cuenta.
* Comparar cuentas mediante `equals()`.
* Generar un `hashCode()` coherente con `equals()`.
* Mostrar la información de la cuenta mediante `toString()`.

---

## Clase CuentaBancaria

La clase `CuentaBancaria` contiene tres atributos privados:

```java
private String titular;
private double saldo;
private String numeroCuenta;
```

Los atributos son privados para aplicar el principio de **encapsulación**, evitando que puedan ser modificados directamente desde otras clases.

---

## Constructores

El programa cuenta con dos constructores.

### Constructor completo

Permite crear una cuenta indicando titular, saldo inicial y número de cuenta.

```java
public CuentaBancaria(String titular, double saldoInicial, String numeroCuenta)
```

El constructor valida que el saldo inicial no sea negativo.

### Constructor alternativo

Permite crear una cuenta indicando solamente el titular y el número de cuenta.

```java
public CuentaBancaria(String titular, String numeroCuenta)
```

Este constructor utiliza `this(...)` para llamar al constructor completo y establecer automáticamente el saldo inicial en `0`.

---

## Depósito

El método `depositar()` permite agregar dinero al saldo de la cuenta.

```java
public void depositar(double monto)
```

El método valida que el monto sea mayor que cero.

Si se intenta depositar `0` o un valor negativo, el programa genera una excepción `IllegalArgumentException`.

---

## Retiro

El método `retirar()` permite retirar dinero de la cuenta.

```java
public void retirar(double monto)
```

El método realiza dos validaciones:

1. El monto debe ser mayor que cero.
2. El monto no puede ser superior al saldo disponible.

De esta manera se garantiza que el saldo de la cuenta nunca sea negativo.

---

## Encapsulación

Los atributos de la clase son privados:

```java
private String titular;
private double saldo;
private String numeroCuenta;
```

Para consultar sus valores se utilizan métodos `getter`:

```java
getTitular()
getSaldo()
getNumeroCuenta()
```

El saldo no tiene un método `setter`, ya que debe modificarse únicamente mediante las operaciones `depositar()` y `retirar()`. Esto permite controlar las modificaciones y mantener la regla de que el saldo nunca sea negativo.

---

## toString()

Se sobrescribe el método `toString()` para mostrar la información de una cuenta de manera legible.

Ejemplo:

```text
CuentaBancaria{titular='Ana', saldo=500.0, numeroCuenta='ABC123'}
```

---

## equals() y hashCode()

El método `equals()` permite determinar si dos objetos `CuentaBancaria` representan la misma cuenta.

En este proyecto, dos cuentas son consideradas iguales cuando tienen el mismo número de cuenta.

Por ejemplo:

```text
Cuenta 1 → ABC123
Cuenta 2 → ABC123

Resultado: true
```

Mientras que:

```text
Cuenta 1 → ABC123
Cuenta 3 → XYZ999

Resultado: false
```

El método `hashCode()` utiliza el mismo número de cuenta empleado por `equals()`, manteniendo así la coherencia entre ambos métodos.

---

## Pruebas realizadas

En el método `main()` se realizan diferentes pruebas.

### Prueba de equals()

Se crean dos cuentas con el mismo número de cuenta:

```java
CuentaBancaria c1 = new CuentaBancaria("Ana", 500, "ABC123");
CuentaBancaria c2 = new CuentaBancaria("Pedro", 1000, "ABC123");
```

El resultado esperado es:

```text
c1.equals(c2): true
```

También se crea una cuenta con un número diferente:

```java
CuentaBancaria c3 = new CuentaBancaria("Luis", 300, "XYZ999");
```

El resultado esperado es:

```text
c1.equals(c3): false
```

### Prueba de depósito

Se realiza un depósito de `200`:

```java
c1.depositar(200);
```

El saldo pasa de:

```text
500
```

a:

```text
700
```

### Prueba de retiro

Posteriormente se retiran `100`:

```java
c1.retirar(100);
```

El saldo pasa de:

```text
700
```

a:

```text
600
```

### Prueba de depósito inválido

Se intenta realizar un depósito de `0`:

```java
c1.depositar(0);
```

El programa captura la excepción y muestra el mensaje correspondiente.

### Prueba de retiro inválido

Se intenta retirar `10000`, aunque el saldo disponible es menor:

```java
c1.retirar(10000);
```

El programa evita que el saldo se vuelva negativo y muestra un mensaje de error.

### Prueba de saldo inicial negativo

También se intenta crear una cuenta con un saldo inicial de `-50`.

```java
new CuentaBancaria("Mario", -50, "NEG001");
```

El programa rechaza la operación mediante una `IllegalArgumentException`.

---

## Design decisions

The `CuentaBancaria` class uses encapsulation by keeping its attributes private and providing public methods to access or modify the account data. The `saldo` attribute follows an invariant that it can never be negative, so the constructors and withdrawal method validate the balance before making changes. The `depositar` and `retirar` methods also validate that the amounts are greater than zero to prevent invalid transactions. The `equals()` method considers two bank accounts equal when they have the same account number, regardless of the account holder or balance. The `hashCode()` method is implemented using the same account number used by `equals()`, maintaining the required equals/hashCode contract. The `toString()` method provides a readable representation of the account and its current information.

---

## Tecnologías utilizadas

* Java
* Programación Orientada a Objetos
* Encapsulación
* Constructores
* Sobrescritura de métodos
* Excepciones (`IllegalArgumentException`)
* `equals()`
* `hashCode()`
* `toString()`

---

## Conclusión

Este proyecto permite aplicar conceptos fundamentales de Programación Orientada a Objetos mediante la implementación de una cuenta bancaria. Se utilizaron atributos privados para garantizar la encapsulación, constructores para inicializar los objetos y métodos con validaciones para mantener el saldo siempre mayor o igual a cero. También se implementaron `toString()`, `equals()` y `hashCode()` para representar, comparar y manejar correctamente los objetos `CuentaBancaria`.
