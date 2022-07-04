package MidiData;

import java.util.ArrayList;

public class MidiFile {
   public String fileName;
   public  int bpm;

   public  int tick; // тиков в четверти
  //Meta 51
   public  int tempo;

//Meta58
   public  int chislitel;
   public  int znamenatel;
   public  int midiCLock;
   public  int amount32;
//Meta58


   public  String trackName; //Название дорожки

//не особо полезная информация
public int fileFormat;
   public int MtrkAmount;
   public  int firstMtrkLength;
   public  int secondMtrkLength;
   //public  int trackNameLength;

   public  String title; // Название файла
   public  String text;
   public  String copyrightInfo;



  // public  int FirstBlockAmount;
  // public int SecondBlockAmount;
  // public  int ThirdBlockAmount;

   public ArrayList<MidiEvent> events = new ArrayList<>(); // массив всех событий
   public ArrayList<MidiNote> notes = new ArrayList<>(); // массив всех встреченных нот

   @Override
   public String toString() {
      return fileName + "\n" +
              "Название трека: " + trackName + "\n" +
              "Темп: " + bpm + " BPM" + "\n" +
              "Размер: " + chislitel + "/" + znamenatel + "\n" +
              "Количество тиков: " + tick + "\n" +
              "Формат файла: " + fileFormat + "\n"
              ;

   }
}
