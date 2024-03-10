package AAC;

import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A basic implementation of AACMappings Class with String as filename,
 * String for current category, and a AssociativeArray include the track for the path for the actions.
 * Associative Arrays store the current category it's on and the new Associative array that include all the old arrays.
 * This class will give relative methods for the checking, modifying, as well as getting info.
 *
 * @author Vivien Yan
 */

public class AACMappings {
  String filename;
  String current;
  AssociativeArray<String, AACCategory> map;

  public AACMappings(String filename) throws NullKeyException {
    this.filename = filename;
    this.current = "";
    this.map = new AssociativeArray<>();
    map.set(current, new AACCategory(current));
  }

  public void add(String imageloc, String text) throws NullKeyException, KeyNotFoundException {
    AACCategory category = this.map.get(current);
    category.addItem(imageloc, text);
    if (this.current.equals("")) {
      map.set(text, new AACCategory(text));
    }
  }

  public String getCurrentCategory() {
    return this.current;
  }

  public String[] getImageLocs() throws KeyNotFoundException {
    AACCategory category = this.map.get(current);
    return category.getImages();
  }

  public String getText(String imageloc) throws KeyNotFoundException {
    AACCategory category = this.map.get(current);
    if (isCategory(imageloc)) {
      this.current = category.getText(imageloc);
    }
    return category.getText(imageloc);
  }

  public boolean isCategory(String imageloc) throws KeyNotFoundException {
    AACCategory category = this.map.get("");
    return category.hasImage(imageloc);
  }

  public void reset() {
    this.current = "";
  }

  public void writeToFile(String filename) throws IOException, KeyNotFoundException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (int i = 0; i < map.size; i++) {
        String categoryName = this.map.pairs[i].key;
        writer.write("Category: " + categoryName + "\n");
        for (String location : this.map.get(categoryName).getImages()) {
          writer.write(location + ", " + this.map.get(categoryName).getText(location) + '\n');
        }
      }
    }
  }
}
