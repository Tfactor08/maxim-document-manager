package com.documents.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String number;
    protected String user;
    protected LocalDate date;

    public abstract String getName();

    @Override
    public String toString() {
        return String.format("%s от %s номер %s", getName(), date, number);
    }

    public String getFullDesc() {
        return String.format("%s\nДата: %s\nНомер: %s\nПользователь: %s\n", getName(), date, number, user);
    }
}
