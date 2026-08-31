// ===============================================
// PUNTO 1: Clase CuentaBancaria con atributos privados e invariante saldo ≥ 0
// ===============================================
class CuentaBancaria {

    private String titular;        // Atributo privado: nombre del titular
    private double saldo;          // Atributo privado: saldo (nunca negativo)
    private String numeroCuenta;   // Identificador único de la cuenta

    // ===============================================
    // PUNTO 2: Constructores (uno completo y otro con this(...))
    // ===============================================

    // Constructor completo: recibe titular, saldo inicial y número de cuenta
    public CuentaBancaria(String titular, double saldoInicial, String numeroCuenta) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
    }

    // Constructor alternativo: usa this(...) para saldo inicial = 0
    public CuentaBancaria(String titular, String numeroCuenta) {
        this(titular, 0, numeroCuenta);
    }

    // ===============================================
    // PUNTO 2: Métodos depositar/retirar con validaciones
    // ===============================================

    // Método para depositar dinero (no permite montos ≤ 0)
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero");
        }
        this.saldo += monto;
    }

    // Método para retirar dinero (no permite montos ≤ 0 ni saldo negativo)
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que cero");
        }
        if (monto > this.saldo) {
            throw new IllegalArgumentException("Fondos insuficientes para realizar el retiro");
        }
        this.saldo -= monto;
    }

    // Getters para acceder a los atributos privados
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // ===============================================
    // PUNTO 3: Sobrescribir toString(), equals() y hashCode()
    // ===============================================

    // Representación legible de la cuenta
    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                '}';
    }

    // Igualdad basada en número de cuenta
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        CuentaBancaria otra = (CuentaBancaria) obj;
        return this.numeroCuenta.equals(otra.numeroCuenta);
    }

    // hashCode coherente con equals
    @Override
    public int hashCode() {
        return numeroCuenta.hashCode();
    }
}

// ===============================================
// PUNTO 4: Main que demuestra validaciones y equals()
// ===============================================
public class Main {

    public static void main(String[] args) {

        // Crear dos cuentas con el mismo número → deben ser iguales
        CuentaBancaria c1 = new CuentaBancaria("Ana", 500, "ABC123");
        CuentaBancaria c2 = new CuentaBancaria("Pedro", 1000, "ABC123");

        // Crear una cuenta diferente
        CuentaBancaria c3 = new CuentaBancaria("Luis", 300, "XYZ999");

        // Prueba de equals()
        System.out.println("=== Prueba equals() ===");
        System.out.println("c1.equals(c2): " + c1.equals(c2)); // true
        System.out.println("c1.equals(c3): " + c1.equals(c3)); // false

        // Prueba de toString()
        System.out.println("\n=== Prueba toString() ===");
        System.out.println(c1);
        System.out.println(c3);

        // Prueba de validaciones
        c1.depositar(200); // Depósito válido
        System.out.println("Saldo después de depositar 200: " + c1.getSaldo());

        c1.retirar(100);   // Retiro válido
        System.out.println("Saldo después de retirar 100: " + c1.getSaldo());

        // Depósito inválido
        try {
            c1.depositar(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al depositar: " + e.getMessage());
        }

        // Retiro inválido
        try {
            c1.retirar(10000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        }

        // Creación de cuenta con saldo negativo
        try {
            CuentaBancaria c4 = new CuentaBancaria("Mario", -50, "NEG001");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
        }
    }
}