package com.oms.saas.commodity.api;

public enum DocumentState {

    CANCEL(-1,"已作废"),
    CREATE(1,"创建"),
    AUDIT(2,"待审核"),
    WAITWAREHOUSING(3,"待入库"),
    WAREHOUSINGCOMPLETED(4,"已入库"),
    WAREHOUSING(1,"入库"),
    OUTBOUND(2,"出库"),
    E_COMMERCE_WAREHOUSE(1,"电商仓库"),
    REAL_WAREHOUSE(1,"真实库存结算"),
    VIRTUALLY_WAREHOUSE(2,"虚拟库存结算"),
    ZP(1,"ZP"),
    CP(2,"CP");


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
