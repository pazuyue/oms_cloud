package com.oms.saas.commodity.api;

public enum DocumentState {

    CANCEL(-1,"已作废"),
    CREATE(1,"创建"),
    AUDIT(2,"待审核"),
    WAITWAREHOUSING(3,"待入库"),
    WAREHOUSINGCOMPLETED(4,"已入库");
    private int code;
    private String msg;
    private DocumentState(int code, String msg) {
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
