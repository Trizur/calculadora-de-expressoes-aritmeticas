package calculator;

/**
 * Representa um token da expressão: um número, um operador ou um parêntese.
 */
public class Token {

    public enum Tipo { NUMERO, OPERADOR, PARENTESE_ESQ, PARENTESE_DIR }

    private final Tipo tipo;
    private final String valor;   // texto do operador/parêntese (ex: "+", "m", "(")
    private final double numero;  // valor numérico, usado só quando tipo == NUMERO

    // Construtor para operadores e parênteses
    public Token(Tipo tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.numero = 0;
    }

    // Construtor para números
    public Token(double numero) {
        this.tipo = Tipo.NUMERO;
        this.valor = null;
        this.numero = numero;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public double getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return tipo == Tipo.NUMERO ? String.valueOf(numero) : valor;
    }
}
