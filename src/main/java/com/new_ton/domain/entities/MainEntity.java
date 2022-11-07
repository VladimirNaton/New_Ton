package com.new_ton.domain.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main", schema = "erpbd", catalog = "")
public class MainEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpr")
    private int idpr;
    @Basic
    @Column(name = "datecr")
    private Timestamp datecr;
    @Basic
    @Column(name = "datepl")
    private Timestamp datepl;
    @Basic
    @Column(name = "datemade")
    private Timestamp datemade;
    @Basic
    @Column(name = "state")
    private Integer state;
    @Basic
    @Column(name = "brend")
    private String brend;
    @Basic
    @Column(name = "nameprod")
    private String nameprod;
    @Basic
    @Column(name = "sp")
    private String sp;
    @Basic
    @Column(name = "percent")
    private Double percent;
    @Basic
    @Column(name = "mass")
    private Double mass;
    @Basic
    @Column(name = "datestart")
    private Timestamp datestart;
    @Basic
    @Column(name = "timemade")
    private Integer timemade;
    @Basic
    @Column(name = "tempprodmin")
    private Double tempprodmin;
    @Basic
    @Column(name = "tempprodmax")
    private Double tempprodmax;
    @Basic
    @Column(name = "operfio")
    private String operfio;
    @Basic
    @Column(name = "deg")
    private Integer deg;
    @Basic
    @Column(name = "labfio")
    private String labfio;
    @Basic
    @Column(name = "numbprot")
    private Integer numbprot;
    @Basic
    @Column(name = "numbpart")
    private Integer numbpart;
    @Basic
    @Column(name = "expdate")
    private Timestamp expdate;
    @Basic
    @Column(name = "dateprot")
    private Timestamp dateprot;
    @Basic
    @Column(name = "filtr")
    private String filtr;
    @Basic
    @Column(name = "comment")
    private String comment;

    public int getIdpr() {
        return idpr;
    }

    public void setIdpr(int idpr) {
        this.idpr = idpr;
    }

    public Timestamp getDatecr() {
        return datecr;
    }

    public void setDatecr(Timestamp datecr) {
        this.datecr = datecr;
    }

    public Timestamp getDatepl() {
        return datepl;
    }

    public void setDatepl(Timestamp datepl) {
        this.datepl = datepl;
    }

    public Timestamp getDatemade() {
        return datemade;
    }

    public void setDatemade(Timestamp datemade) {
        this.datemade = datemade;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getNameprod() {
        return nameprod;
    }

    public void setNameprod(String nameprod) {
        this.nameprod = nameprod;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
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

    public Timestamp getDatestart() {
        return datestart;
    }

    public void setDatestart(Timestamp datestart) {
        this.datestart = datestart;
    }

    public Integer getTimemade() {
        return timemade;
    }

    public void setTimemade(Integer timemade) {
        this.timemade = timemade;
    }

    public Double getTempprodmin() {
        return tempprodmin;
    }

    public void setTempprodmin(Double tempprodmin) {
        this.tempprodmin = tempprodmin;
    }

    public Double getTempprodmax() {
        return tempprodmax;
    }

    public void setTempprodmax(Double tempprodmax) {
        this.tempprodmax = tempprodmax;
    }

    public String getOperfio() {
        return operfio;
    }

    public void setOperfio(String operfio) {
        this.operfio = operfio;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public String getLabfio() {
        return labfio;
    }

    public void setLabfio(String labfio) {
        this.labfio = labfio;
    }

    public Integer getNumbprot() {
        return numbprot;
    }

    public void setNumbprot(Integer numbprot) {
        this.numbprot = numbprot;
    }

    public Integer getNumbpart() {
        return numbpart;
    }

    public void setNumbpart(Integer numbpart) {
        this.numbpart = numbpart;
    }

    public Timestamp getExpdate() {
        return expdate;
    }

    public void setExpdate(Timestamp expdate) {
        this.expdate = expdate;
    }

    public Timestamp getDateprot() {
        return dateprot;
    }

    public void setDateprot(Timestamp dateprot) {
        this.dateprot = dateprot;
    }

    public String getFiltr() {
        return filtr;
    }

    public void setFiltr(String filtr) {
        this.filtr = filtr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainEntity that = (MainEntity) o;

        if (idpr != that.idpr) return false;
        if (datecr != null ? !datecr.equals(that.datecr) : that.datecr != null) return false;
        if (datepl != null ? !datepl.equals(that.datepl) : that.datepl != null) return false;
        if (datemade != null ? !datemade.equals(that.datemade) : that.datemade != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (brend != null ? !brend.equals(that.brend) : that.brend != null) return false;
        if (nameprod != null ? !nameprod.equals(that.nameprod) : that.nameprod != null) return false;
        if (sp != null ? !sp.equals(that.sp) : that.sp != null) return false;
        if (percent != null ? !percent.equals(that.percent) : that.percent != null) return false;
        if (mass != null ? !mass.equals(that.mass) : that.mass != null) return false;
        if (datestart != null ? !datestart.equals(that.datestart) : that.datestart != null) return false;
        if (timemade != null ? !timemade.equals(that.timemade) : that.timemade != null) return false;
        if (tempprodmin != null ? !tempprodmin.equals(that.tempprodmin) : that.tempprodmin != null) return false;
        if (tempprodmax != null ? !tempprodmax.equals(that.tempprodmax) : that.tempprodmax != null) return false;
        if (operfio != null ? !operfio.equals(that.operfio) : that.operfio != null) return false;
        if (deg != null ? !deg.equals(that.deg) : that.deg != null) return false;
        if (labfio != null ? !labfio.equals(that.labfio) : that.labfio != null) return false;
        if (numbprot != null ? !numbprot.equals(that.numbprot) : that.numbprot != null) return false;
        if (numbpart != null ? !numbpart.equals(that.numbpart) : that.numbpart != null) return false;
        if (expdate != null ? !expdate.equals(that.expdate) : that.expdate != null) return false;
        if (dateprot != null ? !dateprot.equals(that.dateprot) : that.dateprot != null) return false;
        if (filtr != null ? !filtr.equals(that.filtr) : that.filtr != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpr;
        result = 31 * result + (datecr != null ? datecr.hashCode() : 0);
        result = 31 * result + (datepl != null ? datepl.hashCode() : 0);
        result = 31 * result + (datemade != null ? datemade.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (brend != null ? brend.hashCode() : 0);
        result = 31 * result + (nameprod != null ? nameprod.hashCode() : 0);
        result = 31 * result + (sp != null ? sp.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (datestart != null ? datestart.hashCode() : 0);
        result = 31 * result + (timemade != null ? timemade.hashCode() : 0);
        result = 31 * result + (tempprodmin != null ? tempprodmin.hashCode() : 0);
        result = 31 * result + (tempprodmax != null ? tempprodmax.hashCode() : 0);
        result = 31 * result + (operfio != null ? operfio.hashCode() : 0);
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        result = 31 * result + (labfio != null ? labfio.hashCode() : 0);
        result = 31 * result + (numbprot != null ? numbprot.hashCode() : 0);
        result = 31 * result + (numbpart != null ? numbpart.hashCode() : 0);
        result = 31 * result + (expdate != null ? expdate.hashCode() : 0);
        result = 31 * result + (dateprot != null ? dateprot.hashCode() : 0);
        result = 31 * result + (filtr != null ? filtr.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
