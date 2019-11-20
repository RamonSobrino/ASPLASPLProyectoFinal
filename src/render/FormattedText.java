package render;

public class FormattedText {

    private String texto;
    private String color;
    private String fontSize;
    private String fontStyle;

    public FormattedText(String texto, String color, String fontSize, String fontStyle) {
        this.texto = texto;
        this.color = color;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
    }

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

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
}
