/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.ImageIcon;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class Carta.
 */
public class Carta implements Comparable{
    
    /** The url. */
    private URL url;
    
    /** The id. */
    private String id;
    
    /** The tipo. */
    private String tipo;
    
    /** The imagen. */
    private ImageIcon imagen;
    
    /** The id value. */
    private int idValue;


    /**
     * Instantiates a new carta.
     *
     * @param id the id
     * @param baraja the baraja
     */
    Carta(String id, String baraja){
        this.id = id;
        this.tipo = baraja;
        this.idValue= getIdValue();
        url = this.getClass().getClassLoader().getResource("imagenes/" + id + tipo + ".png");
        //System.out.println(url.toString());


        imagen = new ImageIcon(url);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Gets the id value.
     *
     * @return the id value
     */
    public int getIdValue() {
        if (id == "J"){return 11;}
        else if (id == "Q"){return 12;}
        else if (id == "K"){return 13;}
        else if (id == "A"){return 14;}
        else{

            return Integer.valueOf(id);
        }
    }

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Gets the imagen.
     *
     * @return the imagen
     */
    public ImageIcon getImagen() {
        return imagen;
    }

    /**
     * Compare to.
     *
     * @param comparableCarta the comparable carta
     * @return the int
     */
    //Para poder organizar el ArrayList De  menor a mayor
    @Override
    public int compareTo(Object comparableCarta) {

        int compareID=((Carta)comparableCarta).getIdValue();
        return this.idValue-compareID;
    }


}
