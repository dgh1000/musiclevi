package musiclevi.core;


import javax.sound.midi.*;

public class Note
{
    int tOn;
    int tOff;
    int pitch;

    public Note(int tOn, int tOff, int pitch)
    {
        this.tOn = tOn;
        this.tOff = tOff;
        this.pitch = pitch;
    }

    public int[] onOffTimestamps(int offset) {
        int[] out = new int[2];
        out[0] = tOn  + offset;
        out[1] = tOff + offset;
        return out;
    }

    public Raw[] toRaws(int offset) {
        int idx = 0;
        Raw[] msgs = new Raw[2];
        
        try 
        {
            int[] ts = this.onOffTimestamps(offset);
            ShortMessage m1 = new ShortMessage();
            m1.setMessage(0x90, 1, this.pitch, 64);
            Raw n1 = new Raw(m1, ts[0]);
            ShortMessage m2 = new ShortMessage();
            m2.setMessage(0x80, 1, this.pitch, 64);
            Raw n2 = new Raw(m2, ts[1]);
                // make note on and note off
            msgs[idx++] = n1;
            msgs[idx++] = n2;
        } catch(InvalidMidiDataException e) {
            System.out.println("Invalid Midi Data");
        } 
        
        
        return msgs;
    }

    
}
