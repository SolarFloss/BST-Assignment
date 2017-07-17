package interfaces;

import application.Node;

/**
 * Created by nicholas on 6/28/17.
 */
public interface IBinaryTree<E> {
    void add(E element, int ID);

    Node remove(E element);

    boolean contains(E element);

    int size();

    boolean isEmpty();

    String toString();

    Node get(E element);

    void reset();

    Node getNext(E element);

    String display();
}
