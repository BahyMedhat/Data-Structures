package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashChaining<K, V> implements IHash<K, V>, IHashChaining {

  private int capacity, collisions, size;
  private ArrayList<LinkedList<Pair<K, V>>> hashTable;

  public HashChaining() {
    capacity = 1200;
    size = 0;
    collisions = 0;
    hashTable = new ArrayList<LinkedList<Pair<K, V>>>();
    for (int i = 0; i < capacity; i++) {
      hashTable.add(new LinkedList<Pair<K, V>>());
    }
  }

  @Override
  public void put(K key, V value) {
    // TODO Auto-generated method stub

    collisions += hashTable.get(key.hashCode() % capacity).size();
    int hash = key.hashCode() % capacity;
    LinkedList<Pair<K, V>> list = hashTable.get(hash);
    list.addFirst(new Pair<K, V>(key, value));
    hashTable.set(hash, list);
    size++;
  }

  @Override
  public String get(K key) {
    // TODO Auto-generated method stub

    int hash = key.hashCode() % capacity;
    LinkedList<Pair<K, V>> list = hashTable.get(hash);
    for (Pair<K, V> pair : list) {
      if (pair.getKey().equals(key)) {
        return (String) pair.getValue();
      }
    }
    return null;
  }

  @Override
  public void delete(K key) {
    // TODO Auto-generated method stub

    int hash = key.hashCode() % capacity;
    LinkedList<Pair<K, V>> list = hashTable.get(hash);
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getKey().equals(key)) {
        list.remove(i);
        size--;
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
    for (LinkedList<Pair<K, V>> list : hashTable) {
      for (Pair<K, V> pair : list) {
        keys.add(pair.getKey());
      }
    }
    return keys;
  }

}
