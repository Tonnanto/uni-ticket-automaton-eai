package de.leuphana.cosa.eai.messagingsystem.structure.content;

public class EmailContent implements Content {
    private final String text;
    private String attachment;

    public EmailContent(String content) {
        this.text = content;
    }
}
