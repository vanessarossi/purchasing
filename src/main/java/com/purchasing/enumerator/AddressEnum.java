package com.purchasing.enumerator;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanessa on 22/04/2016.
 */
public enum AddressEnum {

    @SerializedName("Rua Francisquinho Dias, nº 583, Centro, São José do Rio Pardo - SP")
    SEDE("Sede","Rua Francisquinho Dias, nº 583, Centro, São José do Rio Pardo - SP"),
    @SerializedName("Rua Silvia Jardim,  nº 260 Centro, São José do Rio Pardo - SP")
    CDU("Centro de Diagnóstico","Rua Silvia Jardim,  nº 260 Centro, São José do Rio Pardo - SP"),
    @SerializedName("Rua Silvia Jardim, nº 268, Centro, São José do Rio Pardo - SP")
    CDUANEXO("Centro de Diagnóstico - Anexo","Rua Silvia Jardim, nº 268, Centro, São José do Rio Pardo - SP"),
    @SerializedName("Rua Campos Salles, nº 491, Centro, São José do Rio Pardo – SP")
    FARMACIA("Farmácia","Rua Campos Salles, nº 491, Centro, São José do Rio Pardo – SP"),
    @SerializedName("Rua Coronel Alípio Dias, nº 885, Centro, São José do Rio Pardo - SP")
    CRU("Centro de Referência","Rua Coronel Alípio Dias, nº 885, Centro, São José do Rio Pardo - SP"),
    @SerializedName("Rua Alexandre Carlos de Melo, nº 118, Jardim Aeroporto, São José do Rio Pardo - SP")
    HOSPITAL("Hospital","Rua Alexandre Carlos de Melo, nº 118, Jardim Aeroporto, São José do Rio Pardo - SP"),
    @SerializedName("Rua Carlos Botelho, n° 593, Centro, São José do Rio Pardo – SP")
    DSO("Departamento de Saúde Ocupacional","Rua Carlos Botelho, n° 593, Centro, São José do Rio Pardo – SP"),
    @SerializedName("Rua Rui Barbosa, n° 109, Centro, São José do Rio Pardo – SP")
    MEDPREV("Medicina Preventiva","Rua Rui Barbosa, n° 109, Centro, São José do Rio Pardo – SP"),
    @SerializedName("Rua Leôncio D’Ávilla Riberiro, n° 11, Vila Brasil, São José do Rio Pardo – SP")
    CEU("Centro de Especialidades","Rua Leôncio D’Ávilla Riberiro, n° 11, Vila Brasil, São José do Rio Pardo – SP"),
    @SerializedName("Rua Padre de Angelis, nº 39 , Caconde - SP")
    CACONDE("Posto de Caconde","Rua Padre de Angelis, nº 39 , Caconde - SP"),
    @SerializedName("Rua XV de Novembro, nº 582 - Divinolândia - SP")
    DIVINOLANDIA("Posto de Dininolândia","Rua XV de Novembro, nº 582 - Divinolândia - SP"),
    @SerializedName("Rua João Batista de Lima Figueredo, nº450 - São José do Rio Pardo - SP")
    TAPIRATIBA("Posto de Tapiratiba","Rua João Batista de Lima Figueredo, nº450 - São José do Rio Pardo - SP");

    AddressEnum(String place, String address) {
        this.place = place;
        this.address = address;
    }

    private String place;
    private String address;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<AddressEnum> getAll(){
        List<AddressEnum> addressEnumList = new ArrayList<>();
        for (AddressEnum addressEnum : AddressEnum.values()) {
            addressEnumList.add(addressEnum);
        }
        return addressEnumList;
    }

    public static String returnPlace(AddressEnum addressEnum){
        switch (addressEnum){
            case SEDE:
                return SEDE.getPlace();
            case CDU:
                return CDU.getPlace();
            case CDUANEXO:
                return CDUANEXO.getPlace();
            case FARMACIA:
                return FARMACIA.getPlace();
            case CRU:
                return CRU.getPlace();
            case HOSPITAL:
                return HOSPITAL.getPlace();
            case DSO:
                return DSO.getPlace();
            case MEDPREV:
                return MEDPREV.getPlace();
            case CEU:
                return CEU.getPlace();
            case CACONDE:
                return CACONDE.getPlace();
            case DIVINOLANDIA:
                return DIVINOLANDIA.getPlace();
            case TAPIRATIBA:
                return TAPIRATIBA.getPlace();
            default:
                return "Não Encontrado";
        }
    }
}
