package readability;

public class SimpleMeasureReadabilityTest implements ReadabilityTest {
    @Override
    public double testReadability(Text text) {
        double polysyllables = text.getNumberPolysyllables();
        double sentences =text.getNumberSentences();
        return 1.043 * Math.sqrt(polysyllables * 30 / sentences) + 3.1291;
    }
}
