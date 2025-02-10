package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Automata {
    private List<State> states;
    public boolean to_debug = false;

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
            char word_letter = word[finalIndex];
            char queueLetterNow = queue.isEmpty() ? '?' : queue.getLast();

            emptyQueue = queue.isEmpty();

            transition = actualState.getTransitions()
                    .stream()
                    .filter(trans -> trans.getLetter() == word_letter)
                    .filter(trans -> trans.getLetterQueue() == queueLetterNow)
                    .findFirst()
                    .orElse(null);

            if (transition == null) {
                transition = actualState.getTransitions()
                        .stream()
                        .filter(trans -> trans.getLetter() == word_letter)
                        .filter(trans -> trans.getLetterQueue() == '*')
                        .findFirst()
                        .orElse(null);

                if(transition == null) {
//                    debugIt(String.valueOf(finalIndex));
//                    debugIt(String.valueOf(stateIndex));
//                    debugIt(String.valueOf(emptyQueue));
//                    debugIt(queue.toString());
//                    states.get(stateIndex).debugTransitions();
//                    debugIt("No transition found");
                    System.out.println("Word not accepted no transitions found!!!");
                    return;
                }
            }

            if(transition.getLetterQueue() != '*' && transition.getLetterQueue() != '?'){
                queue.removeLast();
            }

            if(transition.getWriteQueue() != "*"){
                for(char ch: transition.getWriteQueue().toCharArray()){
                    if(ch != '*'){
                        queue.add(ch);
                    }
                }
            }

            stateIndex = states.indexOf(transition.getDestination());
            index++;
        }

        boolean isFinal = states.get(stateIndex).getFinalState();
        if (isFinal && queue.isEmpty())
        {
            debugIt("Final state");
            System.out.println("Word accepted!!!");
            return;
        }

        debugIt("Function ended");
        System.out.println("Word not accepted!!!");
    }

    public void debugIt(String message){
        if(!to_debug){
            return;
        }
        System.out.println(message);
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
