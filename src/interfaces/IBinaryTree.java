package interfaces;

import application.Node;
import exceptions.StackUnderflowException;

/**
 * Created by nicholas on 6/28/17.
 */
public interface IBinaryTree<E> {
    void add(E element, int ID);

    //Underflow exception
    Node remove(E element) throws StackUnderflowException;

    boolean contains(E element);

    int size();

    boolean isEmpty();

    String toString();

    Node get(E element) throws NullPointerException;

    void reset();

    Node getNext(E element);

    String display();
}
