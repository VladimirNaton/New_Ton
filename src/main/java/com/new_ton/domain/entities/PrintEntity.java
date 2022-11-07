package com.new_ton.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "print", schema = "erpbd", catalog = "")
public class PrintEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private Integer code;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getIdpr() {
        return idpr;
    }

    public void setIdpr(Integer idpr) {
        this.idpr = idpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintEntity that = (PrintEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (idpr != null ? !idpr.equals(that.idpr) : that.idpr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (idpr != null ? idpr.hashCode() : 0);
        return result;
    }
}
