package eg.edu.alexu.csd.filestructure.hash;

import java.util.Random;

public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    HashChaining<Integer, String> chaining = new HashChaining<Integer, String>();
    HashLinearProbing<Integer, String> linear = new HashLinearProbing<Integer, String>();
    HashQuadraticProbing<Integer, String> quad = new HashQuadraticProbing<Integer, String>();
    HashDouble<Integer, String> doubleHash = new HashDouble<Integer, String>();
    Random rand = new Random();
    for(int k = 1000; k < 6000; k += 1000) {
      for(int j = 0; j < 3; j++) {
        for(int i = 0; i < k; i++) {
          linear.put(rand.nextInt(10000), "anything");
          quad.put(rand.nextInt(10000), "anything");
          doubleHash.put(rand.nextInt(10000), "anything");
          chaining.put(rand.nextInt(10000), "anything");
        }
        System.out.println("\n\nCase of " + k + " inserted keys");
        System.out.println("Linear Probing --> Collisions = " + linear.collisions() + ", Capacity = " + linear.capacity());
        System.out.println("Quadratic Probing --> Collisions = " + quad.collisions() + ", Capacity = " + quad.capacity());
        System.out.println("Double Hash --> Collisions = " + doubleHash.collisions() + ", Capacity = " + doubleHash.capacity());
        System.out.println("chaining --> Collisions = " + chaining.collisions() + ", Capacity = " + (chaining.size() + 1200));
        chaining = new HashChaining<Integer, String>();
        linear = new HashLinearProbing<Integer, String>();
        quad = new HashQuadraticProbing<Integer, String>();
        doubleHash = new HashDouble<Integer, String>();
      }
    }
  }
}
