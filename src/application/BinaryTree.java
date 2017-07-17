package application;

import interfaces.IBinaryTree;

/**
 * Created by nicholas on 6/28/17.
 */
public class BinaryTree<E> implements IBinaryTree<E> {

    private static Node root;
    private int size;

    public BinaryTree(){
        this.root = null;
    }

    @Override
    public void add(E element, int ID) {
        Node newNode = new Node(element, ID);
        size++;
        if(root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;


        // x > 0: right
        // x < 0: left


        while(true){
            parent = current;
            if(Float.parseFloat(String.valueOf(element)) < Float.parseFloat(current.getValue().toString())){
                //go right
                current = current.getLeft();
                if(current == null){
                    parent.setLeft(newNode);
                    return;
                }
            }else{
                //go left
                current = current.getRight();
                if(current == null){
                    parent.setRight(newNode);
                    return;
                }
            }
        }

    }

    public void add(E element) {
        Node newNode = new Node(element, 0);
        size++;
        if(root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;


        // x > 0: right
        // x < 0: left


        while(true){
            parent = current;
            if(Float.parseFloat(String.valueOf(element)) < Float.parseFloat(current.getValue().toString())){
                //go right
                current = current.getLeft();
                if(current == null){
                    parent.setLeft(newNode);
                    return;
                }
            }else{
                //go left
                current = current.getRight();
                if(current == null){
                    parent.setRight(newNode);
                    return;
                }
            }
        }

    }


    @Override
    public Node remove(E element) {

        Node current = get(element);
        Node parent = getParent(element);
        boolean isLeft, isRight = false;
        if(root == null)
            return null;


        if(current == root)
            root = null;


        //No children
        if(current.getRight() == null && current.getLeft() == null)
            current = null;


        isLeft = Float.parseFloat(current.getValue().toString()) < Float.parseFloat(getParent(element).getValue().toString());
        isRight = Float.parseFloat(current.getValue().toString()) > Float.parseFloat(getParent(element).getValue().toString());

        //One node
        if(current.getLeft() == null){
            if(isLeft){
                parent.setLeft(current.getRight());
            }else{
                parent.setRight(current.getRight());
            }
        }else if(current.getRight() == null){
            if(isRight){
                parent.setRight(current.getLeft());
            }else{
                parent.setLeft(current.getLeft());
            }
        }else if(current.getRight() != null && current.getLeft() != null){
            parent = getParent(element);
            if(isLeft){
                //parent.getLeft()
            }
        }
        return null;
    }


    public Node getParent(E element){

        //cannot get parent of the beginning of the tree
        if(get(element) == root)
            return null;

        if(root == null)
            return null;


        Node current = root;
        Node parent = root;
        while(current != null){
            if(current.getValue().equals(element)) {
                return parent;
            }else if(Float.parseFloat(String.valueOf(element)) < Float.parseFloat(current.getValue().toString())){
                parent = current;
                current = current.getLeft();
            }else{
                parent = current;
                current = current.getRight();
            }

        }

        return null;
    }

    @Override
    public boolean contains(E element) {
        Node current = root;
        while(current != null){
            if(current.getValue().equals(element)){
                return true;
            }else if(Float.parseFloat(String.valueOf(element)) < Float.parseFloat(current.getValue().toString())){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node node){
        if(node == null)
            return 0;
        else
            return(size(node.getLeft()) + 1 + size(node.getRight()));
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Node get(E element) {
        Node current = root;
        while(current != null){
            if(current.getValue().equals(element)) {
                return current;
            }else if(Float.parseFloat(String.valueOf(element)) < Float.parseFloat(current.getValue().toString())){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }

        }
        return null;
    }

    @Override
    public void reset() {
        root = null;
    }

    @Override
    public Node getNext(E element) {
        Node current = get(element).getRight();
        Node next = null;
        Node parent = null;

        while(current != null){
            parent = next;
            next = current;
            current = current.getLeft();
        }


        return next;
    }

    @Override
    public String display() {
        toString(root);
        return null;
    }

    public String toString(Node root) {
        if(root != null) {
            toString(root.getLeft());
            System.out.println("" + root.getValue());
            toString(root.getRight());
        }
        return null;
    }


    public static void main(String args[]){
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(3);
        tree.add(8);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(2);
        tree.add(10);
        tree.add(9);
        tree.add(20);
        tree.add(25);
        tree.add(15);
        tree.add(16);
        tree.add(14);
        System.out.println(tree.getParent(14).getValue());
        tree.remove(15);
        System.out.println(tree.getParent(14).getValue());



    }
}
