package Fonts;

import java.awt.Font;
import java.io.InputStream;

public class Fuentes {
    private Font font = null;
    public  static final String DECORED = "Ewert-Regular.ttf";
    public static final String DECORED2 = "BungeeShade-Regular.ttf";
    public static final String BIT8_2 = "VT323-Regular.ttf";
    public static final String BIT8 = "PressStart2P-Regular.ttf";

    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
     * tamanio = float
     */
    public  Font fuente( String fontName, int estilo, float tamanio)
    {

        try {
            //Se carga la fuente
            InputStream is =  getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }
}
