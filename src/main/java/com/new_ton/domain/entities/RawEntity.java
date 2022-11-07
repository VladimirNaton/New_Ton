package com.new_ton.domain.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "raw", schema = "erpbd", catalog = "")
public class RawEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "n")
    private Integer n;
    @Basic
    @Column(name = "stage")
    private Integer stage;
    @Basic
    @Column(name = "code")
    private Integer code;
    @Basic
    @Column(name = "nameraw")
    private String nameraw;
    @Basic
    @Column(name = "percent")
    private Double percent;
    @Basic
    @Column(name = "mass")
    private Double mass;
    @Basic
    @Column(name = "devper")
    private Double devper;
    @Basic
    @Column(name = "devmass")
    private Double devmass;
    @Basic
    @Column(name = "factmass")
    private Double factmass;
    @Basic
    @Column(name = "factmassdev")
    private Double factmassdev;
    @Basic
    @Column(name = "tempdep")
    private Double tempdep;
    @Basic
    @Column(name = "wetdep")
    private Double wetdep;
    @Basic
    @Column(name = "prodtemp")
    private Double prodtemp;
    @Basic
    @Column(name = "datestart")
    private Timestamp datestart;
    @Basic
    @Column(name = "datestop")
    private Timestamp datestop;
    @Basic
    @Column(name = "timemade")
    private Integer timemade;
    @Basic
    @Column(name = "turnmix")
    private Integer turnmix;
    @Basic
    @Column(name = "devturn")
    private Integer devturn;
    @Basic
    @Column(name = "timemix")
    private Integer timemix;
    @Basic
    @Column(name = "factturn")
    private Integer factturn;
    @Basic
    @Column(name = "facttimemix")
    private Integer facttimemix;
    @Basic
    @Column(name = "eq")
    private Integer eq;
    @Basic
    @Column(name = "pastpart")
    private Integer pastpart;
    @Basic
    @Column(name = "pastdate")
    private Timestamp pastdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdpr() {
        return idpr;
    }

    public void setIdpr(Integer idpr) {
        this.idpr = idpr;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNameraw() {
        return nameraw;
    }

    public void setNameraw(String nameraw) {
        this.nameraw = nameraw;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getDevper() {
        return devper;
    }

    public void setDevper(Double devper) {
        this.devper = devper;
    }

    public Double getDevmass() {
        return devmass;
    }

    public void setDevmass(Double devmass) {
        this.devmass = devmass;
    }

    public Double getFactmass() {
        return factmass;
    }

    public void setFactmass(Double factmass) {
        this.factmass = factmass;
    }

    public Double getFactmassdev() {
        return factmassdev;
    }

    public void setFactmassdev(Double factmassdev) {
        this.factmassdev = factmassdev;
    }

    public Double getTempdep() {
        return tempdep;
    }

    public void setTempdep(Double tempdep) {
        this.tempdep = tempdep;
    }

    public Double getWetdep() {
        return wetdep;
    }

    public void setWetdep(Double wetdep) {
        this.wetdep = wetdep;
    }

    public Double getProdtemp() {
        return prodtemp;
    }

    public void setProdtemp(Double prodtemp) {
        this.prodtemp = prodtemp;
    }

    public Timestamp getDatestart() {
        return datestart;
    }

    public void setDatestart(Timestamp datestart) {
        this.datestart = datestart;
    }

    public Timestamp getDatestop() {
        return datestop;
    }

    public void setDatestop(Timestamp datestop) {
        this.datestop = datestop;
    }

    public Integer getTimemade() {
        return timemade;
    }

    public void setTimemade(Integer timemade) {
        this.timemade = timemade;
    }

    public Integer getTurnmix() {
        return turnmix;
    }

    public void setTurnmix(Integer turnmix) {
        this.turnmix = turnmix;
    }

    public Integer getDevturn() {
        return devturn;
    }

    public void setDevturn(Integer devturn) {
        this.devturn = devturn;
    }

    public Integer getTimemix() {
        return timemix;
    }

    public void setTimemix(Integer timemix) {
        this.timemix = timemix;
    }

    public Integer getFactturn() {
        return factturn;
    }

    public void setFactturn(Integer factturn) {
        this.factturn = factturn;
    }

    public Integer getFacttimemix() {
        return facttimemix;
    }

    public void setFacttimemix(Integer facttimemix) {
        this.facttimemix = facttimemix;
    }

    public Integer getEq() {
        return eq;
    }

    public void setEq(Integer eq) {
        this.eq = eq;
    }

    public Integer getPastpart() {
        return pastpart;
    }

    public void setPastpart(Integer pastpart) {
        this.pastpart = pastpart;
    }

    public Timestamp getPastdate() {
        return pastdate;
    }

    public void setPastdate(Timestamp pastdate) {
        this.pastdate = pastdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawEntity rawEntity = (RawEntity) o;

        if (id != rawEntity.id) return false;
        if (idpr != null ? !idpr.equals(rawEntity.idpr) : rawEntity.idpr != null) return false;
        if (n != null ? !n.equals(rawEntity.n) : rawEntity.n != null) return false;
        if (stage != null ? !stage.equals(rawEntity.stage) : rawEntity.stage != null) return false;
        if (code != null ? !code.equals(rawEntity.code) : rawEntity.code != null) return false;
        if (nameraw != null ? !nameraw.equals(rawEntity.nameraw) : rawEntity.nameraw != null) return false;
        if (percent != null ? !percent.equals(rawEntity.percent) : rawEntity.percent != null) return false;
        if (mass != null ? !mass.equals(rawEntity.mass) : rawEntity.mass != null) return false;
        if (devper != null ? !devper.equals(rawEntity.devper) : rawEntity.devper != null) return false;
        if (devmass != null ? !devmass.equals(rawEntity.devmass) : rawEntity.devmass != null) return false;
        if (factmass != null ? !factmass.equals(rawEntity.factmass) : rawEntity.factmass != null) return false;
        if (factmassdev != null ? !factmassdev.equals(rawEntity.factmassdev) : rawEntity.factmassdev != null)
            return false;
        if (tempdep != null ? !tempdep.equals(rawEntity.tempdep) : rawEntity.tempdep != null) return false;
        if (wetdep != null ? !wetdep.equals(rawEntity.wetdep) : rawEntity.wetdep != null) return false;
        if (prodtemp != null ? !prodtemp.equals(rawEntity.prodtemp) : rawEntity.prodtemp != null) return false;
        if (datestart != null ? !datestart.equals(rawEntity.datestart) : rawEntity.datestart != null) return false;
        if (datestop != null ? !datestop.equals(rawEntity.datestop) : rawEntity.datestop != null) return false;
        if (timemade != null ? !timemade.equals(rawEntity.timemade) : rawEntity.timemade != null) return false;
        if (turnmix != null ? !turnmix.equals(rawEntity.turnmix) : rawEntity.turnmix != null) return false;
        if (devturn != null ? !devturn.equals(rawEntity.devturn) : rawEntity.devturn != null) return false;
        if (timemix != null ? !timemix.equals(rawEntity.timemix) : rawEntity.timemix != null) return false;
        if (factturn != null ? !factturn.equals(rawEntity.factturn) : rawEntity.factturn != null) return false;
        if (facttimemix != null ? !facttimemix.equals(rawEntity.facttimemix) : rawEntity.facttimemix != null)
            return false;
        if (eq != null ? !eq.equals(rawEntity.eq) : rawEntity.eq != null) return false;
        if (pastpart != null ? !pastpart.equals(rawEntity.pastpart) : rawEntity.pastpart != null) return false;
        if (pastdate != null ? !pastdate.equals(rawEntity.pastdate) : rawEntity.pastdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idpr != null ? idpr.hashCode() : 0);
        result = 31 * result + (n != null ? n.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (nameraw != null ? nameraw.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (devper != null ? devper.hashCode() : 0);
        result = 31 * result + (devmass != null ? devmass.hashCode() : 0);
        result = 31 * result + (factmass != null ? factmass.hashCode() : 0);
        result = 31 * result + (factmassdev != null ? factmassdev.hashCode() : 0);
        result = 31 * result + (tempdep != null ? tempdep.hashCode() : 0);
        result = 31 * result + (wetdep != null ? wetdep.hashCode() : 0);
        result = 31 * result + (prodtemp != null ? prodtemp.hashCode() : 0);
        result = 31 * result + (datestart != null ? datestart.hashCode() : 0);
        result = 31 * result + (datestop != null ? datestop.hashCode() : 0);
        result = 31 * result + (timemade != null ? timemade.hashCode() : 0);
        result = 31 * result + (turnmix != null ? turnmix.hashCode() : 0);
        result = 31 * result + (devturn != null ? devturn.hashCode() : 0);
        result = 31 * result + (timemix != null ? timemix.hashCode() : 0);
        result = 31 * result + (factturn != null ? factturn.hashCode() : 0);
        result = 31 * result + (facttimemix != null ? facttimemix.hashCode() : 0);
        result = 31 * result + (eq != null ? eq.hashCode() : 0);
        result = 31 * result + (pastpart != null ? pastpart.hashCode() : 0);
        result = 31 * result + (pastdate != null ? pastdate.hashCode() : 0);
        return result;
    }
}
