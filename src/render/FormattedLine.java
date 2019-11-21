package render;

import java.util.ArrayList;
import java.util.List;

public class FormattedLine {

    private List<FormattedText> textos = new ArrayList<FormattedText>();
    private String textAlign;

    public List<FormattedText> getTextos() {
        return textos;
    }

    public void setTextos(List<FormattedText> textos) {
        this.textos = textos;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    @Override
    public String toString() {
        return "\n\tFormattedLine{" +
                "textAlign='" + textAlign + '\'' +
                ", textos=" + textos +

                '}';
    }
}
