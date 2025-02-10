import classes.Automata;
import classes.GenerateAutomata;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/resources/transitions1.txt";

        Automata automata = GenerateAutomata.generate(filePath);

        automata.testWord("bbaa");
        automata.testFileWords("src/resources/testfiles/test1.txt");
    }
}