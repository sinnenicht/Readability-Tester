package readability;

public class ReadabilityTester {
    private ReadabilityTest test;

    public void setTest(ReadabilityTest test) {
        this.test = test;
    }

    public double testReadability(Text text) {
        return this.test.testReadability(text);
    }
}
