import java.util.Locale;
import java.util.Objects;

/**
 * Класс, представляющий фильм.
 * <p>
 * Используется для демонстрации работы коллекций ({@code List}, {@code Set}, {@code Map}),
 * сортировки через {@link java.util.Comparator}, Stream API и корректной работы
 * {@code HashSet} за счёт переопределения {@link #equals(Object)} и {@link #hashCode()}.
 *
 * @author Student
 * @version 1.0
 */
public class Film4 implements Comparable<Film4> {

    /** Статический счётчик созданных объектов. */
    private static int counter = 0;

    /** Уникальный идентификатор. */
    private long id;

    /** Название фильма. */
    private String title;

    /** Режиссёр. */
    private String director;

    /** Год выпуска. */
    private int year;

    /** Рейтинг (0.0 – 10.0). */
    private double rating;

    /** Конструктор по умолчанию. */
    public Film4() {
        this(0, "Без названия", "Неизвестен", 0, 0.0);
    }

    /**
     * Конструктор без id.
     *
     * @param title    название
     * @param director режиссёр
     * @param year     год
     * @param rating   рейтинг
     */
    public Film4(String title, String director, int year, double rating) {
        this(0, title, director, year, rating);
    }

    /**
     * Основной конструктор.
     *
     * @param id       идентификатор (0 — автоинкремент)
     * @param title    название
     * @param director режиссёр
     * @param year     год
     * @param rating   рейтинг
     */
    public Film4(long id, String title, String director, int year, double rating) {
        if (id == 0) {
            this.id = ++counter;
        } else {
            this.id = id;
            counter++;
        }
        setTitle(title);
        setDirector(director);
        setYear(year);
        setRating(rating);
    }

    /**
     * Фабричный метод.
     *
     * @param title    название
     * @param director режиссёр
     * @param year     год
     * @param rating   рейтинг
     * @return новый объект
     */
    public static Film4 createFilm(String title, String director, int year, double rating) {
        return new Film4(title, director, year, rating);
    }

    /** @return счётчик созданных объектов */
    public static int getCounter() { return counter; }

    /** @return id */
    public long getId() { return id; }

    /** @return название */
    public String getTitle() { return title; }

    /**
     * @param title название
     */
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.title = title;
    }

    /** @return режиссёр */
    public String getDirector() { return director; }

    /**
     * @param director режиссёр
     */
    public void setDirector(String director) {
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Режиссёр не может быть пустым");
        }
        this.director = director;
    }

    /** @return год */
    public int getYear() { return year; }

    /**
     * @param year год
     */
    public void setYear(int year) {
        if (year < 0 || year > 2100) {
            throw new IllegalArgumentException("Некорректный год");
        }
        this.year = year;
    }

    /** @return рейтинг */
    public double getRating() { return rating; }

    /**
     * @param rating рейтинг
     */
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("Рейтинг должен быть в диапазоне 0.0 – 10.0");
        }
        this.rating = rating;
    }

    /**
     * Описание фильма.
     *
     * @return строка описания
     */
    public String getDescription() {
        return String.format(Locale.US,
                "Фильм #%d: \"%s\" — режиссёр %s (%d), рейтинг %.1f",
                id, title, director, year, rating);
    }

    /**
     * Перегруженное описание.
     *
     * @param shortFormat если {@code true} — краткое
     * @return строка описания
     */
    public String getDescription(boolean shortFormat) {
        if (shortFormat) {
            return String.format("\"%s\" (%d)", title, year);
        }
        return getDescription();
    }

    /**
     * Естественный порядок — по названию (для {@link java.util.TreeSet}).
     *
     * @param other другой фильм
     * @return результат сравнения
     */
    @Override
    public int compareTo(Film4 other) {
        return this.title.compareTo(other.title);
    }

    /**
     * Сравнение по значимым полям, чтобы {@code HashSet} корректно отсеивал дубликаты.
     *
     * @param o другой объект
     * @return {@code true}, если фильмы логически равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film4 film = (Film4) o;
        return year == film.year
                && Double.compare(film.rating, rating) == 0
                && Objects.equals(title, film.title)
                && Objects.equals(director, film.director);
    }

    /**
     * Хеш-код по тем же полям, что и в {@link #equals(Object)}.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, director, year, rating);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}