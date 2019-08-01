package poker;

import java.util.ArrayList;

public class Carta {
    private String id;
    private String tipo;
    private String imagen;



    Carta(String baraja){


    }

    private ArrayList<Carta> repartir(String baraja){
        retorno = new ArrayList();
        for (int carta = 2; carta<=14; carta++){

            if (carta >= 11 ){
                if (carta == 11){id = "A";}
                else if (carta == 12){id = "J";}
                else if (carta == 13){id = "K";}
                else if (carta == 14){id = "Q";}
            }
            else{
                id = String.valueOf(carta);
            }
            for (int type = 0; type<4; type++){
                tipo = baraja;
            }

            imagen = id + tipo + ".png";
            System.out.println(imagen + "\n");
        }
        return retorno;
    }
    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public ArrayList<Carta> getRetorno() {
        return retorno;
    }

    private ArrayList<Carta> retorno ;
}
