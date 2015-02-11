package com.purchasing.enumerator;

import com.google.gson.annotations.SerializedName;

/**
 * @author vanessa
 */
public enum TypePersonEnum {
    @SerializedName("Pessoa Física")
    NaturalPerson("Pessoa Física"),
    @SerializedName("Pessoa Jurídica")
    JuristicPerson("Pessoa Jurídica");

    private String description;

    TypePersonEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
