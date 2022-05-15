package fri.tik.seznam;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinomskaKopica<Tip extends Comparable> implements Seznam<Tip>{
    private BKNode top;
    private int eltCount;

    class BKNode {
        BKNode parent;
        Tip value;
        int degree;
        BKNode child;
        BKNode sibling;

        public BKNode(Tip e) {
            value = e;
        }
    }

    @Override
    public void add(Tip e) {
        BKNode newTop = new BKNode(e);
        newTop.sibling = top;
        top = newTop;
        eltCount++;
        consolidate();
    }

    private void consolidate() {
        BKNode x = top, y, z;
        while (/*x != null && */x.sibling != null) {
            y = x.sibling;
            //z = y.sibling;
            if(x.degree != y.degree) {
                x = y;
                continue;
            }
            /*
            if(z != null && y.degree == z.degree) {
                x = y;
                continue;
            }

             */
            if (x.value.compareTo(y.value) <= 0) {
                //boolean wasTop = top == x;
                x = merge(x,y);
                /*if (wasTop)*/ top = x;
            } else {
                //boolean wasTop = top == x;
                BKNode newSib = y.sibling;
                x = merge(y, x);
                x.sibling = newSib;
                /*if (wasTop)*/ top = x;
            }
        }
    }

    private BKNode merge(BKNode s, BKNode b) {
        s.sibling = b.child;
        b.child = s;
        s.parent = b;
        b.degree++;
        return b;
    }

    @Override
    public Tip removeFirst() {
        throw new UnsupportedOperationException("Unsupported operation on BinomskaKopica");
    }

    @Override
    public Tip getFirst() {
        if(top == null) throw new NoSuchElementException();
        Tip maxV = top.value;
        BKNode i = top;
        while (i.sibling != null) {
            if(i.sibling.value.compareTo(maxV) > 0) maxV = i.sibling.value;
            i = i.sibling;
        }
        return maxV;
    }

    @Override
    public int size() {
        return eltCount;
    }

    @Override
    public int depth() {
        if(top == null) return 0;
        BKNode i = top;
        while (i.sibling != null) i = i.sibling;
        return i.degree + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Tip remove(Tip e) {
        throw new UnsupportedOperationException("Unsupported operation on BinomskaKopica");
    }

    @Override
    public boolean exists(Tip e) {
        return existsRec(top, e);
    }

    private boolean existsRec(BKNode node, Tip e) {
        BKNode i = node;
        boolean found = false;
        while (i != null && !found) {
            if(i.value.equals(e)) return true;
            if(e.compareTo(i.value) < 0) found = existsRec(i.child, e);
            i = i.sibling;
        }
        return found;
    }

    @Override
    public ArrayList<Tip> asList() {
        return asListRec(top);
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Unsupported operation on BinomskaKopica");
    }

    private ArrayList<Tip> asListRec(BKNode node) {
        BKNode i = node;
        ArrayList<Tip> list = new ArrayList<>();
        while (i != null){
            list.add(i.value);
            list.addAll(asListRec(i.child));
            i = i.sibling;
        }
        return list;
    }
}
