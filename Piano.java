import java.util.ArrayList;

/**
 * Class that models a piano, with characters mapped to piano keys.
 *   @author Dave Reed
 *   @version 8/28/12
 */
public class Piano {
    public final static int SAMPLING_RATE = 44100;
    public final static String KEYS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private ArrayList<PianoWire> wires;
    private int low;
    private int high;

    /**
     * Constructs a piano with 37 keys from across the keyboard.
     */
    public Piano() {
        this.wires = new ArrayList<PianoWire>();
        low = -13; 
        high = KEYS.length()-13;
        for (int i = -13; i <= KEYS.length()-13; i++) {
            this.wires.add(new PianoWire(i));
        }
    }
    
    public Piano(int low, int high)
    {
    	this.wires = new ArrayList<PianoWire>();
    	this.low = low;
    	this.high = high;
        for (int i = low; i <= high; i++) {
        	this.wires.add(new PianoWire(i));
        }
    }
    
    /**
     * Strikes the piano key (wire) corresponding to the specified character.
     *   @param key the character specifying the piano key (or wire)
     */
    public void strikeKey(char key) {
        int index = Piano.KEYS.indexOf(key);
        if (index != -1) {
            this.wires.get(index).strike();
        }
    }
    
    public void strikeKey(int wireNum)
    {
    	this.wires.get(wireNum-low).strike();
    }

    /**
     * Plays all of the vibrating keys (wires) at the current time step.
     */
    public void play() {
        double combined = 0.0;
        for (int i = 0; i < this.wires.size(); i++) {
            combined += this.wires.get(i).sample();
        }
        
        StdAudio.play(combined);      
    }
}