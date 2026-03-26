package com.hexabank.client.domain.model;

import java.util.Objects;

/**
 * Full domain entity for Client (F22).
 */
public final class Client {

    private final Long id;
    private final String name;
    private final String gender;
    private final Integer age;
    private final String identification;
    private final String address;
    private final String phone;
    private final Boolean status;

    public Client(ClientData data) {
        if (data == null) {
            throw new IllegalArgumentException("ClientData is required");
        }
        if (data.getName() == null || data.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (data.getIdentification() == null || data.getIdentification().isBlank()) {
            throw new IllegalArgumentException("identification is required");
        }
        this.id = data.getId();
        this.name = data.getName();
        this.gender = data.getGender();
        this.age = data.getAge();
        this.identification = data.getIdentification();
        this.address = data.getAddress();
        this.phone = data.getPhone();
        this.status = data.getStatus() == null ? Boolean.TRUE : data.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getIdentification() {
        return identification;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(identification, client.identification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }
}
