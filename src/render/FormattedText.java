package render;

public class FormattedText {

    private String texto;
    private String color;
    private Float fontSize;
    private String fontStyle;
    private String fontFamily;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getFontSize() {
        return fontSize;
    }

    public void setFontSize(Float fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    @Override
    public String toString() {
        return "\n\t\tFormattedText{" +
                "texto='" + texto + '\'' +
                ", color='" + color + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", fontStyle='" + fontStyle + '\'' +
                ", fontFamily='" + fontFamily + '\'' +
                '}';
    }
}
