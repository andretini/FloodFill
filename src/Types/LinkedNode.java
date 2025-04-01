package Types;

public class LinkedNode<T> {
    T value;
    LinkedNode<T> previous = null;
    LinkedNode<T> next = null;

    LinkedNode(T value){
        this.value = value;
    }

    LinkedNode(T value, LinkedNode<T> link, int type){
        if (type == 1){
            this.value = value;
            this.previous = link;
        }
        else{
            this.value = value;
            this.next = link;
        }
    }
}
