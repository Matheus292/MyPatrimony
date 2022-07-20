package br.com.matheus.mypatrimony.general.enums;

public enum Status {
    ACTIVE(1) , INACTIVE(2);

    private int status;

    Status(int status){
        this.status = status;
    }

    public int status() {
        return status;
    }
}
