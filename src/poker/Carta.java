package poker;

import javax.swing.ImageIcon;
import java.net.URL;

public class Carta implements Comparable{
    private URL url;
    private String id;
    private String tipo;
    private ImageIcon imagen;
    private int idValue;


    Carta(String id, String baraja){
        this.id = id;
        this.tipo = baraja;
        this.idValue= getIdValue();
        url = this.getClass().getClassLoader().getResource("imagenes/" + id + tipo + ".png");
        //System.out.println(url.toString());


        imagen = new ImageIcon(url);
    }

    public String getId() {
        return id;
    }
    public int getIdValue() {
        if (id == "J"){return 11;}
        else if (id == "Q"){return 12;}
        else if (id == "K"){return 13;}
        else if (id == "A"){return 14;}
        else{

            return Integer.valueOf(id);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    //Para poder organizar el ArrayList De  menor a mayor
    @Override
    public int compareTo(Object comparableCarta) {

        int compareID=((Carta)comparableCarta).getIdValue();
        return this.idValue-compareID;
    }


}
