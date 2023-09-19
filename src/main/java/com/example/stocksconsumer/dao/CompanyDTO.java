package com.example.stocksconsumer.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "companies")
public class CompanyDTO {

    @Id
    @GeneratedValue(generator = "company_company_id_seq")
    private Long companyId;
    private String symbol;
    private String name;
    private boolean isEnabled;

    public CompanyDTO() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO that = (CompanyDTO) o;
        return isEnabled == that.isEnabled && Objects.equals(companyId, that.companyId) && Objects.equals(symbol, that.symbol) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, symbol, name, isEnabled);
    }
}
