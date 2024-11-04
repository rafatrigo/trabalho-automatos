package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateAutomata {
    //path to the transactions list
    public static Automata generate(String filePath)
    {
        File file = new File(filePath);
        Scanner scanner;

        String holdLine = null;
        ArrayList<String> lines = new ArrayList<>();

        try {

            scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                //Ignore comments on automata
                holdLine = scanner.nextLine();

                if(!holdLine.startsWith("#")){
                    lines.add(holdLine);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return null;
        }

        //list of final states
        String[] finals = getFinals(lines.get(0));

        //create states
        ArrayList<State> states = new ArrayList<>(createStates(lines));

        //set finals
        setFinals(states, finals);

        //create trasitions
        createTransitions(lines, states);

        return new Automata(states);

    }

    private static String[] getFinals(String firstLine)
    {
        String[] finals = firstLine.split(";");
        String[] result = new String[finals.length - 1];
        System.arraycopy(finals, 1, result, 0, finals.length - 1);

         return result;
    }

    private static void setFinals(ArrayList<State> states, String[] finals)
    {
        for(int i = 0; i < finals.length; i++)
        {
            int index = i;
            states.stream()
                    .filter(state -> state.getLabel() == finals[index].charAt(0))
                    .findFirst().orElseThrow().setFinal(true);
        }
    }

    private static boolean verifyStateExistence(ArrayList<State> states, String label)
    {
        return states.stream().anyMatch(state -> state.getLabel() == label.charAt(0));
    }

    private static ArrayList<State> createStates(ArrayList<String> fileLines)
    {
        ArrayList<State> states = new ArrayList<>();

        for (int i = 1; i < fileLines.size(); i++) {

            String[] line = fileLines.get(i).split(";");

            if(!verifyStateExistence(states, line[0]))
            {
                State newState = new State(line[0].charAt(0));
                states.add(newState);
            }

        }

        return states;
    }

    private static void createTransitions(ArrayList<String> fileLines, ArrayList<State> states)
    {

        for (int i = 1; i < fileLines.size(); i++) {
            String[] line = fileLines.get(i).split(";");

            Transition newTransaction = new Transition(line[1].charAt(0),
                    states.stream()
                            .filter(state -> state.getLabel() == line[2].charAt(0)).findFirst().orElseThrow());

            State stateOwner = states.stream()
                    .filter(state -> state.getLabel() == line[0].charAt(0)).findFirst().orElseThrow();

            stateOwner.addTransition(newTransaction);
        }
    }

}
