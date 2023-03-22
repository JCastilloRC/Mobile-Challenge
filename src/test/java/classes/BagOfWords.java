package classes;
import java.util.List;
import java.util.Random;

public class BagOfWords{
    private List<String> words;
    public List<String> getWords() {
        return words;
    }
    public String getRandomWord() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(words.size());
        return words.get(randomIndex);
    }
}
