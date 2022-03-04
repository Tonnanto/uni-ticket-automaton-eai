package de.leuphana.cosa.eai.documentsystem.structure;

public class Documentable {
    private String name;
    private String header;
    private String body;
    private String footer;

    public Documentable(String name, String header, String body, String footer) {
        this.name = name;
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Documentable{" +
                "name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }
}