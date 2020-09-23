package com.employeesystem.employeesystem.repository.model.IDcard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = IDcard.class)
public class IDcard implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty(message = "Series must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]{2}+$", message = "You are allowed to input only 2 letters!")
    private String series;

    @NotNull(message = "Number must not be empty.")
    @Pattern(regexp="^[0-9]{6}+$", message = "Your Id number must have 6 digits and not includ letters!")
    private String number;

    @NotEmpty(message = "CNP must not be empty.")
    @Pattern(regexp="^[1-2]{1}[0-9]{12}+$", message = "Your CNP must have 13 digits, not includ letters and begin with 1 or 2!")
    private String cnp;

    @NotEmpty(message = "citizenship must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String citizenship;

    @NotEmpty(message = "Issued By de must not be empty.")
    private String issuedBy;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date Of Issue must not be null.")
    @JsonFormat(lenient = OptBoolean.FALSE)
    private Date dateOfIssue;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Expiration Date must not be null.")
    @JsonFormat(lenient = OptBoolean.FALSE)
    private Date expirationDate;


    public IDcard() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Override
    public String toString() {
        return "IDcard{" +
                "id='" + id + '\'' +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", cnp='" + cnp + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
