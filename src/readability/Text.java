package readability;

public class Text {
    private double numberSentences;
    private double numberWords;
    private double numberCharacters;
    private double numberSyllables;
    private double numberPolysyllables;

    public Text(double numberSentences, double numberWords, double numberCharacters, double[] numberSyllables) {
        this.numberSentences = numberSentences;
        this.numberWords = numberWords;
        this.numberCharacters = numberCharacters;
        this.numberSyllables = numberSyllables[0];
        this.numberPolysyllables = numberSyllables[1];
    }

    public double getNumberSentences() {
        return numberSentences;
    }

    public double getNumberWords() {
        return numberWords;
    }

    public double getNumberCharacters() {
        return numberCharacters;
    }

    public double getNumberSyllables() {
        return numberSyllables;
    }

    public double getNumberPolysyllables() {
        return numberPolysyllables;
    }
}
