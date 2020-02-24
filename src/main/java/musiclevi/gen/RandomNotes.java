
package musiclevi.gen;

import java.util.*;
import musiclevi.core.*;


public class RandomNotes {

    public static List<Note> genRandomNotes(
        int n, double span, double dur, List<Integer> pitches)
    {
        List<Note> out = new ArrayList<>();
        Random rand = new Random();
        int size = pitches.size();
        double t = 0;
        // int lastPitch = 0;
        for (int i = 0; i < n; i++)
        {
            int nextPitch = pitches.get(rand.nextInt(size));
            out.add(
                new Note(t, t+dur, nextPitch)
            );
            t += span;
        } 
        return out;
    }
}