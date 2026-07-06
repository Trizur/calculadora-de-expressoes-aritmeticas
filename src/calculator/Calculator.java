package calculator;

import java.util.Scanner;

/**
 * Classe principal: lê expressões digitadas pelo usuário, processa
 * e imprime o resultado (ou uma mensagem de erro clara).
 */
public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lexer lexer = new Lexer();
        ShuntingYard shuntingYard = new ShuntingYard();
        Evaluator evaluator = new Evaluator();

        System.out.println("=== Calculadora de Expressões Aritméticas (Shunting Yard) ===");
        System.out.println("Operadores suportados: + - * / m (resto da divisão inteira) ^ (potenciação)");
        System.out.println("Parênteses são suportados para agrupamento.");
        System.out.println("Digite 'sair' para encerrar.\n");

        while (true) {
            System.out.print("Expressão: ");
            String linha = scanner.nextLine();

            if (linha.trim().equalsIgnoreCase("sair")) {
                break;
            }
            if (linha.trim().isEmpty()) {
                continue;
            }

            try {
                MyQueue<Token> tokensInfixos = lexer.tokenizar(linha);
                MyQueue<Token> tokensPosFixos = shuntingYard.converterParaPosFixa(tokensInfixos);
                double resultado = evaluator.avaliar(tokensPosFixos);

                if (resultado == (long) resultado) {
                    System.out.println("Resultado: " + (long) resultado);
                } else {
                    System.out.println("Resultado: " + resultado);
                }

            } catch (CalculatorException e) {
                // Alerta de erro tratado (requisito obrigatório 1.1.3)
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
            System.out.println();
        }

        scanner.close();
        System.out.println("Encerrando calculadora.");
    }
}
