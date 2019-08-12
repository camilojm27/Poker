package poker;

import java.util.ArrayList;

public class Escaleras {
    public static  ArrayList<Carta> FLUSH_STRAIGHT = new ArrayList<>();
    public static  ArrayList<Carta> ROYAL_FLUSH_STRAIGHT = new ArrayList<>();
    public static  ArrayList<Carta> FOUR_OF_A_KIND = new ArrayList<>();
    public static  ArrayList<Carta> FULL_HOUSE = new ArrayList<>();
    public static  ArrayList<Carta> COLOR_FLUSH = new ArrayList<>();
    public static  ArrayList<Carta> ESCALERA_STRAIGHT = new ArrayList<>();
    public static ArrayList<Carta> THREE_OF_A_KIND = new ArrayList<>();
    public static ArrayList<Carta> TWO_PAIR = new ArrayList<>();
    public static ArrayList<Carta> PAIR = new ArrayList<>();
    public static ArrayList<Carta> HIGH_CARD = new ArrayList<>();



    Escaleras(){
        FLUSH_STRAIGHT.add(new Carta("9", "D"));
        FLUSH_STRAIGHT.add(new Carta("8", "D"));
        FLUSH_STRAIGHT.add(new Carta("7", "D"));
        FLUSH_STRAIGHT.add(new Carta("6", "D"));
        FLUSH_STRAIGHT.add(new Carta("5", "D"));

        ROYAL_FLUSH_STRAIGHT.add(new Carta("10", "C"));
        ROYAL_FLUSH_STRAIGHT.add(new Carta("J", "C"));
        ROYAL_FLUSH_STRAIGHT.add(new Carta("Q", "C"));
        ROYAL_FLUSH_STRAIGHT.add(new Carta("K", "C"));
        ROYAL_FLUSH_STRAIGHT.add(new Carta("A", "C"));

        FOUR_OF_A_KIND.add(new Carta("8","C"));
        FOUR_OF_A_KIND.add(new Carta("8","S"));
        FOUR_OF_A_KIND.add(new Carta("1","H"));
        FOUR_OF_A_KIND.add(new Carta("8","H"));
        FOUR_OF_A_KIND.add(new Carta("8","S"));

        FULL_HOUSE.add(new Carta("9", "H"));
        FULL_HOUSE.add(new Carta("9", "C"));
        FULL_HOUSE.add(new Carta("9", "S"));
        FULL_HOUSE.add(new Carta("8", "H"));
        FULL_HOUSE.add(new Carta("8", "H"));

        COLOR_FLUSH.add(new Carta("5", "S"));
        COLOR_FLUSH.add(new Carta("2", "S"));
        COLOR_FLUSH.add(new Carta("K", "S"));
        COLOR_FLUSH.add(new Carta("3", "S"));
        COLOR_FLUSH.add(new Carta("6", "S"));

        ESCALERA_STRAIGHT.add(new Carta("5","H"));
        ESCALERA_STRAIGHT.add(new Carta("6","D"));
        ESCALERA_STRAIGHT.add(new Carta("7","S"));
        ESCALERA_STRAIGHT.add(new Carta("8","C"));
        ESCALERA_STRAIGHT.add(new Carta("9","H"));

        THREE_OF_A_KIND.add(new Carta("5", "C"));
        THREE_OF_A_KIND.add(new Carta("5", "S"));
        THREE_OF_A_KIND.add(new Carta("5", "H"));
        THREE_OF_A_KIND.add(new Carta("9", "H"));
        THREE_OF_A_KIND.add(new Carta("2", "S"));

        TWO_PAIR.add(new Carta("6", "C"));
        TWO_PAIR.add(new Carta("6", "D"));
        TWO_PAIR.add(new Carta("9", "C"));
        TWO_PAIR.add(new Carta("9", "H"));
        TWO_PAIR.add(new Carta("Q", "C"));

        PAIR.add(new Carta("10", "D"));
        PAIR.add(new Carta("10", "S"));
        PAIR.add(new Carta("8", "C"));
        PAIR.add(new Carta("A", "D"));
        PAIR.add(new Carta("3", "S"));

        HIGH_CARD.add(new Carta("4", "C"));
        HIGH_CARD.add(new Carta("9", "D"));
        HIGH_CARD.add(new Carta("Q", "H"));
        HIGH_CARD.add(new Carta("3", "C"));
        HIGH_CARD.add(new Carta("5", "S"));





    }
}
