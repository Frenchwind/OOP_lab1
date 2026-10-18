import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Демонстрация работы коллекций для варианта 8 («Фильм»).
 * <p>
 * Показывает: сортировку List, работу Set с переопределёнными equals/hashCode,
 * группировку Map, Stream API и замер производительности ArrayList vs HashSet.
 */
public class Main4 {

    /**
     * Точка входа.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // 1. Создаём список из 10 фильмов
        List<Film4> films = new ArrayList<>(List.of(
                Film4.createFilm("Иван Васильевич меняет профессию", "Гайдай Л.И.", 1973, 8.8),
                Film4.createFilm("Мастер и Маргарита", "Бортко В.В.", 2005, 8.7),
                Film4.createFilm("Преступление и наказание", "Достоевский Ф.М.", 1866, 9.0),
                Film4.createFilm("Сталкер", "Тарковский А.А.", 1979, 8.2),
                Film4.createFilm("Солярис", "Тарковский А.А.", 1972, 8.1),
                Film4.createFilm("Аватар", "Кэмерон Дж.", 2009, 7.9),
                Film4.createFilm("Титаник", "Кэмерон Дж.", 1997, 7.8),
                Film4.createFilm("Начало", "Нолан К.", 2010, 8.8),
                Film4.createFilm("Интерстеллар", "Нолан К.", 2014, 8.6),
                Film4.createFilm("Оппенгеймер", "Нолан К.", 2023, 8.4)
        ));

        System.out.println("=== Исходный список фильмов ===");
        films.forEach(System.out::println);

        // 2. Три вида сортировки
        System.out.println();
        System.out.println("=== Сортировка по названию ===");
        List<Film4> byTitle = new ArrayList<>(films);
        byTitle.sort(Comparator.comparing(Film4::getTitle));
        byTitle.forEach(f -> System.out.println("  " + f.getDescription(true)));

        System.out.println();
        System.out.println("=== Сортировка по году ===");
        List<Film4> byYear = new ArrayList<>(films);
        byYear.sort(Comparator.comparingInt(Film4::getYear));
        byYear.forEach(f -> System.out.println("  " + f.getDescription(true)));

        System.out.println();
        System.out.println("=== Сортировка по рейтингу (по убыванию) ===");
        List<Film4> byRating = new ArrayList<>(films);
        byRating.sort(Comparator.comparingDouble(Film4::getRating).reversed());
        byRating.forEach(f -> System.out.printf(Locale.US, "  %s — %.1f%n",
                f.getDescription(true), f.getRating()));

        // 3. Set — проверяем, что дубликаты не добавляются
        System.out.println();
        System.out.println("=== Set: проверка уникальности ===");
        Set<Film4> uniqueFilms = new HashSet<>(films);
        // Пытаемся добавить дубликат «Мастера и Маргариты»
        Film4 duplicate = Film4.createFilm("Мастер и Маргарита", "Бортко В.В.", 2005, 8.7);
        boolean added = uniqueFilms.add(duplicate);
        System.out.println("Дубликат добавлен? " + added + " (ожидается false)");
        System.out.println("Размер Set: " + uniqueFilms.size() + " (ожидается 10)");

        // 4. Map — группировка по режиссёру
        System.out.println();
        System.out.println("=== Map: группировка по режиссёру ===");
        Map<String, List<Film4>> byDirector = new HashMap<>();
        for (Film4 f : films) {
            byDirector.computeIfAbsent(f.getDirector(), k -> new ArrayList<>()).add(f);
        }
        byDirector.forEach((director, list) -> {
            System.out.println(director + ":");
            list.forEach(f -> System.out.println("   " + f.getDescription(true)));
        });

        // 5. Stream API: фильтрация (после 2000 года)
        System.out.println();
        System.out.println("=== Stream API: фильмы после 2000 года ===");
        List<Film4> modern = films.stream()
                .filter(f -> f.getYear() > 2000)
                .sorted(Comparator.comparing(Film4::getYear))
                .collect(Collectors.toList());
        modern.forEach(f -> System.out.println("  " + f.getDescription()));

        // 6. Stream API: агрегация — средний рейтинг по режиссёрам
        System.out.println();
        System.out.println("=== Stream API: средний рейтинг по режиссёрам ===");
        Map<String, Double> avgRatingByDirector = films.stream()
                .collect(Collectors.groupingBy(
                        Film4::getDirector,
                        Collectors.averagingDouble(Film4::getRating)
                ));
        avgRatingByDirector.forEach((director, avg) ->
                System.out.printf(Locale.US, "  %-20s — %.2f%n", director, avg));

        // 7. Замер производительности: ArrayList vs HashSet
        System.out.println();
        System.out.println("=== Замер производительности поиска (100 000 элементов) ===");
        measureSearchPerformance();

        System.out.println();
        System.out.println("Всего создано объектов Film4: " + Film4.getCounter());
    }

    /**
     * Сравнивает время поиска в ArrayList и HashSet для 100 000 элементов.
     */
    private static void measureSearchPerformance() {
        final int SIZE = 100_000;

        List<Film4> arrayList = new ArrayList<>(SIZE);
        Set<Film4> hashSet = new HashSet<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            Film4 f = Film4.createFilm("Фильм " + i, "Режиссёр " + (i % 100), 1950 + (i % 70), 5.0);
            arrayList.add(f);
            hashSet.add(f);
        }
        // Берём элемент из конца списка — для ArrayList это худший случай
        Film4 target = arrayList.get(SIZE - 1);

        long start = System.nanoTime();
        boolean foundInList = arrayList.contains(target);
        long listTime = System.nanoTime() - start;

        start = System.nanoTime();
        boolean foundInSet = hashSet.contains(target);
        long setTime = System.nanoTime() - start;

        System.out.println("ArrayList.contains: " + listTime / 1_000 + " мкс, найдено: " + foundInList);
        System.out.println("HashSet.contains:   " + setTime / 1_000 + " мкс, найдено: " + foundInSet);
        System.out.println("HashSet быстрее примерно в "
                + (listTime / Math.max(setTime, 1)) + " раз");
    }
}