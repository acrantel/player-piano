/**
 * A simple interactive piano, where pressing a key on the keyboard results
 * in the corresponding piano wire being struck and played.
 *   @author Dave Reed
 *   @version 8/28/12
 */
public class InteractivePiano {
    public static void main(String[] args) {
        Piano p = new Piano();
        // this is an infinite loop--user must quit the application
        while (true) {
            // check if the user has typed a key; if so, process it   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                p.strikeKey(key);
            }
            p.play();
        }
    }    
}