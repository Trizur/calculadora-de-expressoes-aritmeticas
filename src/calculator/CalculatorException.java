package calculator;

/**
 * Exceção lançada quando a expressão é inválida ou ocorre algum erro
 * durante o processamento (tokenização, conversão ou avaliação).
 */
public class CalculatorException extends RuntimeException {
    public CalculatorException(String mensagem) {
        super(mensagem);
    }
}
