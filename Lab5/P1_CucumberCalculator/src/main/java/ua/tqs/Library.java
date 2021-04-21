package ua.tqs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;





public class Library {
	private final List<Book> store = new ArrayList<>();
 
	public void addBook(final Book book) {
		store.add(book);
	}
 
	public List<Book> findBooksByDate(final LocalDateTime from, final LocalDateTime to) {
		//return store.stream().filter(book -> {
		//	return from.before(book.getPublished()) && end.getTime().after(book.getPublished());
		//}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
		Calendar end = Calendar.getInstance();
        end.setTime(convertToDateViaSqlTimestamp(to));
        end.roll(Calendar.YEAR, 1);

        return store.stream().filter(book -> {
            return convertToDateViaSqlTimestamp(from).before(convertToDateViaSqlTimestamp(book.getPublished())) && end.getTime().after(convertToDateViaSqlTimestamp(book.getPublished()));
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

	public List<Book> findBooksByName(final String name) {
 
		return store.stream().filter(book -> {
			return book.getTitle().contains(name);
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}

	public List<Book> findBooksByAuthor(final String author) {
 
		return store.stream().filter(book -> {
			return book.getAuthor().equals(author);
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}
}