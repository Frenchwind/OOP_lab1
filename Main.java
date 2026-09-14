/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

/**
 * Демонстрация работы с классом Book.
 * Лабораторная работа №1, задание на паре.
 */
public class Main {

    public static void main(String[] args) {

        Book book1 = new Book(1, "Война и мир", "Толстой Л.Н.", 1869);
        Book book2 = new Book(2, "Мастер и Маргарита", "Булгаков М.А.", 1967);
        Book book3 = new Book(3, "Преступление и наказание", "Достоевский Ф.М.", 1866);

        System.out.println("=== Список книг ===");
        System.out.println("1. " + book1.getDescription());
        System.out.println("2. " + book2.getDescription());
        System.out.println("3. " + book3.getDescription());

        // Демонстрация работы валидации (раскомментируйте для проверки):
        // book1.setTitle("");           // IllegalArgumentException: пустое название
        // book2.setAuthor(null);        // IllegalArgumentException: null автор
        // book3.setYear(3000);          // IllegalArgumentException: год > текущего
    }
    
    
}