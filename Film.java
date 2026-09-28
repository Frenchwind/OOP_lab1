/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Locale;

/**
 * Класс, представляющий фильм.
 * <p>
 * Содержит статический счётчик созданных объектов, несколько конструкторов
 * с вызовом через {@code this(...)}, фабричный метод и перегруженные методы
 * получения описания.
 *
 * @author Student
 * @version 1.0
 */
public class Film {

    /** Статический счётчик созданных объектов. */
    private static int counter = 0;

    /** Уникальный идентификатор фильма. */
    private long id;

    /** Название фильма. */
    private String title;

    /** Режиссёр фильма. */
    private String director;

    /** Год выпуска. */
    private int year;

    /** Длительность в минутах. */
    private int duration;

    /** Рейтинг фильма (0.0 – 10.0). */
    private double rating;

    /**
     * Конструктор по умолчанию.
     * Создаёт фильм со значениями по умолчанию.
     */
    public Film() {
        this(0, "Без названия", "Неизвестен", 0, 0, 0.0);
    }

    /**
     * Конструктор без id.
     *
     * @param title    название фильма
     * @param director режиссёр
     * @param year     год выпуска
     * @param duration длительность в минутах
     * @param rating   рейтинг
     */
    public Film(String title, String director, int year, int duration, double rating) {
        this(0, title, director, year, duration, rating);
    }

    /**
     * Конструктор только с названием и режиссёром.
     *
     * @param title    название фильма
     * @param director режиссёр
     */
    public Film(String title, String director) {
        this(0, title, director, 0, 0, 0.0);
    }

    /**
     * Основной конструктор.
     * Присваивает автоинкрементный id и инициализирует все поля.
     *
     * @param id       идентификатор (если 0 — будет присвоен автоматически)
     * @param title    название фильма
     * @param director режиссёр
     * @param year     год выпуска
     * @param duration длительность в минутах
     * @param rating   рейтинг
     */
    public Film(long id, String title, String director, int year, int duration, double rating) {
        if (id == 0) {
            this.id = ++counter;
        } else {
            this.id = id;
            counter++;
        }
        setTitle(title);
        setDirector(director);
        setYear(year);
        setDuration(duration);
        setRating(rating);
    }

    /**
     * Статический фабричный метод для создания фильма.
     * Автоматически присваивает автоинкрементный id.
     *
     * @param title    название фильма
     * @param director режиссёр
     * @param year     год выпуска
     * @param duration длительность в минутах
     * @param rating   рейтинг
     * @return новый объект {@code Film}
     */
    public static Film createFilm(String title, String director, int year, int duration, double rating) {
        return new Film(title, director, year, duration, rating);
    }

    /**
     * Возвращает количество созданных фильмов.
     *
     * @return количество созданных объектов
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Возвращает id фильма.
     *
     * @return идентификатор
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает название фильма.
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название фильма.
     *
     * @param title название
     */
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.title = title;
    }

    /**
     * Возвращает режиссёра фильма.
     *
     * @return режиссёр
     */
    public String getDirector() {
        return director;
    }

    /**
     * Устанавливает режиссёра фильма.
     *
     * @param director режиссёр
     */
    public void setDirector(String director) {
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Режиссёр не может быть пустым");
        }
        this.director = director;
    }

    /**
     * Возвращает год выпуска.
     *
     * @return год
     */
    public int getYear() {
        return year;
    }

    /**
     * Устанавливает год выпуска.
     *
     * @param year год
     */
    public void setYear(int year) {
        if (year < 0 || year > 2100) {
            throw new IllegalArgumentException("Некорректный год выпуска");
        }
        this.year = year;
    }

    /**
     * Возвращает длительность фильма.
     *
     * @return длительность в минутах
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Устанавливает длительность фильма.
     *
     * @param duration длительность в минутах
     */
    public void setDuration(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Длительность не может быть отрицательной");
        }
        this.duration = duration;
    }

    /**
     * Возвращает рейтинг фильма.
     *
     * @return рейтинг
     */
    public double getRating() {
        return rating;
    }

    /**
     * Устанавливает рейтинг фильма.
     *
     * @param rating рейтинг (0.0 – 10.0)
     */
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("Рейтинг должен быть в диапазоне 0.0 – 10.0");
        }
        this.rating = rating;
    }

    /**
     * Возвращает полное описание фильма.
     *
     * @return строка с описанием
     */
    public String getDescription() {
        return String.format(Locale.US,
                "Фильм #%d: \"%s\" — режиссёр %s (%d), %d мин., рейтинг %.1f",
                id, title, director, year, duration, rating);
    }

    /**
     * Возвращает описание фильма.
     *
     * @param shortFormat если {@code true} — краткое описание, иначе полное
     * @return строка с описанием
     */
    public String getDescription(boolean shortFormat) {
        if (shortFormat) {
            return String.format("\"%s\" (%d)", title, year);
        }
        return getDescription();
    }
}
