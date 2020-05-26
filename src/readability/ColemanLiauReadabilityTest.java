package readability;

public class ColemanLiauReadabilityTest implements ReadabilityTest {
    @Override
    public double testReadability(Text text) {
        double characters = text.getNumberCharacters();
        double words = text.getNumberWords();
        double sentences = text.getNumberSentences();
        double avgCharacters = characters / words * 100;
        double avgSentences = sentences / words * 100;
        return 0.0588 * avgCharacters - 0.296 * avgSentences - 15.8;
    }
}
