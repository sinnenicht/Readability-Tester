package readability;

public class AutomatedReadabilityTest implements ReadabilityTest {
    @Override
    public double testReadability(Text text) {
        double characters = text.getNumberCharacters();
        double words = text.getNumberWords();
        double sentences = text.getNumberSentences();
        return 4.71 * (characters / words) + 0.5 * (words / sentences) - 21.43;
    }
}
