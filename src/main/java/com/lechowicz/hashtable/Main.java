package com.lechowicz.hashtable;

public class Main {
    public static void main(String[] args) throws Exception{
        MyHashMap<String, Integer> myMap = new MyHashMap<>();

        myMap.put("Apple", 10);
        myMap.put("Grape", 20);
        myMap.put("Grape", 30);

//
//        try{
//            myMap.remove("ApplE");
//        }catch (Exception ex){
//            System.out.println(ex.fillInStackTrace());
//        }

        System.out.println(myMap.get("Apple"));
        System.out.println(myMap.remove("Grape"));
        System.out.println(myMap.get("Grape"));

    }
}
