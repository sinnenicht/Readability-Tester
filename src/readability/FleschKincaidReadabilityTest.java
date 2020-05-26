package readability;

public class FleschKincaidReadabilityTest implements ReadabilityTest {
    @Override
    public double testReadability(Text text) {
        double syllables = text.getNumberSyllables();
        double words = text.getNumberWords();
        double sentences = text.getNumberSentences();
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }
}
