package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class HashLinearProbing<K, V> implements IHash<K, V>, IHashLinearProbing {

  private int capacity, collisions, size;
  private ArrayList<Pair<K, V>> hashTable, list;
  private boolean[] deleted;

  public HashLinearProbing() {
    
    capacity = 1200;
    size = 0;
    collisions = 0;
    hashTable = new ArrayList<Pair<K, V>>();
    list = new ArrayList<Pair<K, V>>();
    for (int i = 0; i < capacity; i++) {
      hashTable.add(new Pair<K, V>());
    }
    deleted = new boolean[capacity];
  }
  
  private int hashFn(K key, int i) {
    
    return (key.hashCode() + i) % capacity;
  }

  private void rehash() {

    capacity *= 2;
    size = 0;
    ArrayList<Pair<K, V>> reserve = new ArrayList<Pair<K, V>>();
    for (Pair<K, V> pair : list) {
      reserve.add(pair);
    }
    hashTable.clear();
    list.clear();
    deleted = new boolean[capacity];
    for (int k = 0; k < capacity; k++) {
      hashTable.add(new Pair<K, V>());
    }
    for (Pair<K, V> pair : reserve) {
      put(pair.getKey(), pair.getValue());
    }
  }

  @Override
  public void put(K key, V value) {
    // TODO Auto-generated method stub

    if (key == null) {
      return;
    }
    list.add(new Pair<K, V>(key, value));
    int i = 0, j = hashFn(key, i);
    for (; hashTable.get(j).getKey() != null && !deleted[j]; i++) {
      j = hashFn(key, i);
      collisions++;
      if (i >= capacity) {
        rehash();
        return;
      }
    }
    hashTable.set(j, new Pair<K, V>(key, value));
    deleted[j] = false;
    size++;
  }

  @Override
  public String get(K key) {
    // TODO Auto-generated method stub

    int i = 0, j = hashFn(key, i);
    for (; hashTable.get(j).getKey() != null && !deleted[j]; i++, j = hashFn(key, i)) {
      if (hashTable.get(j).getKey().equals(key)) {
        return (String) hashTable.get(j).getValue();
      }
    }
    return null;
  }

  @Override
  public void delete(K key) {
    // TODO Auto-generated method stub

    int i = 0, j = hashFn(key, i);
    for (; hashTable.get(j).getKey() != null && !deleted[j]; i++, j = hashFn(key, i)) {
      if (hashTable.get(j).getKey().equals(key)) {
        hashTable.set(j, new Pair<K, V>());
        deleted[j] = true;
        size--;
        return;
      }
    }
  }

  @Override
  public boolean contains(K key) {
    // TODO Auto-generated method stub

    return get(key) == null ? false : true;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub

    return size == 0 ? true : false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub

    return size;
  }

  @Override
  public int capacity() {
    // TODO Auto-generated method stub

    return capacity;
  }

  @Override
  public int collisions() {
    // TODO Auto-generated method stub

    return collisions;
  }

  @Override
  public Iterable<K> keys() {
    // TODO Auto-generated method stub

    ArrayList<K> keys = new ArrayList<K>();
    for (Pair<K, V> pair : hashTable) {
      if (pair.getKey() != null) {
        keys.add(pair.getKey());
      }
    }
    return keys;
  }

}
