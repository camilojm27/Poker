package poker;

import java.util.ArrayList;
import java.util.Random;


public class Baraja {
    private Carta carta;
    private ArrayList<Carta> clubs, diamonds, hearts, spades, all;
    private String tipo, valor;
    private Random random;



    Baraja() {
        all = new ArrayList<>();
        clubs = new ArrayList<>();
        diamonds = new ArrayList<>();
        hearts = new ArrayList<>();
        spades = new ArrayList<>();

        for (int carta = 2; carta <= 14; carta++) {

            if (carta >= 11) {
                if (carta == 11) {
                    valor = "A";
                } else if (carta == 12) {
                    valor = "J";
                } else if (carta == 13) {
                    valor = "K";
                } else if (carta == 14) {
                    valor = "Q";
                }
            } else {
                valor = String.valueOf(carta);
            }

            for (int type = 0; type < 4; type++) {
                if (type == 0) {
                    tipo = "C";
                    clubs.add(new Carta(valor, tipo));
                } else if (type == 1) {
                    tipo = "D";
                    diamonds.add(new Carta(valor, tipo));
                } else if (type == 2) {
                    tipo = "H";
                    hearts.add(new Carta(valor, tipo));
                } else if (type == 3) {
                    tipo = "S";
                    spades.add(new Carta(valor, tipo));
                }
                all.add(new Carta(valor, tipo));

            }


        }

    }

    public ArrayList<Carta> repartirBaraja(){
        ArrayList<Carta> todos = all;
        ArrayList<Carta> repartir = new ArrayList<>();
        random = new Random();
        int cualCarta, tamañoFichas;


        for (int i =0; i < 5; i++){
            tamañoFichas = todos.size();
            cualCarta = random.nextInt(tamañoFichas);
            repartir.add(todos.get(cualCarta));
            todos.remove(cualCarta);
        }
        return repartir;
    }

    public void print(ArrayList<Carta> arrayList) {
        int f =1;
        for (Carta value : arrayList) {
            System.out.println(value.getImagen() + f);
            f++;
        }
    }

    //Getters
    public ArrayList<Carta> getDiamonds() {
        return diamonds;
    }

    public ArrayList<Carta> getHearts() {
        return hearts;
    }

    public ArrayList<Carta> getSpades() {
        return spades;
    }

    public ArrayList<Carta> getClubs() {
        return clubs;
    }
    public ArrayList<Carta> getAll() {
        return all;
    }

}
