package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean hasFile = true;
        String text = null;
        try {
            text = readTextFromFile(args[0]);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            hasFile = false;
        }
        if (hasFile) {
            programRuns(text);
        }
    }

    public static void programRuns(String text) {
        String[] words = text.split("\\s");
        Text textToTest = new Text(findNumberSentences(text), words.length, findNumberCharacters(words), findSyllablesAndPolysyllables(words));
        printStatistics(textToTest);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String testType = scanner.next();
        printScores(testType, textToTest);
    }

    public static String readTextFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static double findNumberSentences(String text) {
        String[] sentences = text.split("[!.?]");
        return sentences.length;
    }

    public static double findNumberCharacters(String[] words) {
        double count = 0;
        for (String word : words) {
            count += word.length();
        }
        return count;
    }

    public static double[] findSyllablesAndPolysyllables(String[] words) {
        double syllableCount = 0;
        double polysyllableCount = 0;
        for (String word : words) {
            double vowelCount = 0;
            for (int index = 0; index < word.length(); index++) {
                String character = String.valueOf(word.charAt(index));
                String nextCharacter;
                if (index < word.length() - 1) {
                    nextCharacter = String.valueOf(word.charAt(index + 1));
                } else {
                    nextCharacter = "";
                }
                if (character.matches("[aeiouy]") && !nextCharacter.matches("[aeiouy]")) {
                    vowelCount += 1;
                }

            }
            if (word.matches(".*e")) {
                vowelCount -= 1;
            }
            if (vowelCount == 0) {
                syllableCount += 1;
            } else {
                syllableCount += vowelCount;
            }
            if (vowelCount > 2) {
                polysyllableCount += 1;
            }
        }
        return new double[] {syllableCount, polysyllableCount};
    }

    public static void printStatistics(Text text) {
        int numSentences = (int) text.getNumberSentences();
        int numWords = (int) text.getNumberWords();
        int numCharacters = (int) text.getNumberCharacters();
        int numSyllables = (int) text.getNumberSyllables();
        int numPolysyllables = (int) text.getNumberPolysyllables();

        System.out.println("Words: " + numWords);
        System.out.println("Sentences: " + numSentences);
        System.out.println("Characters: " + numCharacters);
        System.out.println("Syllables: " + numSyllables);
        System.out.println("Polysyllables: " + numPolysyllables);
    }

    public static double calculateScore(String testType, Text text) {
        ReadabilityTester tester = new ReadabilityTester();
        switch (testType) {
            case "ARI":
                tester.setTest(new AutomatedReadabilityTest());
                return tester.testReadability(text);
            case "FK":
                tester.setTest(new FleschKincaidReadabilityTest());
                return tester.testReadability(text);
            case "SMOG":
                tester.setTest(new SimpleMeasureReadabilityTest());
                return tester.testReadability(text);
            case "CL":
                tester.setTest(new ColemanLiauReadabilityTest());
                return tester.testReadability(text);
            default:
                return 0;
        }
    }

    public static double[] calculateAll(Text text) {
        double[] scores = new double[4];
        ReadabilityTester tester = new ReadabilityTester();

        tester.setTest(new AutomatedReadabilityTest());
        scores[0] = tester.testReadability(text);

        tester.setTest(new FleschKincaidReadabilityTest());
        scores[1] = tester.testReadability(text);

        tester.setTest(new SimpleMeasureReadabilityTest());
        scores[2] = tester.testReadability(text);

        tester.setTest(new ColemanLiauReadabilityTest());
        scores[3] = tester.testReadability(text);

        return scores;
    }

    public static String estimateAge(double score) {
        Map<Integer, String> ages = new HashMap<>(14);
        char age = 6;
        for (int index = 1; index <= 12; index++) {
            ages.put(1, String.valueOf(age));
            age += 1;
        }
        ages.put(13, "24");
        ages.put(14, "24+");
        ages.put(15, "24+");
        int roundedScore = (int) Math.ceil(score);
        return ages.get(roundedScore);
    }

    public static void printScores(String testType, Text text) {
        System.out.println();
        switch (testType) {
            case "ARI":
                double ariScore = calculateScore("ARI", text);
                System.out.println("Automated Readability Index: " + ariScore + " (about " + estimateAge(ariScore) + " year olds).");
                break;
            case "FK":
                double fkScore = calculateScore("FK", text);
                System.out.println("Flesch–Kincaid readability tests: " + fkScore + " (about " + estimateAge(fkScore) + " year olds).");
                break;
            case "SMOG":
                double smogScore = calculateScore("SMOG", text);
                System.out.println("Simple Measure of Gobbledygook: " + smogScore + " (about " + estimateAge(smogScore) + " year olds).");
                break;
            case "CL":
                double clScore = calculateScore("CL", text);
                System.out.println("Coleman–Liau index: " + clScore + " (about " + estimateAge(clScore) + " year olds).");
                break;
            case "all":
                double[] scores = calculateAll(text);
                System.out.println("Automated Readability Index: " + scores[0] + " (about " + estimateAge(scores[0]) + " year olds).");
                System.out.println("Flesch–Kincaid readability tests: " + scores[1] + " (about " + estimateAge(scores[1]) + " year olds).");
                System.out.println("Simple Measure of Gobbledygook: " + scores[2] + " (about " + estimateAge(scores[2]) + " year olds).");
                System.out.println("Coleman–Liau index: " + scores[3] + " (about " + estimateAge(scores[3]) + " year olds).");
                System.out.println();
                double averageAge = (scores[0] + scores[1] + scores[2] + scores[3]) / 4;
                System.out.println("This text should be understood in average by " + averageAge + " year olds.");
                break;
        }
    }
}
