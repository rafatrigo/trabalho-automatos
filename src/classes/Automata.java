package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Automata {
    private List<State> states;

    Automata()
    {
        states = new ArrayList<State>();
    }

    Automata(List<State> states)
    {
        this.states = new ArrayList<>(states);
    }

    public void testWord(String word)
    {
        char[] letters = word.toCharArray();

        int stateIndex = 0;

        for (int i = 0; i < letters.length; i++) {

            int index = i;

            State actualState = states.get(stateIndex);

            Transition transition = actualState.getTransitions()
                    .stream().filter(trans -> trans.getLetter() == letters[index])
                    .findFirst().orElse(null);

            if (transition == null) {
                System.out.println("Word not accepted!!!");
                return;
            }

            stateIndex = states.indexOf(transition.getDestination());

        }

        boolean isFinal = states.get(stateIndex).getFinalState();

        if (isFinal)
        {
            System.out.println("Word accepted!!!");
            return;
        }

        System.out.println("Word not accepted!!!");
    }

    public void testFileWords(String testFilePath){
        File file = new File(testFilePath);
        Scanner scanner;

        try {

            scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                //Ignore comments on automata
                this.testWord(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + testFilePath);
        }
    }

}
