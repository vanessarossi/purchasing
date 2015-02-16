package com.purchasing.support.datatable;

import java.util.List;

/**
 * @author vanessa
 */
public class DataTables {

    private List<List<String>> aaData;
    private long iTotalRecords;
    private long iTotalDisplayRecords;
    private int minSearch;

    @Override
    public String toString() {
        return "{'minSearch':" + minSearch + ", 'iTotalRecords':"
                + iTotalRecords + ", 'iTotalDisplayRecords':"
                + iTotalDisplayRecords + ", 'aaData':" + aaData.toString()
                + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
