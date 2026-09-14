/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

import java.time.Year;

/**
 * Класс, описывающий книгу.
 * Демонстрирует инкапсуляцию: все поля private,
 * доступ — через публичные getter/setter с валидацией.
 */
public class Book {

    private long id;
    private String title;
    private String author;
    private int year;

    /** Пустой конструктор. */
    public Book() {
    }

    /** Конструктор с полным набором параметров. */
    public Book(long id, String title, String author, int year) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    // ---------- getters ----------

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
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
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Имя автора не может быть пустым");
        }
        this.author = author;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 0 || year > currentYear) {
            throw new IllegalArgumentException(
                    "Год должен быть в диапазоне 0.." + currentYear + ", получено: " + year);
        }
        this.year = year;
    }

    // ---------- бизнес-логика ----------

    /**
     * Возвращает строковое описание книги в формате:
     * "Название" — Автор (год)
     */
    public String getDescription() {
        return "\"" + title + "\" — " + author + " (" + year + ")";
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
