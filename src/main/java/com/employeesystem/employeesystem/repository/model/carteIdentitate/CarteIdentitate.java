package com.employeesystem.employeesystem.repository.model.carteIdentitate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = CarteIdentitate.class)
public class CarteIdentitate implements Serializable {
    @Id
    @SequenceGenerator(initialValue=1, name="carteId_seq", sequenceName="carteId_sequence")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carteId_seq")
    private int id;

    @NotEmpty(message = "Serie must not be empty.")
    private String serie;

    @NotEmpty(message = "Number must not be empty.")
    private Integer number;

    @NotEmpty(message = "CNP must not be empty.")
    private String cnp;

    @NotEmpty(message = "Cetatenie must not be empty.")
    private String cetatenie;

    @NotEmpty(message = "Emis de must not be empty.")
    private String emisDe;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data emitere must not be null.")
    private Date dataEmitere;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data expirare must not be null.")
    private Date dataExpirare;

    public CarteIdentitate() {
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

    @Override
    public String toString() {
        return "CarteIdentitate:" +
                "id=" + id +
                ", serie='" + serie + '\'' +
                ", number=" + number +
                ", cnp='" + cnp + '\'' +
                ", cetatenie='" + cetatenie + '\'' +
                ", emisDe='" + emisDe + '\'' +
                ", dataEmitere=" + dataEmitere +
                ", dataExpirare=" + dataExpirare +
                '.';
    }
}
