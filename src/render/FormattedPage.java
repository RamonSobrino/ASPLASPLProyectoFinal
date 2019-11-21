package render;

import java.util.ArrayList;
import java.util.List;

public class FormattedPage {

    private List<FormattedLine> lineas = new ArrayList<FormattedLine>();

    private String title;

    public List<FormattedLine> getLineas() {
        return lineas;
    }

    public void setLineas(List<FormattedLine> lineas) {
        this.lineas = lineas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "FormattedPage{" +
                "title='" + title + '\'' +
                ", lineas=" + lineas +
                '}';
    }
}
