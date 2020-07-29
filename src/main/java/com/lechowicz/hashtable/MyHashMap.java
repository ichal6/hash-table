package com.lechowicz.hashtable;

import java.util.*;


class KeyValue<K, V> {
    public K key;
    public V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class MyHashMap<K, V> {
    private int bucketSize = 16;

    // This holds all the data. Its a primitive array where every element is a Linked List.
    // They Linked List holds elements of type KeyValue
    private List<KeyValue<K, V>>[] elements = new LinkedList[bucketSize];

    private int getPositionByHash(K key) {
        // This function converts somehow the key to an integer between 0 and bucketSize
        // In Java, hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.

        int position = key.hashCode() % bucketSize;

        return position < 0 ? -position: position;
    }

    public void put(K key, V value) {
        // Find out which position of the elements array to use:
        int position = getPositionByHash(key);

        // If the key already exists, replace the old value with the new.
        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
        List<KeyValue<K, V>> keyValueList = elements[position];

        if(keyValueList == null){
            keyValueList = new LinkedList<>();
        }

        Optional<KeyValue<K, V>> optional = keyValueList.stream()
                .filter(el -> el.key.equals(key))
                .findFirst();

        try{
            optional.get().value = value;
        }catch (NoSuchElementException ex){
            KeyValue<K, V> element = new KeyValue<>(key, value);
            keyValueList.add(element);
        }
        elements[position] = keyValueList;
    }

    public V get(K key) throws Exception {
        List<KeyValue<K, V>> keyValueList = getKeyValueList(key);

        Optional<KeyValue<K, V>> optional = getOptional(key, keyValueList);


        try{
            return optional.get().value;
        }catch (NoSuchElementException ex){
            throw new Exception(ex);
        }
    }

    public void clear() {
        // Make the elements array empty
        elements = new LinkedList[bucketSize];
    }

    public V remove(K key) throws Exception {
        // If key not found, throw an exception
        List<KeyValue<K, V>> keyValueList = getKeyValueList(key);

        Optional<KeyValue<K, V>> optional = getOptional(key, keyValueList);

       try{
            V value = optional.get().value;
            keyValueList.remove(optional.get());
            return value;
        } catch (NoSuchElementException ex){
           throw new Exception(ex);
       }
    }

    private Optional<KeyValue<K, V>> getOptional(K key, List<KeyValue<K, V>> keyValueList) throws Exception{
        if(keyValueList == null){
            throw new Exception();
        }

        return keyValueList.stream()
                .filter(el -> el.key.equals(key))
                .findFirst();
    }

    private List<KeyValue<K, V>> getKeyValueList(K key){
        int position = getPositionByHash(key);
        return elements[position];
    }

}
