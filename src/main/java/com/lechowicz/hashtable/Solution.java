package com.lechowicz.hashtable;

import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        MyHashMap<String, Integer> myMap = new MyHashMap();
        Scanner sc = new Scanner(System.in);

        // helper list to store all the keys
        List<String> keys = new ArrayList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.equals("EXIT")){
                break;
            }
            if (line.equals("CLEAR")) {
                myMap.clear();
                keys.clear();
                continue;
            }
            String[] keyValue = line.split(" ");

            String key = keyValue[0];

            if (key.equals("REMOVE")) {
                String keyToRemove = keyValue[1];
                Integer removedValue = myMap.remove(keyToRemove);
                keys.remove(keyToRemove);
                System.out.println(removedValue + " removed");
                continue;
            }

            Integer value = Integer.valueOf(keyValue[1]);

            myMap.put(key, value);
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }

        for (String key : keys) {
            System.out.println(myMap.get(key));
        }
    }
}
