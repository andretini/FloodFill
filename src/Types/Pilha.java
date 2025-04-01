package Types;

public class Pilha<T> {
    private LinkedNode<T> topo = null;

    // Push data onto the stack
    public void push(T data) {
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.previous = topo; // Link to the previous top
        topo = newNode; // Update the top of the stack
    }

    // Pop data from the stack
    public T pop() {
        if (topo == null) {
            return null; // Stack is empty, nothing to pop
        }

        LinkedNode<T> temp = topo;
        topo = temp.previous; // Update the top to the previous element
        return temp.value; // Return the popped value
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return topo == null; // Returns true if stack is empty
    }
}
