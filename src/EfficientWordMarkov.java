import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
  private Map<WordGram, ArrayList<String>> myMap;

  public EfficientWordMarkov(int order) {
    super(order);
    myMap = new HashMap<WordGram, ArrayList<String>>();
  }

  public EfficientWordMarkov() {
    this(2);
  }

// puts various Strings in the ArrayList<String> using WordGrams
// that were generated from text as a key
  @Override
  public void setTraining(String text) {
    myMap.clear();
    myWords = text.split("\\s+");

    for (int i = 0; i < myWords.length - myOrder + 1; i += 1) {
      WordGram current = new WordGram(myWords, i, myOrder);

      if (!myMap.containsKey(current)) {
        myMap.put(current, new ArrayList<String>());
      }
      if (i + myOrder < myWords.length) {
        myMap.get(current).add(myWords[i + myOrder]);
      }
      else {
        myMap.get(current).add(PSEUDO_EOS);
      }
    }
  }

// returns the ArrayList that is associated with the given WordGram key
  @Override
  public ArrayList<String> getFollows(WordGram key) {
    if (!myMap.containsKey(key)) {
      throw new NoSuchElementException(key + " not in map");
    }
    else {
      return myMap.get(key);
    }
  }

}
