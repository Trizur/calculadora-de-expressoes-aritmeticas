package calculator;

/**
 * Responsável por ler a expressão como texto e transformá-la
 * em uma fila de Tokens (números, operadores e parênteses).
 */
public class Lexer {

    // Operadores de um único caractere aceitos pela calculadora
    private static final String OPERADORES_VALIDOS = "+-*/m";

    public MyQueue<Token> tokenizar(String expressao) {
        MyQueue<Token> tokens = new MyQueue<>();
        int i = 0;
        int n = expressao.length();

        while (i < n) {
            char c = expressao.charAt(i);

            // Ignora espaços em branco
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // Números (aceita inteiros e decimais com ponto)
            if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                boolean pontoEncontrado = false;

                while (i < n && (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                    char atual = expressao.charAt(i);
                    if (atual == '.') {
                        if (pontoEncontrado) {
                            throw new CalculatorException(
                                "Erro: número inválido perto da posição " + i + " (ponto decimal duplicado).");
                        }
                        pontoEncontrado = true;
                    }
                    numero.append(atual);
                    i++;
                }

                try {
                    tokens.enqueue(new Token(Double.parseDouble(numero.toString())));
                } catch (NumberFormatException e) {
                    throw new CalculatorException("Erro: número inválido '" + numero + "'.");
                }
                continue;
            }

            // Parênteses
            if (c == '(') {
                tokens.enqueue(new Token(Token.Tipo.PARENTESE_ESQ, "("));
                i++;
                continue;
            }
            if (c == ')') {
                tokens.enqueue(new Token(Token.Tipo.PARENTESE_DIR, ")"));
                i++;
                continue;
            }
            if (c == 'p') {
                if(i + 1 < n && expressao.charAt(i + 1) == 'i') {
                  tokens.enqueue(new Token(3.14159265));
                    i += 2; // Avança dois caracteres
                    continue;
                } else {
                    throw new CalculatorException(
                        "Erro: caractere inválido encontrado: '" + c + "' na posição " + i + ".");
                }
            }

            // Operadores válidos
            if (OPERADORES_VALIDOS.indexOf(c) >= 0) {
                tokens.enqueue(new Token(Token.Tipo.OPERADOR, String.valueOf(c)));
                i++;
                continue;
            }

            // Qualquer outro caractere é inválido
            throw new CalculatorException(
                "Erro: caractere/operador inválido encontrado: '" + c + "' na posição " + i + ".");
        }

        return tokens;
    }
}
