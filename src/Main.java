import classes.Automata;
import classes.GenerateAutomata;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/resources/transitions3.txt";

        Automata automata = GenerateAutomata.generate(filePath);

        automata.testWord("bbbbaaaabbaab");
    }
}