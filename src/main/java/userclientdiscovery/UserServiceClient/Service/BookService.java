package userclientdiscovery.UserServiceClient.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import userclientdiscovery.UserServiceClient.extras.Book;

@FeignClient(name="BOOK-SERVICE")
public interface BookService {
	
	
	
	
	@GetMapping("/books")
	List<Book> getbooks();

	@GetMapping("/books/{bookid}")
	 Book getbookbyid(@PathVariable("bookid") int bookid);
	

}
