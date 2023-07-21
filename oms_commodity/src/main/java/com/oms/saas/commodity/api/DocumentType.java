package com.oms.saas.commodity.api;

public enum DocumentType{
    PO(1,"采购单"),
    NO(2,"入库通知单");

    private int code;
    private String msg;
    private DocumentType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
