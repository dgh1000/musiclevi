package musiclevi.core;


import javax.sound.midi.*;

public class Note
{
    double tOn;
    double tOff;
    int pitch;

    public Note(double tOn, double tOff, int pitch)
    {
        this.tOn = tOn;
        this.tOff = tOff;
        this.pitch = pitch;
    }

    public long[] onOffTimestamps(long offset) {
        long[] out = new long[2];
        out[0] = (long) (tOn * 1000000) + offset;
        out[1] = (long) (tOff * 1000000) + offset;
        return out;
    }

    public MyMidiNote[] toMyMidiNotes(long offset) {
        int idx = 0;
        MyMidiNote[] msgs = new MyMidiNote[2];
        
        try 
        {
            long[] ts = this.onOffTimestamps(offset);
            ShortMessage m1 = new ShortMessage();
            m1.setMessage(0x90, 1, this.pitch, 64);
            MyMidiNote n1 = new MyMidiNote(m1, ts[0]);
            ShortMessage m2 = new ShortMessage();
            m2.setMessage(0x80, 1, this.pitch, 64);
            MyMidiNote n2 = new MyMidiNote(m2, ts[1]);
                // make note on and note off
            msgs[idx++] = n1;
            msgs[idx++] = n2;
        } catch(InvalidMidiDataException e) {
            System.out.println("Invalid Midi Data");
        } 
        
        
        return msgs;
    }

    
}
