import java.util.Queue;
import java.util.ArrayDeque;

public class PianoWire
{
	private Queue<Double> frequencies;
	public static final double DECAY = .996;
	
	public PianoWire(int wireNum)
	{
		frequencies = new ArrayDeque<Double>((int) (SAMPLE_RATE * Math.pow(2, (22 - wireNum)/12) / 440));
		for (int i = 0; i < frequencies.size(); i++)
		{
			frequencies.add(0.0);
		}
	}
	
	public static void main(String[] args)
	{
		PianoWire p = new PianoWire(0);
		p.strike();
		System.out.println(p.frequencies);
		p.sample();
		System.out.println(p.frequencies);
		p.sample();
		System.out.println(p.frequencies);
		p.sample();
		System.out.println(p.frequencies);
		p.sample();
		System.out.println(p.frequencies);
		p.sample();
		System.out.println(p.frequencies);
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
		frequencies.add(DECAY * frequencies.peek()*removedVal/2);
		return removedVal;
	}
}
