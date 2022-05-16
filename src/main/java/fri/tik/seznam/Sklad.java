package fri.tik.seznam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class Sklad<Tip> implements Seznam<Tip>{
    private Element<Tip> vrh;

    static class Element<Tip> {
        public Tip vrednost;
        public Element<Tip> vezava;
        
        public Element(Tip e) {
            vrednost = e;
        }
    }
    
    public Sklad() {
    }

    @Override
    public void add(Tip e) {
        push(e);
    }

    @Override
    public Tip removeFirst() {
        return pop();
    }

    @Override
    public Tip getFirst() {
        return top();
    }
    
    @Override
    public int size() {
        if(null == vrh) return 0;
        Element<Tip> i = vrh;
        int s = 1;
        while(null != i.vezava) {
            i = i.vezava;
            s += 1;
        }
        return s;
    }

    @Override
    public int depth() {
        return (isEmpty()) ? 0 : 1;
    }
    
    @Override
    public boolean isEmpty() {
        return (null == vrh);
    }

    @Override
    public Tip remove(Tip e) {
        if(isEmpty() || !exists(e)) throw new java.util.NoSuchElementException();
        if(vrh.vrednost.equals(e)) return removeFirst();
        
        Element<Tip> elt = vrh.vezava;
        Element<Tip> prev = vrh;
        
        while (!elt.vrednost.equals(e)) {
            prev = elt;
            elt = elt.vezava;
        }
        
        prev.vezava = elt.vezava;
        
        return elt.vrednost;
    }

    @Override
    public boolean exists(Tip e) {
        return search(e) != -1;
    }

    @Override
    public ArrayList<Tip> asList() {
        ArrayList<Tip> list = new ArrayList<>();
        Element<Tip> elt = vrh;
        while(elt != null) {
            list.add(elt.vrednost);
            elt = elt.vezava;
        }
        return list;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        Element<Tip> elt = vrh;
        while(elt != null) {
            sb.append(elt.vrednost);
            sb.append(" ");
            elt = elt.vezava;
        }
        String print = sb.toString();
        System.out.println(print.trim());
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException();
    }

    private void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    private Tip pop() {
        if(null == vrh) throw new java.util.NoSuchElementException();
        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    private Tip top() {
        if(null == vrh) throw new java.util.NoSuchElementException();
        return vrh.vrednost;
    }
    
    public boolean isTop(Tip e) {
        if(null == vrh) throw new java.util.NoSuchElementException();
        return e.equals(vrh.vrednost);
    }
    
    public int search(Tip e) {
        if(null == vrh) return -1;
        Element<Tip> i = vrh;
        int index = 0;
        while(null != i.vezava) {
            if(i.vrednost.equals(e)) return index;
            i = i.vezava;
            index++;
        }
        if(i.vrednost.equals(e)) return index;
        return -1;
    }
}
