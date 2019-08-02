package poker;



public class Carta {
    private String id;
    private String tipo;
    private String imagen;
    private boolean turned = true;


    Carta(String id, String baraja, boolean turned){
        this.id = id;
        this.tipo = baraja;
        this.turned = turned;

        if(turned == true){

            imagen = "imagenes/BK.png";

        }

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
