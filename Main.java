/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Демонстрационная программа для класса {@link Film}.
 * Показывает работу конструкторов, фабричного метода,
 * статического счётчика и перегруженных методов.
 */
public class Main {

    /**
     * Точка входа.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Принудительно устанавливаем UTF-8 для корректного вывода русского текста
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("=== Создание фильмов ===");

        // 1. Конструктор только с названием и режиссёром
        Film film1 = new Film("Иван Васильевич меняет профессию", "Гайдай Л.И.");
        System.out.println("Создано через конструктор (title, director):");
        System.out.println("  " + film1.getDescription());

        // 2. Фабричный метод
        Film film2 = Film.createFilm("Мастер и Маргарита", "Бортко В.В.", 2005, 500, 8.7);
        System.out.println("Создано через фабричный метод:");
        System.out.println("  " + film2.getDescription());

        // 3. Конструктор без id
        Film film3 = new Film("Преступление и наказание", "Достоевский Ф.М.", 1866, 150, 9.0);
        System.out.println("Создано через конструктор без id:");
        System.out.println("  " + film3.getDescription());

        // 4. Конструктор по умолчанию
        Film film4 = new Film();
        System.out.println("Создано через конструктор по умолчанию:");
        System.out.println("  " + film4.getDescription());

        System.out.println();
        System.out.println("=== Краткое описание (перегруженный метод) ===");
        System.out.println("film1: " + film1.getDescription(true));
        System.out.println("film2: " + film2.getDescription(true));
        System.out.println("film3: " + film3.getDescription(true));

        System.out.println();
        System.out.println("=== Статистика ===");
        System.out.println("Всего создано фильмов: " + Film.getCounter());
    }
}
