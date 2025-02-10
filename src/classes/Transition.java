package classes;

public class Transition {
    private char letter;
    private char letterQueue;
    private char writeQueue;
    private State destination;

    Transition(char letter, char letterQueue, char writeQueue, State destination)
    {
        this.letter = letter;
        this.letterQueue = letterQueue;
        this.writeQueue = writeQueue;
        this.destination = destination;
    }

    public char getLetter()
    {
        return this.letter;
    }

    public char getLetterQueue()
    {
        return this.letterQueue;
    }

    public char getWriteQueue()
    {
        return this.writeQueue;
    }

    public State getDestination()
    {
        return this.destination;
    }
}
