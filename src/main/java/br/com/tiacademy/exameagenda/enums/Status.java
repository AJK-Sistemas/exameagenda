package br.com.tiacademy.exameagenda.enums;

public enum Status {

    AFAZER("A Fazer"),
    AGUARDANDO("Aguardando Retirada"),
    RETIRADO("Retirado");

    private String status;

    public String getStatus() {
        return status;
    }

    private Status(String status) {
        this.status = status;
    }

}
