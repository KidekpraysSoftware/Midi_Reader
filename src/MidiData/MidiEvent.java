package MidiData;

public class MidiEvent {

   public int deltaTime;
    public int noteTurnOnChannel;
   // public boolean noteActive;
    public int noteNumber;
    public int noteVelocity;

   // @Override
//    public String toString() {
//        return "MidiEvent{" +
//                "deltaTime=" + deltaTime +
//                ", noteTurnOnChannel=" + noteTurnOnChannel +
//              //  ", noteActive=" + noteActive +
//                ", noteNumber=" + noteNumber +
//                ", noteVelocity=" + noteVelocity +
//                '}';
//    }


    @Override
    public String toString() {
        if (noteTurnOnChannel ==144) {
            return "Нажата нота " + noteNumber +" с vel " + noteVelocity +  " на тике " + deltaTime;
        } else if (noteTurnOnChannel ==128) {
            return "Снята нота " + noteNumber +" с vel " + noteVelocity +  " на тике " + deltaTime;
        }
        return "";
    }

}
