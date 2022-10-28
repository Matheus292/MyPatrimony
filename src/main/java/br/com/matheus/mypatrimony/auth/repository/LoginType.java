package br.com.matheus.mypatrimony.auth.repository;

public enum LoginType {

    ADMIN(1), COMMON(2), UNKNOWN(-1);

    private int type;

    LoginType(int type){
        this.type = type;
    }

    public int type(){
        return type;
    }

    public static LoginType getLoginTypeById(int id){
        switch (id){
            case 1:
                return ADMIN;
            case 2:
                return COMMON;
            default:
                return UNKNOWN;
        }
    }

}
