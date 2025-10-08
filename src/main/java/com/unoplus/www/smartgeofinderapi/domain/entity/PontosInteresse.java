package com.unoplus.www.smartgeofinderapi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pontos_interesse")
public class PontosInteresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long x, y;
    private String nome;

    public PontosInteresse() {}

    public PontosInteresse(String nome, Long x, Long y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
