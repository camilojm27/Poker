package poker;



public class Carta {
    private String id;
    private String tipo;
    private String imagen;


    Carta(String id, String baraja ){
        this.id = id;
        this.tipo = baraja;
        imagen = id + tipo + ".png";
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

}
