package com.example.ejercicio24.config;

public class objetFirmas {
    private String id;
    private byte[] signature;
    private String nombre;

    public objetFirmas(String id, byte[] signature, String nombre) {
        this.id = id;
        this.signature = signature;
        this.nombre = nombre;
    }

    public objetFirmas() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
