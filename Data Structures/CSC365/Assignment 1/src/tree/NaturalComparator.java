package tree;

public class NaturalComparator<T extends Comparable<T>> implements java.util.Comparator<T> {

    public int compare(T lhs, T rhs) {
        return lhs.compareTo(rhs);
    }
}
