import java.util.*;

public class EfficientMarkov extends BaseMarkov {

  protected Map<String, ArrayList<String>> myMap;

  public EfficientMarkov(int order) {
    super(order);
    myMap = new HashMap<String, ArrayList<String>>();
  }

  public EfficientMarkov() {
    this(3);
  }

  // fill map with values using k-gram as keys from given text
  @Override
  public void setTraining(String text) {
    myMap.clear();
    myText = text;

    for (int i = 0; i < myText.length() - myOrder + 1; i += 1) {
      String tempText = myText.substring(i, i + myOrder);
      ArrayList<String> listy = new ArrayList<String>();

      if (!myMap.containsKey(tempText)) {
        myMap.put(tempText, listy);
      }
      if (i + myOrder < myText.length()) {
        listy.add(Character.toString(myText.charAt(i + myOrder)));
      }
      else {
        listy.add(PSEUDO_EOS);
      }

    }
  }

  // gets the ArrayList associated with the key in the map, throws exception
  // if no ArrayList found
  @Override
  public ArrayList<String> getFollows(String key) {
    if (!myMap.containsKey(key)) {
      throw new NoSuchElementException(key + " not in map");
    }
    else {
      return myMap.get(key);
    }
  }

}
