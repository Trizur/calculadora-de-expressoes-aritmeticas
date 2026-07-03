package calculator;

/**
 * Implementação própria de uma Fila (FIFO) genérica,
 * usando lista encadeada simples (sem usar java.util.Queue).
 */
public class MyQueue<T> {

    private class No {
        T dado;
        No proximo;
        No(T dado) {
            this.dado = dado;
        }
    }

    private No inicio;
    private No fim;
    private int tamanho;

    public void enqueue(T valor) {
        No novo = new No(valor);
        if (isEmpty()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
        tamanho++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new CalculatorException("Erro interno: tentativa de dequeue em fila vazia.");
        }
        T valor = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return valor;
    }

    public T peek() {
        if (isEmpty()) {
            throw new CalculatorException("Erro interno: tentativa de peek em fila vazia.");
        }
        return inicio.dado;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public int size() {
        return tamanho;
    }
}
