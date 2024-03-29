/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fri.tik.seznam;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public interface Seznam<Tip> {
    // Dodajanje elementa v podatkovno strukturo
    void add(Tip e);
    // Odstranjevanje (in vračanje) prvega elementa iz pod. struk.
    Tip removeFirst();
    // Vračanje prvega elementa iz pod. struk.
    Tip getFirst();
    // Število elementov v podatkovni strukturi
    int size();
    // Globina podatkovne strukture
    int depth();
    // Ali je podakovna struktura prazna
    boolean isEmpty();
    
    Tip remove(Tip e);
    
    boolean exists(Tip e);

    ArrayList<Tip> asList();

    void print();

    void save(OutputStream outputStream) throws IOException;
    void restore(InputStream inputStream) throws IOException,ClassNotFoundException;
}
