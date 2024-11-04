package classes;

public class Transition {
    private char letter;
    private State destination;

    Transition(char letter, State destination)
    {
        this.letter = letter;
        this.destination = destination;
    }

    public char getLetter()
    {
        return this.letter;
    }

    public State getDestination()
    {
        return this.destination;
    }
}
