package br.com.matheus.mypatrimony.auth.repository;

public enum LoginType {

    ADMIN(1), COMMON(2);

    private int type;

    LoginType(int type){
        this.type = type;
    }

    public int type(){
        return type;
    }
}
