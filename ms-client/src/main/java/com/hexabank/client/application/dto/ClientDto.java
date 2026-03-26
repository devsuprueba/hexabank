package com.hexabank.client.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientDto {

    private Long id;

    @NotBlank(message = "name is required")
    @Size(max = 200)
    private String name;

    @NotBlank(message = "identification is required")
    @Size(max = 100)
    private String identification;

    @Size(max = 20)
    private String gender;

    @Min(value = 0, message = "age must be >= 0")
    private Integer age;

    @Size(max = 300)
    private String address;

    @Size(max = 50)
    private String phone;

    private Boolean status;

    public ClientDto() {
        // default constructor for frameworks and deserialization
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
