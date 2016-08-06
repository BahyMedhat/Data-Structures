package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyDictionary implements IDictionary {

  private MyAVLTree<String> avl;

  public MyDictionary() {

    avl = new MyAVLTree<String>();
  }

  @Override
  public void load(File file) {
    // TODO Auto-generated method stub

    String[] last = null;
    BufferedReader br = null;
    String words;
    try {
      FileReader fr = new FileReader(file);
      br = new BufferedReader(fr);
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
      while (line != null) {
        sb.append(line + " ");
        line = br.readLine();
      }
      words = sb.toString();
      last = words.split(" ");
    } catch (FileNotFoundException e) {
      System.out.println("'" + file.toString() + "' File not found!");
    } catch (IOException e) {
      System.out.println("Unable to read '" + file.toString() + "' File!");
    }
    try {
      br.close();
    } catch (IOException e) {
      System.out.println("'" + file.toString()
          + "' File is unable to be closed!");
    }
    for (String word : last) {
      if (!avl.search(word)) {
        avl.insert(word);
      }
    }
  }

  @Override
  public boolean insert(String word) {
    // TODO Auto-generated method stub

    if (!avl.search(word)) {
      avl.insert(word);
      return true;
    }
    System.out.println("ERROR: Word already in the dictionary!");
    return false;
  }

  @Override
  public boolean exists(String word) {
    // TODO Auto-generated method stub

    return avl.search(word);
  }

  @Override
  public boolean delete(String word) {
    // TODO Auto-generated method stub

    return avl.delete(word);
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub

    return avl.getSize();
  }

  @Override
  public int height() {
    // TODO Auto-generated method stub

    return avl.height();
  }

}
