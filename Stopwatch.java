public class Stopwatch {

    private final long startingTime;

    public Stopwatch()
    {
        startingTime = System.currentTimeMillis();
    }

    public double elapsedTime()
    {
        long currentTime = System.currentTimeMillis();
        // converts time from milliseconds to seconds
        return (currentTime - startingTime) / 1000.0;
    }
}
