package org.example.checkout.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpf")
public class Cpf {

    @Id
    @Column(name = "cpf_value")
    private final String value;

    protected Cpf() {
        this.value = null;
    }

    public Cpf(String cpf) {
        if (!validate(cpf)) throw new IllegalArgumentException("Invalid cpf");
        this.value = cpf;
    }

    private int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) total += Character.getNumericValue(digit) * factor--;
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private String clean(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean isValidLength(String cpf) {
        return cpf.length() != 11;
    }

    private boolean allDigitsTheSame(String cpf) {
        char firstDigit = cpf.charAt(0);
        for (char digit : cpf.toCharArray()) {
            if (digit != firstDigit) return false;
        }
        return true;
    }

    private String extractCheckDigit(String cpf) {
        return cpf.substring(cpf.length() - 2);
    }

    private boolean validate(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false;
        cpf = clean(cpf);
        if (isValidLength(cpf)) return false;
        if (allDigitsTheSame(cpf)) return false;
        int digit1 = calculateDigit(cpf, 10);
        int digit2 = calculateDigit(cpf, 11);
        String actualDigit = extractCheckDigit(cpf);
        String calculatedDigit = String.valueOf(digit1) + digit2;
        return actualDigit.equals(calculatedDigit);
    }

    public String getValue() {
        return value;
    }
}
