import java.util.Queue;
import java.util.ArrayDeque;

public class PianoWire
{
	private Queue<Double> frequencies;
	public static final double DECAY = .998;
	
	/**
	 * Takes a single input, a non-negative integer that specifies the wire number (relative to C). 
	 * That is, a parameter of 0 will produce a C string, 1 will produce C#, 2 will produce D..., etc. 
	 * @param wireNum
	 */
	public PianoWire(int wireNum)
	{
		// original
		//int queueLength = (int) (StdAudio.SAMPLE_RATE * Math.pow(2, (22-wireNum)/12.0) / 440);
		// manually calculated middle C
		//int queueLength = 169; // middle C
		// one that works
		int queueLength = (int) (StdAudio.SAMPLE_RATE / (Math.pow(2, (wireNum)/12.0) * 261.3));
		frequencies = new ArrayDeque<Double>();
		for (int i = 0; i < queueLength; i++)
		{
			frequencies.add(0.0);
		}
	}
	
	public void strike()
	{
		for (int i = 0; i < frequencies.size(); i++)
		{
			frequencies.add(Math.random()-.5);
			frequencies.remove();
		}
	}
	
	public double sample()
	{
		double removedVal = frequencies.remove();
		frequencies.add(DECAY * (frequencies.peek()+removedVal)/2);
		return removedVal;
	}
}
