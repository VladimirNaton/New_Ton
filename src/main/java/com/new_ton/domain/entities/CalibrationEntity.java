package com.new_ton.domain.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "calibration", schema = "erpbd", catalog = "")
public class CalibrationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "nw")
    private Integer nw;
    @Basic
    @Column(name = "plmass")
    private Double plmass;
    @Basic
    @Column(name = "factmass")
    private Double factmass;
    @Basic
    @Column(name = "operfio")
    private String operfio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getNw() {
        return nw;
    }

    public void setNw(Integer nw) {
        this.nw = nw;
    }

    public Double getPlmass() {
        return plmass;
    }

    public void setPlmass(Double plmass) {
        this.plmass = plmass;
    }

    public Double getFactmass() {
        return factmass;
    }

    public void setFactmass(Double factmass) {
        this.factmass = factmass;
    }

    public String getOperfio() {
        return operfio;
    }

    public void setOperfio(String operfio) {
        this.operfio = operfio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalibrationEntity that = (CalibrationEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (nw != null ? !nw.equals(that.nw) : that.nw != null) return false;
        if (plmass != null ? !plmass.equals(that.plmass) : that.plmass != null) return false;
        if (factmass != null ? !factmass.equals(that.factmass) : that.factmass != null) return false;
        if (operfio != null ? !operfio.equals(that.operfio) : that.operfio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (nw != null ? nw.hashCode() : 0);
        result = 31 * result + (plmass != null ? plmass.hashCode() : 0);
        result = 31 * result + (factmass != null ? factmass.hashCode() : 0);
        result = 31 * result + (operfio != null ? operfio.hashCode() : 0);
        return result;
    }
}
