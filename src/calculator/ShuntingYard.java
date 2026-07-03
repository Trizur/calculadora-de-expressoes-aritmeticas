package calculator;

/**
 * Implementa o algoritmo Shunting Yard: converte uma fila de tokens
 * em notação infixa para uma fila de tokens em notação pós-fixa (RPN),
 * respeitando precedência de operadores e parênteses.
 */
public class ShuntingYard {

    // Quanto maior o número, maior a precedência
    private int precedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "m":
                return 2;
            default:
                throw new CalculatorException("Erro: operador desconhecido '" + operador + "'.");
        }
    }

    public MyQueue<Token> converterParaPosFixa(MyQueue<Token> tokensInfixos) {
        MyQueue<Token> saida = new MyQueue<>();
        MyStack<Token> pilhaOperadores = new MyStack<>();

        while (!tokensInfixos.isEmpty()) {
            Token token = tokensInfixos.dequeue();

            switch (token.getTipo()) {

                case NUMERO:
                    saida.enqueue(token);
                    break;

                case OPERADOR:
                    // Desempilha operadores de precedência >= à do token atual
                    // (todos os operadores aqui são associativos à esquerda)
                    while (!pilhaOperadores.isEmpty()
                            && pilhaOperadores.peek().getTipo() == Token.Tipo.OPERADOR
                            && precedencia(pilhaOperadores.peek().getValor()) >= precedencia(token.getValor())) {
                        saida.enqueue(pilhaOperadores.pop());
                    }
                    pilhaOperadores.push(token);
                    break;

                case PARENTESE_ESQ:
                    pilhaOperadores.push(token);
                    break;

                case PARENTESE_DIR:
                    boolean achouParenteseAbre = false;
                    while (!pilhaOperadores.isEmpty()) {
                        Token topo = pilhaOperadores.pop();
                        if (topo.getTipo() == Token.Tipo.PARENTESE_ESQ) {
                            achouParenteseAbre = true;
                            break;
                        }
                        saida.enqueue(topo);
                    }
                    if (!achouParenteseAbre) {
                        throw new CalculatorException(
                            "Erro: parênteses desbalanceados (') ' sem '(' correspondente).");
                    }
                    break;
            }
        }

        // Esvazia o restante da pilha de operadores
        while (!pilhaOperadores.isEmpty()) {
            Token topo = pilhaOperadores.pop();
            if (topo.getTipo() == Token.Tipo.PARENTESE_ESQ) {
                throw new CalculatorException(
                    "Erro: parênteses desbalanceados ('(' sem ')' correspondente).");
            }
            saida.enqueue(topo);
        }

        return saida;
    }
}
