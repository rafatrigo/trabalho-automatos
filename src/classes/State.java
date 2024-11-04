package classes;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Transition> transitions;
    private boolean finalState;
    private char label;

    State()
    {
        this.transitions = new ArrayList<Transition>();
        this.finalState = false;
        this.label = '0';
    }

    State(char label)
    {
        this.transitions = new ArrayList<>();
        this.finalState = false;
        this.label = label;
    }

    State(List<Transition> transitions)
    {
        this.transitions = new ArrayList<>(transitions);
        this.finalState = false;
        this.label = '0';
    }

    State(List<Transition> transitions, boolean finalState, char label)
    {
        this.transitions = new ArrayList<>(transitions);
        this.finalState = finalState;
        this.label = label;
    }

    public char getLabel()
    {
        return label;
    }

    public void addTransition(Transition transition)
    {
        this.transitions.add(transition);
    }

    public void setFinal(boolean finalState)
    {
        this.finalState = finalState;
    }

    public List<Transition> getTransitions()
    {
        return this.transitions;
    }

    public boolean getFinalState()
    {
        return this.finalState;
    }
}
