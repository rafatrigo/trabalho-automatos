package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.cert.TrustAnchor;
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

    public void testWord(String wordParam)
    {
        wordParam += "?";;
        List<Character> queue = new ArrayList<>();
        char[] word = wordParam.toCharArray();

        int stateIndex = 0;
        int index = 0;
        boolean emptyQueue = true;
        while(index < word.length){
            Transition transition;
            int finalIndex = index;
            State actualState = states.get(stateIndex);

            emptyQueue = queue.isEmpty();

            if(emptyQueue){
                transition = actualState.getTransitions()
                        .stream()
                        .filter(trans -> trans.getLetter() == word[finalIndex])
                        .filter(trans -> trans.getLetterQueue() == '?')
                        .findFirst()
                        .orElse(null);
            }else{
                transition = actualState.getTransitions()
                        .stream()
                        .filter(trans -> trans.getLetter() == word[finalIndex])
                        .filter(trans -> trans.getLetterQueue() == queue.getLast())
                        .findFirst()
                        .orElse(null);
            }

            if (transition == null) {
                transition = actualState.getTransitions()
                        .stream()
                        .filter(trans -> trans.getLetter() == word[finalIndex])
                        .filter(trans -> trans.getLetterQueue() == '*')
                        .findFirst()
                        .orElse(null);

                if(transition == null) {
                    System.out.println("Word not accepted!!!");
                    return;
                }
            }

            if(transition.getLetterQueue() != '*' && transition.getLetterQueue() != '?'){
                queue.removeLast();
            }

            if(transition.getWriteQueue() != '*'){
                queue.add(transition.getWriteQueue());
            }

            stateIndex = states.indexOf(transition.getDestination());
            index++;
        }

        boolean isFinal = states.get(stateIndex).getFinalState();
        if (isFinal && queue.isEmpty())
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
