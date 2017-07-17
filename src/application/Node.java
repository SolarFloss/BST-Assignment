package application;

/**
 * Created by nicholas on 6/28/17.
 */
public class Node<E> {
    private E value;
    private Node left;
    private Node right;
    private int RID;

    public Node(E data, int ID){
        this.value = data;
        this.left = null;
        this.right = null;
        this.RID = ID;
    }

    public int getRID(){ return RID; }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public E getValue(){
        return value;
    }

    public void setValue(E value){
        this.value = value;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setLeft(Node left){
        this.left = left;
    }


}
