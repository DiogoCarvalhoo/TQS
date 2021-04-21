package ua.tqs;
 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 

 

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();


    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime date_iso_local_date_time(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
	}

 
	@Given("(a|another) book with the title {string}, written by {string}, published in {date_iso_local_date_time}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}
 
    @When("the customer searches for books published between {date_iso_local_date_time} and {date_iso_local_date_time}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
		result = library.findBooksByDate(from, to);
	}

    @When("^the customer searches for books with name '(.+)'")
	public void setSearchParameters2(final String name) {
		result = library.findBooksByName(name);
	}

    @When("^the customer searches for books written by '(.+)'")
	public void setSearchParameters3(final String author) {
		result = library.findBooksByAuthor(author);
	}
 
	@Then("(\\d+) books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}