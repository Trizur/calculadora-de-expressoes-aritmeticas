package calculator;

/**
 * Implementação própria de uma Pilha (LIFO) genérica,
 * usando lista encadeada simples (sem usar java.util.Stack).
 */
public class MyStack<T> {

    private class No {
        T dado;
        No proximo;
        No(T dado) {
            this.dado = dado;
        }
    }

    private No topo;
    private int tamanho;

    public void push(T valor) {
        No novo = new No(valor);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new CalculatorException("Erro interno: tentativa de pop em pilha vazia.");
        }
        T valor = topo.dado;
        topo = topo.proximo;
        tamanho--;
        return valor;
    }

    public T peek() {
        if (isEmpty()) {
            throw new CalculatorException("Erro interno: tentativa de peek em pilha vazia.");
        }
        return topo.dado;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public int size() {
        return tamanho;
    }
}
