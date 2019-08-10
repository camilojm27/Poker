package poker;

import java.util.ArrayList;

public class Escaleras {
    public static  ArrayList<Carta> FLUSH_STRAIGHT = new ArrayList<>();
    public static  ArrayList<Carta> ROYAL_FLUSH_STRAIGHT = new ArrayList<>();
    public static  ArrayList<Carta> FOUR_OF_A_KIND = new ArrayList<>();
    public static  ArrayList<Carta> FULL_HOUSE = new ArrayList<>();
    public static  ArrayList<Carta> COLOR_FLUSH = new ArrayList<>();
    public static  ArrayList<Carta> ESCALERA_STRAIGHT = new ArrayList<>();


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


    }
}
