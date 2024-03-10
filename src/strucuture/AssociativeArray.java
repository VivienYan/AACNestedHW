package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K and values of type V.
 * Associative Arrays store key/value pairs and permit you to look up values by key.
 *
 * @author Vivien Yan
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  public int size;

  /**
   * The array of key/value pairs.
   */
  public KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({"unchecked"})
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(), DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+
  @SuppressWarnings("unchecked")
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> cloneArray = new AssociativeArray<>();
    cloneArray.pairs =
        (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(), this.pairs.length * 2);

    cloneArray.size = this.size;

    for (int i = 0; i < this.size; i++) {
      cloneArray.pairs[i] = this.pairs[i];
    }

    return cloneArray; // STUB
  }

  /**
   * A toString method that convert the content to String this {}
   */
  public String toString() {
    StringBuilder content = new StringBuilder();
    content.append("{ ");
    for (int i = 0; i < this.size(); i++) {
      content.append(pairs[i].key.toString()).append(" : ").append(pairs[i].value.toString());
    }
    content.append(" }");
    return content.toString(); // STUB
  } // toString()

  /**
   * Set the value associated with key to value. Future calls to get(key) will return value.
   */
  public void set(K key, V value) throws NullKeyException {
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i].value = value;
        return;
      }
    }
    this.expand();
    pairs[size] = new KVPair<>(key, value);
    size++;
    return;
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException when the key is null or does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return pairs[i].value;
      }
    }
    throw new IllegalArgumentException("The Key is not in the array"); // STUB
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should return false for the null key.
   */
  public boolean hasKey(K key) {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return true;
      }
    }
    return false; // STUB
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls to get(key) will throw an
   * exception. If the key does not appear in the associative array, does nothing.
   */
  public void remove(K key) {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i] = pairs[size - 1];
        pairs[size - 1] = null;
        size -= 1;
        return;
      } // STUB
    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key. If no such entry is found,
   * throws an exception.
   */
  int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException(); // STUB
  } // STUB
} // find(K)
// class AssociativeArray
