package com.purchasing.enumerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public enum TypeEnum {

    Material("Material"),
    Service("Serviço"),
    MaterialService("Material/Serviço");

    private String description;

    TypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<TypeEnum> findTypeQuotation(){
        List<TypeEnum> typeEnumList = new ArrayList<>();
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.ordinal() != 2) {
                typeEnumList.add(typeEnum);
            }
        }
        return typeEnumList;
    }

}
