package data;
import tree.Node;
import java.util.Comparator;

public class NodeCountComparator<T> implements Comparator<Node<T>> {

    public int compare(Node<T> lhs, Node<T> rhs) {
        int to_return = -3;
        int lcnt = lhs.getCount();
        int rcnt = rhs.getCount();

        if(lcnt == rcnt) {
            to_return = 0;
        } else if(lcnt < rcnt) {
            to_return = -1;
        } else if(rcnt > lcnt) {
            to_return = 1;
        }
        return to_return;
    }
}
