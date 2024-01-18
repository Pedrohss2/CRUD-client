package com.crud.client.dto;

import com.crud.client.entities.Client;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;

    @NotBlank(message = "Campo nome não pode ser vazio")
    private String name;
    private String cpf;
    private double income;
    @PastOrPresent(message = "Data não pode ser futura")
    private LocalDate birthDate;

    @Positive(message = "Numero de fihlos precisa ser positivo")
    private Integer children;

    public ClientDTO() {

    }

    public ClientDTO(Long id, String name, String cpf, double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public  ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
