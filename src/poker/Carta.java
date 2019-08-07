package poker;



public class Carta implements Comparable{
    private String id;
    private String tipo;
    private String imagen;
    private boolean turned = true;
    private int idValue;


    Carta(String id, String baraja){
        this.id = id;
        this.tipo = baraja;
        this.turned = turned;
        this.idValue= getIdValue();


        imagen = "imagenes/" + id + tipo + ".png";
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

    public String getImagen() {
        return imagen;
    }

    //Para poder organizar el ArrayList De  menor a mayor
    @Override
    public int compareTo(Object comparableCarta) {

        int compareID=((Carta)comparableCarta).getIdValue();
        return this.idValue-compareID;
    }


}
