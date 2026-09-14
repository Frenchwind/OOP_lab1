/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.time.Year;

/**
 * Класс, описывающий фильм.
 * Демонстрирует инкапсуляцию: все поля private,
 * доступ — через публичные getter/setter с валидацией.
 */
public class Film {

    private long id;
    private String title;
    private String director;
    private int year;
    private double rating;

    /** Пустой конструктор. */
    public Film() {
    }

    /** Конструктор с полным набором параметров. */
    public Film(long id, String title, String director, int year, double rating) {
        setId(id);
        setTitle(title);
        setDirector(director);
        setYear(year);
        setRating(rating);
    }

    // ---------- getters ----------

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    // ---------- setters с валидацией ----------

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id не может быть отрицательным: " + id);
        }
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название фильма не может быть пустым");
        }
        this.title = title;
    }

    public void setDirector(String director) {
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Имя режиссёра не может быть пустым");
        }
        this.director = director;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1895 || year > currentYear) {
            throw new IllegalArgumentException(
                    "Год должен быть в диапазоне 1895.." + currentYear + ", получено: " + year);
        }
        this.year = year;
    }

    public void setRating(double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException(
                    "Рейтинг должен быть в диапазоне 0.0..10.0, получено: " + rating);
        }
        this.rating = rating;
    }

    // ---------- бизнес-логика ----------

    /**
     * Возвращает строковое описание фильма в формате:
     * "Название" (реж. Режиссёр, год) — рейтинг X.Y
     */
    public String getDescription() {
        return "\"" + title + "\" (реж. " + director + ", " + year + ") — рейтинг " + rating;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}