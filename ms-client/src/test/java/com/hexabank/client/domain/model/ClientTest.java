package com.hexabank.client.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void createClientWithRequiredFields() {
        ClientData d = new ClientData();
        d.setId(1L);
        d.setName("Jose Lema");
        d.setIdentification("ID-1234");
        d.setAge(35);
        d.setGender("M");
        d.setAddress("Otavalo sn y principal");
        d.setPhone("098254785");
        d.setStatus(Boolean.TRUE);

        Client c = new Client(d);

        assertEquals("Jose Lema", c.getName());
        assertEquals("ID-1234", c.getIdentification());
        assertEquals(35, c.getAge().intValue());
        assertTrue(c.getStatus());
    }

    @Test
    void missingNameShouldThrow() {
        ClientData d = new ClientData();
        d.setIdentification("ID-1");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Client(d));
        assertTrue(ex.getMessage().contains("name is required"));
    }

    @Test
    void missingIdentificationShouldThrow() {
        ClientData d = new ClientData();
        d.setName("Someone");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Client(d));
        assertTrue(ex.getMessage().contains("identification is required"));
    }
}
