package com.mycompany.loging.score.model;

/**
 *
 * @author NHuaman
 */
public class Transmision {
    TransmisionHeader header;
    Actas body;

    public TransmisionHeader getHeader() {
        return header;
    }

    public void setHeader(TransmisionHeader header) {
        this.header = header;
    }

    public Actas getBody() {
        return body;
    }

    public void setBody(Actas body) {
        this.body = body;
    }
}
