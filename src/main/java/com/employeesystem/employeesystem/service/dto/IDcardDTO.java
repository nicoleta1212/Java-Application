package com.employeesystem.employeesystem.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class IDcardDTO implements Serializable {
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

    @NotEmpty(message = "Citizenship must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String citizenship;

    @NotEmpty(message = "Issued By de must not be empty.")
    private String issuedBy;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date Of Issue must not be null.")
    private Date dateOfIssue;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Expiration Date must not be null.")
    private Date expirationDate;

    public IDcardDTO(String id, String series, String number, String cnp, String citizenship,
                     String issuedBy, Date dateOfIssue, Date expirationDate) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.cnp = cnp;
        this.citizenship = citizenship;
        this.issuedBy = issuedBy;
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
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
        return "IDcardDTO{" +
                "id='" + id + '\'' +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", cnp='" + cnp + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
