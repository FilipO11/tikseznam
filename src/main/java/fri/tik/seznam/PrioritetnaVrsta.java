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
 *
 * @author Filip
 */
public class PrioritetnaVrsta<Tip extends Comparable> implements Seznam<Tip> {
    private Object[] heap;
    private int end = 0;
    
    public PrioritetnaVrsta() { this(100); }
    public PrioritetnaVrsta(int maxSize) { heap = new Object[maxSize]; }
    
    private void bubbleUp() {
        int eltIdx = end - 1;
        while (eltIdx >= 0) {
            Tip elt = (Tip) heap[eltIdx];
            int childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                Tip child = (Tip) heap[childIdx];
                if (childIdx + 1 < end && child.compareTo( (Tip) heap[childIdx+1]) < 0) child = (Tip) heap[++childIdx];
                if (elt.compareTo(child) >= 0) return;
                swap(eltIdx, childIdx);
            }
            eltIdx = eltIdx % 2 == 1 ? (eltIdx - 1) / 2 : (eltIdx - 2) / 2;
        }
    }
    @Override
    public void add (Tip e) {
        try {
            heap[end] = e;
        } catch (ArrayIndexOutOfBoundsException exc) {
            Object[] newH = new Object[heap.length * 2];
            int i = 0;
            for (Object tip : heap) {
                newH[i++] = tip;
            }
            heap = newH;
            heap[end] = e;
        }
        end++;
        bubbleUp();
    }
    
    private void bubbleDown(int start) {
        int eltIdx = start;
        int childIdx = eltIdx * 2 + 1;
        while (childIdx < end) {
            Tip elt = (Tip) heap[eltIdx];
            Tip child = (Tip) heap[childIdx];
            if (childIdx + 1 < end && child.compareTo((Tip) heap[childIdx+1]) < 0) child = (Tip) heap[++childIdx];
            if (elt.compareTo(child) >= 0) return;
            swap(eltIdx, childIdx);
            eltIdx = childIdx;
            childIdx = eltIdx * 2 + 1;
        }
    }

    @Override
    public Tip removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Tip elt = (Tip) heap[0];
        swap(0, --end);
        bubbleDown(0);
        return elt;
    }
    
    private void swap(int a, int b) {
        Tip tmp = (Tip) heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
    
    @Override
    public Tip getFirst() { 
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return (Tip) heap[0]; 
    }
    
    @Override
    public int depth() {
        if(end==0) return 0;
        return (int) (Math.log(end) / Math.log(2)) +1;
    }
    
    @Override
    public boolean isEmpty() {
        return end==0;
    }
    
    @Override
    public int size() {
        return end;
    }

    @Override
    public Tip remove(Tip e) {
        if(!exists(e)) throw new java.util.NoSuchElementException();
        
        int eltIdx = 0;
        while(!e.equals((Tip) heap[eltIdx])) eltIdx++;
        
        swap(eltIdx, --end);
        heap[end] = null;
        int childIdx = eltIdx * 2 + 1;
        
        //"BubbleDown"
        while (childIdx < end) {
            Tip elt = (Tip) heap[eltIdx];
            Tip child = (Tip) heap[childIdx];
            if (childIdx + 1 < end && child.compareTo((Tip) heap[childIdx+1]) < 0) child = (Tip) heap[++childIdx];
            if (elt.compareTo(child) >= 0) break;
            swap(eltIdx, childIdx);
            eltIdx = childIdx;
            childIdx = eltIdx * 2 + 1;
        }
        
        //"BubbleUp"
        while (eltIdx >= 0) {
            Tip elt = (Tip) heap[eltIdx];
            childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                Tip child = (Tip) heap[childIdx];
                if (childIdx + 1 < end && child.compareTo( (Tip) heap[childIdx+1]) < 0) child = (Tip) heap[++childIdx];
                if (elt.compareTo(child) >= 0) break;
                swap(eltIdx, childIdx);
            }
            eltIdx = eltIdx % 2 == 1 ? (eltIdx - 1) / 2 : (eltIdx - 2) / 2;
        }
        
        return e;
    }

    @Override
    public boolean exists(Tip e) {
        if(isEmpty()) return false;
        for (Object tip : heap) {
            if (e.equals(tip)) return true;
        }
        return false;
    }

    @Override
    public ArrayList<Tip> asList() {
        ArrayList<Tip> list = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            list.add((Tip) heap[i]);
        }
        return list;
    }

    @Override
    public void print() {

    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException();
    }
} 