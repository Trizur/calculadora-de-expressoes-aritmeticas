package calculator;

/**
 * Avalia uma expressão em notação pós-fixa (RPN) usando uma pilha de valores.
 */
public class Evaluator {

    public double avaliar(MyQueue<Token> posfixa) {
        MyStack<Double> pilhaValores = new MyStack<>();

        while (!posfixa.isEmpty()) {
            Token token = posfixa.dequeue();

            if (token.getTipo() == Token.Tipo.NUMERO) {
                pilhaValores.push(token.getNumero());

            } else if (token.getTipo() == Token.Tipo.OPERADOR) {

                if (token.getValor().equals("u-")) {
                    // Operador unário: só precisa de 1 operando
                    if (pilhaValores.size() < 1) {
                        throw new CalculatorException(
                            "Erro: expressão inválida (falta operando para a negação '-').");
                    }
                    double a = pilhaValores.pop();
                    pilhaValores.push(-a);
                    continue;
                }

                // Operadores binários: precisam de 2 operandos
                if (pilhaValores.size() < 2) {
                    throw new CalculatorException(
                        "Erro: expressão inválida (faltam operandos para o operador '"
                        + token.getValor() + "').");
                }
                double b = pilhaValores.pop();
                double a = pilhaValores.pop();
                pilhaValores.push(aplicarOperador(token.getValor(), a, b));

            } else {
                throw new CalculatorException("Erro: token inesperado na expressão pós-fixa.");
            }
        }

        if (pilhaValores.size() != 1) {
            throw new CalculatorException("Erro: expressão inválida (operandos/operadores não se equilibram).");
        }

        return pilhaValores.pop();
    }

    private double aplicarOperador(String operador, double a, double b) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if ((long) b == 0) {
                    throw new CalculatorException("Erro: divisão por zero.");
                }
                return (long) a / (long) b; // divisão inteira, conforme especificação
            case "m":
                if ((long) b == 0) {
                    throw new CalculatorException("Erro: módulo por zero.");
                }
                return (long) a % (long) b; // resto da divisão inteira
            case "^":
                return Math.pow(a, b);
            default:
                throw new CalculatorException("Erro: operador desconhecido '" + operador + "'.");
        }
    }
}