package com.new_ton.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "unload", schema = "erpbd", catalog = "")
public class UnloadEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "numb")
    private Integer numb;
    @Basic
    @Column(name = "barrel")
    private String barrel;
    @Basic
    @Column(name = "mass")
    private Double mass;

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

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

    public String getBarrel() {
        return barrel;
    }

    public void setBarrel(String barrel) {
        this.barrel = barrel;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnloadEntity that = (UnloadEntity) o;

        if (id != that.id) return false;
        if (idpr != null ? !idpr.equals(that.idpr) : that.idpr != null) return false;
        if (numb != null ? !numb.equals(that.numb) : that.numb != null) return false;
        if (barrel != null ? !barrel.equals(that.barrel) : that.barrel != null) return false;
        if (mass != null ? !mass.equals(that.mass) : that.mass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idpr != null ? idpr.hashCode() : 0);
        result = 31 * result + (numb != null ? numb.hashCode() : 0);
        result = 31 * result + (barrel != null ? barrel.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        return result;
    }
}
