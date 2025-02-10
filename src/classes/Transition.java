package classes;

public class Transition {
    private char letter;
    private char letterQueue;
    private String writeQueue;
    private State destination;

    Transition(char letter, char letterQueue, String writeQueue, State destination)
    {
        this.letter = letter;
        this.letterQueue = letterQueue;
        this.writeQueue = writeQueue;
        this.destination = destination;
    }

    public void resumeIt(){
        System.out.println("l:" + letter + " lq:" + letterQueue + " wq:" + writeQueue + " d:" + destination);
    }

    public char getLetter()
    {
        return this.letter;
    }

    public char getLetterQueue()
    {
        return this.letterQueue;
    }

    public String getWriteQueue()
    {
        return this.writeQueue;
    }

    public State getDestination()
    {
        return this.destination;
    }
}
