/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import javaapplication1.Film;

 public static void main(String[] args) {

        Film film1 = new Film(1, "Начало", "Кристофер Нолан", 2010, 8.8);
        Film film2 = new Film(2, "Матрица", "Лана Вачовски", 1999, 8.7);
        Film film3 = new Film(3, "Интерстеллар", "Кристофер Нолан", 2014, 8.6);

        System.out.println("=== Список фильмов ===");
        System.out.println("1. " + film1.getDescription());
        System.out.println("2. " + film2.getDescription());
        System.out.println("3. " + film3.getDescription());

        // Демонстрация работы валидации (раскомментируйте для проверки):
        // film1.setYear(1800);      // IllegalArgumentException: год < 1895
        // film2.setRating(11.0);    // IllegalArgumentException: рейтинг > 10.0
        // film3.setTitle("   ");    // IllegalArgumentException: пустое название
    }