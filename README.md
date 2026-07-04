# Calculadora de Expressões Aritméticas — Shunting Yard

Trabalho de Implementação — QXD0010 (Estrutura de Dados) — UFC Quixadá

## Como compilar

Pelo terminal, na pasta `src`:

```bash
cd src
javac calculator/*.java
```

## Como executar

Ainda na pasta `src`:

```bash
java calculator.Calculator
```

O programa fica em loop pedindo expressões. Digite `sair` para encerrar.

## Exemplos de uso

```
Expressão: 9 + 2 * 2
Resultado: 13

Expressão: (9 + 2) * 2
Resultado: 22

Expressão: 10 / 3
Resultado: 3

Expressão: 10 m 3
Resultado: 1

Expressão: 5 / 0
Erro: divisão por zero.

Expressão: (1 + 2
Erro: parênteses desbalanceados ('(' sem ')' correspondente).
```

## Estrutura do projeto

```
src/calculator/
├── Token.java              (representa número, operador ou parêntese)
├── MyStack.java             (pilha genérica própria, lista encadeada)
├── MyQueue.java              (fila genérica própria, lista encadeada)
├── CalculatorException.java  (exceção customizada p/ erros de expressão)
├── Lexer.java                (transforma a string em fila de tokens)
├── ShuntingYard.java          (converte infixa -> pós-fixa/RPN)
├── Evaluator.java             (avalia a expressão pós-fixa)
└── Calculator.java             (main - laço de leitura e impressão)
```

## Como funciona 

1. **Lexer**: lê a expressão caractere a caractere e monta uma `MyQueue<Token>`
   com números, operadores (`+ - * / m`) e parênteses. Caracteres desconhecidos
   geram erro imediatamente.
2. **ShuntingYard**: percorre a fila de tokens infixos usando uma `MyStack<Token>`
   auxiliar de operadores. Números vão direto para a saída; operadores são
   empilhados respeitando precedência (`*`, `/`, `m` > `+`, `-`); parênteses
   controlam quando desempilhar. O resultado é uma fila em notação pós-fixa (RPN).
3. **Evaluator**: percorre a fila pós-fixa usando uma `MyStack<Double>` de
   valores. Números são empilhados; ao encontrar um operador, desempilha os
   dois últimos valores, aplica a operação e empilha o resultado. Ao final,
   deve sobrar exatamente um valor na pilha — esse é o resultado.
4. **Tratamento de erros**: implementado via `CalculatorException`, capturada
   no `main` — cobre operador inválido, parênteses desbalanceados, divisão/
   módulo por zero e expressão malformada (operandos faltando ou sobrando).

## Pontos extras já cobertos

- ✅ Pilha e fila implementadas do zero (`MyStack`, `MyQueue`), sem usar
  `java.util.Stack`/`java.util.Queue`.
- ✅Constantes nomeadas (`pi`)
- ✅Suporte a números negativos/unários (ex: `-5 + 3`)
## Pontos extras que ainda podem ser adicionados 

- Potência (`^`), com precedência maior e associatividade à direita
- Operadores de mais de um caractere (`mod`, `log`)
- Funções (`max(a,b)`)


