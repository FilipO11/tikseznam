package fri.tik.seznam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.HashMap;
import java.util.Scanner;

public class SeznamiUV {
    private HashMap<String, Seznam<String>> seznami;
    private Seznam<String> seznam;
    
    public SeznamiUV() {
        seznami = new HashMap<>();
        seznami.put("pv", new PrioritetnaVrsta<>());
        seznami.put("sk", new Sklad<>());
        seznami.put("bst", new Bst<>());
        seznami.put("bk", new BinomskaKopica<>());
    }
    
    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        String arg;

        try {
            switch (token) {
                case "use":
                    if (sc.hasNext()) {
                        seznam = seznami.get(sc.next());
                        if (null == seznam) result = "Error: please specify a correct data structure type (pv, sk, bst, bk)";
                    }
                    else result = "Error: please specify a data structure type (pv, sk, bst, bk)";
                    break;

                case "add":
                    if (sc.hasNext()) {
                        arg = sc.next();
                        while (sc.hasNext()) arg += " "+sc.next();
                        seznam.add(arg.replaceAll("\"", ""));
                    } else result = "Error: please specify a string";
                    break;

                case "remove_first":
                    if (!seznam.isEmpty()) result = seznam.removeFirst();
                    else result = "Error: structure is empty";
                    break;

                case "size":
                    result = String.format("%d", seznam.size());
                    break;

                case "get_first":
                    if(seznam.isEmpty()){
                        result = "Error: structure is empty";
                        break;
                    }

                    result = seznam.getFirst();
                    break;

                case "depth":
                    result = String.format("%d", seznam.depth());
                    break;

                case "is_empty":
                    result = String.format("Structure is%s empty", (seznam.isEmpty()) ? "" : " not");
                    break;

                case "exists":
                    if(!sc.hasNext()){
                        result = "Error: please specify a string";
                        break;
                    }

                    arg = sc.next();
                    while (sc.hasNext()) arg += " "+sc.next();
                    arg = arg.replaceAll("\"", "");

                    result = String.format("Element is%s in the structure", (seznam.exists(arg)) ? "" : " not");
                    break;

                case "remove":
                    if(!sc.hasNext()){
                        result = "Error: please specify a string";
                        break;
                    }

                    if(seznam.isEmpty()){
                        result = "Error: structure is empty";
                        break;
                    }

                    arg = sc.next();
                    while (sc.hasNext()) arg += " "+sc.next();
                    arg = arg.replaceAll("\"", "");

                    if(!seznam.exists(arg)) {
                        result = "Element not found";
                        break;
                    }

                    result = seznam.remove(arg);
                    break;

                case "as_list":
                    result = seznam.asList().toString();
                    break;

                case "reset":
                    while(!seznam.isEmpty()) seznam.removeFirst();
                    break;

                case "print":
                    seznam.print();

                default:
                    result = String.format("Unrecognized command: %s", token);
            }
        } catch (UnsupportedOperationException e) {
            result = e.getMessage();
        }
        return result;
    }

}
