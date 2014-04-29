package tree;
import java.util.Comparator;
import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {

    //Instance Variables
    Node<T> root;
    Comparator<T> cmp;
    int size;
    public BinaryTree(Comparator<T> cmp) {
        root = null;
        this.cmp = cmp;
        size = 0;
    }

    public BinaryTree() {
        root = null;
        this.cmp = new NaturalComparator<T>();
        size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public boolean add(T key) {
    	boolean to_return = false;
    	if(root == null) {
    		root = new Node<T>(key);
    		size = 1;
    	} else {
    		to_return = add(root, key);
    	}
        return to_return;
    }

    private boolean add(Node<T> node, T key) {
        boolean to_return = false;
        int cmp1 = cmp.compare(node.key, key);
        if(cmp1 == 0) {
        	node.count += 1;
        	to_return = true;
        } else if(cmp1 < 0) {
        	if(node.left == null) {
        		node.left = new Node<T>(key);
        		size += 1;
        		to_return = true;
        	} else {
        		to_return = add(node.left, key);
        	}
        } else if(cmp1 > 0) {
        	if(node.right == null) {
        		node.right = new Node<T>(key);
        		size += 1;
        		to_return = true;
        	} else {
        		to_return = add(node.right, key);
        	}
        }
        return to_return;
    }

    public boolean remove(T key) {
        boolean to_return = false;
        int cmp1 = cmp.compare(root.key, key);
        if(cmp1 == 0) {
            if(root.isLeaf()) {
                root = null;
                size = 0;
                to_return = true;
            } else {
                root = successor(root);
                size -= 1;
                to_return = true;
            }
        } else {
            to_return = remove(root, key);
        }
        return to_return;
    }

    private boolean remove(Node<T> node, T key) {
        boolean to_return = false;
        Node<T> child = null;
        int cmp1 = cmp.compare(node.key, key);

        if(cmp1 < 0) {
            child = node.left;
        } else if(cmp1 > 0) {
            child = node.right;
        }

        if(child != null) {
            int cmp2 = cmp.compare(child.key, key);
            if(cmp2 == 0) {
                if(child.isLeaf()) {
                    if(cmp1 < 0) {
                        node.left = null;
                        size -= 1;
                        to_return = true;
                    } else if(cmp1 > 0) {
                        node.right = null;
                        size -= 1;
                        to_return = true;
                    }
                } else {
                    if(cmp1 < 0) {
                        node.left = successor(child);
                        size -= 1;
                        to_return = true;
                    } else if(cmp1 > 0) {
                        node.right = successor(child);
                        size -= 1;
                        to_return = true;
                    }
                }
            } else if(cmp2 < 0) {
                if(child.left == null) {
                    to_return = false;
                } else {
                    to_return = remove(child, key);
                }
            } else if(cmp2 > 0) {
                if(child.right == null) {
                    to_return = false;
                } else {
                    to_return = remove(child, key);
                }
            }
        }
        return to_return;
    }

    public Node<T> successor(Node<T> node) {
        return null;
    }
    
    public Node<T> getNode(T key) {
    	return getNode(root, key);
    }
    
    private Node<T> getNode(Node<T> node, T key) {
    	Node<T> to_return = null;
    	int cmp1 = cmp.compare(node.key, key);
    	if(cmp1 == 0) {
    		to_return = node;
    	} else if(cmp1 < 0) {
    		to_return = getNode(node.left, key);
    	} else if(cmp1 > 0) {
    		to_return = getNode(node.right, key);
    	}
    	return to_return;
    }

    public T getMin() {
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        T to_return = null;
        if(node.left == null) {
            to_return = node.key;
        } else {
            to_return = getMin(node.left);
        }
        return to_return;
    }

    public T getMax() {
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        T to_return = null;
        if(node.right == null) {
            to_return = node.key;
        } else {
            to_return = getMax(node.right);
        }
        return to_return;
    }

    public T getMinCount() {
        return getMinCount(root, root.key, root.count);
    }

    private T getMinCount(Node<T> node, T min, int count) {
        if(node.left != null) {
            min = getMinCount(node.left, min, count);
        }
        if(node.count < count) {
            min = node.key;
            count = node.count;
        }

        if(node.right != null) {
            min = getMinCount(node.right, min, count);
        }
        return min;
    }


    public T getMaxCount() {
        return getMaxCount(root, root.key, root.count);
    }

    private T getMaxCount(Node<T> node, T max, int count) {
        if(node.left != null) {
            max = getMinCount(node.left, max, count);
        }
        if(node.count > count) {
            max = node.key;
            count = node.count;
        }

        if(node.right != null) {
            max = getMinCount(node.right, max, count);
        }
        return max;
    }

    public ArrayList<Node<T>> getInOrderArray() {
        ArrayList<Node<T>> to_return = new ArrayList<Node<T>>();
        to_return =  getInOrderArray(to_return, root);
        return to_return;
    }

    private ArrayList<Node<T>> getInOrderArray(ArrayList<Node<T>> nodes, Node<T> node) {
        if(node.left != null) {
           nodes = getInOrderArray(nodes,node.left);
        }
        nodes.add(node);
        if(node.right != null) {
            nodes = getInOrderArray(nodes, node.right);
        }
        return nodes;
    }


    
    
}
