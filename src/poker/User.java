package poker;

import java.util.ArrayList;

public class User {
    private String userName;
    private int dinero;
    private ArrayList<Carta> baraja;


    User(String userName){
        this.userName = userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }

    public void setBaraja(ArrayList<Carta> baraja) {
        this.baraja = baraja;
    }
    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
