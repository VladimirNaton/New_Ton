package com.new_ton.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "labprot", schema = "erpbd", catalog = "")
public class LabprotEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "indicator")
    private String indicator;
    @Basic
    @Column(name = "nd")
    private String nd;
    @Basic
    @Column(name = "allvalues")
    private String allvalues;
    @Basic
    @Column(name = "dev")
    private String dev;
    @Basic
    @Column(name = "result")
    private String result;

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

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getAllvalues() {
        return allvalues;
    }

    public void setAllvalues(String allvalues) {
        this.allvalues = allvalues;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabprotEntity that = (LabprotEntity) o;

        if (id != that.id) return false;
        if (idpr != null ? !idpr.equals(that.idpr) : that.idpr != null) return false;
        if (indicator != null ? !indicator.equals(that.indicator) : that.indicator != null) return false;
        if (nd != null ? !nd.equals(that.nd) : that.nd != null) return false;
        if (allvalues != null ? !allvalues.equals(that.allvalues) : that.allvalues != null) return false;
        if (dev != null ? !dev.equals(that.dev) : that.dev != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (idpr != null ? idpr.hashCode() : 0);
        result1 = 31 * result1 + (indicator != null ? indicator.hashCode() : 0);
        result1 = 31 * result1 + (nd != null ? nd.hashCode() : 0);
        result1 = 31 * result1 + (allvalues != null ? allvalues.hashCode() : 0);
        result1 = 31 * result1 + (dev != null ? dev.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
