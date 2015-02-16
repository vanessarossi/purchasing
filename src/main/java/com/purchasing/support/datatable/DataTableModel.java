package com.purchasing.support.datatable;

/**
 * @author vanessa
 */
public class DataTableModel {

    private String sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object[] aaData;

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public Object[] getAaData() {
        return aaData;
    }

    public void setAaData(Object[] aaData) {
        this.aaData = aaData;
    }
}
