import structure.*;

/**
 * A basic implementation of AACCategory with String as the name and
 * AssociativeArray for a certain category. 
 * AaCCategory will store the name and location for the images added and
 * provide with the methods for checking, modifying as well as getting nessesary 
 * info from the arrat.
 *
 * @author Vivien Yan
 */

public class AACCategory {

  String name;
  AssociativeArray<String, String> category;

  public AACCategory(String name) {
    this.name = name;
    this.category = new AssociativeArray<>();
  }

  public void addItem(String imageloc, String text) throws NullKeyException {
    category.set(imageloc, text);
  }

  public String getCategory(String imageLocation) throws KeyNotFoundException {
    return this.name;
  }

  public String[] getImages() {
    String[] location = new String[this.category.size];
    for (int i = 0; i < this.category.size; i++) {
      location[i] = this.category.getKey(i);
    }
    return location;
  }

  public String getText(String imageloc) throws KeyNotFoundException {
    return this.category.get(imageloc);
  }

  public boolean hasImage(String imageloc) {
    return this.category.hasKey(imageloc);
  }
}
