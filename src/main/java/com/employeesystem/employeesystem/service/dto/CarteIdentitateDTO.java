package com.employeesystem.employeesystem.service.dto;

import java.io.Serializable;
import java.util.Date;

public class CarteIdentitateDTO implements Serializable {
    private int id;
    private String serie;
    private Integer number;
    private String cnp;
    private String cetatenie;
    private String emisDe;
    private Date dataEmitere;
    private Date dataExpirare;

    public CarteIdentitateDTO(int id, String serie, Integer number, String cnp, String cetatenie,
                              String emisDe, Date dataEmitere, Date dataExpirare) {
        this.id = id;
        this.serie = serie;
        this.number = number;
        this.cnp = cnp;
        this.cetatenie = cetatenie;
        this.emisDe = emisDe;
        this.dataEmitere = dataEmitere;
        this.dataExpirare = dataExpirare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCNP() {
        return cnp;
    }

    public void setCNP(String cnp) {
        this.cnp = cnp;
    }

    public String getCetatenie() {
        return cetatenie;
    }

    public void setCetatenie(String cetatenie) {
        this.cetatenie = cetatenie;
    }

    public String getEmisDe() {
        return emisDe;
    }

    public void setEmisDe(String emisDe) {
        this.emisDe = emisDe;
    }

    public Date getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(Date dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }
}
