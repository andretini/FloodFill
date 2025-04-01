package Types;

public class Fila<T> {
    private LinkedNode<T> inicio = null;
    private LinkedNode<T> topo = null;

    public boolean empty = true;

    public void queue(T data) {
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (inicio == null) {
            inicio = newNode;
            topo = newNode;
        } else {
            topo.next = newNode;
            topo = newNode;
        }
        empty = false;
    }

    public T dequeue() {
        if (inicio == null) {
            empty = true;
            return null;
        }

        LinkedNode<T> temp = inicio;
        inicio = inicio.next;

        if (inicio == null) {
            empty = true;
            topo = null;
        }

        return temp.value;
    }
    public boolean isEmpty() {
        return empty; // Returns true if stack is empty
    }
}
