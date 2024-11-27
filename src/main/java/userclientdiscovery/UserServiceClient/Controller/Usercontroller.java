package userclientdiscovery.UserServiceClient.Controller;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import userclientdiscovery.UserServiceClient.Service.BookService;
import userclientdiscovery.UserServiceClient.User.User;
import userclientdiscovery.UserServiceClient.extras.Book;
import userclientdiscovery.UserServiceClient.extras.Ratings;

@RestController
@RequestMapping("/users")
@Slf4j
public class Usercontroller {
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private BookService ratingsservice;
	
	User user1;
	User user2;
	public List<User> users;
	
  int retryCount=0;
	
	@GetMapping
	public List<User> adduser()
	{ 	//user1 = new User();
	user1.setId(1);
	user1.setName("Javed");
	user1.setEmail("test@test.com");
	
	//user2 = new User();
	user2.setId(2);
	user2.setName("Akram");
	user2.setEmail("test1@test.com");
	users=new ArrayList<>();
	
	users.add(user1);
	users.add(user2);
		
		return users;
	}
	
	
	//@CircuitBreaker(name="bookbreaker",fallbackMethod="bookfallback")
	//@Retry(name="retryuser", fallbackMethod="bookfallback")
	@RateLimiter(name="userratelimiter")
	@GetMapping("/{userid}")
	public User getuserbyid(@PathVariable("userid") int userid)
	{ //User user1 = new User();
	
	user1.setId(1);
	user1.setName("Javed");
	user1.setEmail("test@test.com");
	
	//user2 = new User();
	user2.setId(2);
	user2.setName("Akram");
	user2.setEmail("test1@test.com");
	users=new ArrayList<>();
	
	users.add(user1);
	users.add(user2);
		 User user=  users.stream().filter(e -> e.getId()==userid).findAny().get();
		 List<Book> userbooks = new ArrayList<>();
		 userbooks.add(bookservice.getbookbyid(userid));
		 user.setBooks(userbooks);
		 
		 return user;
		   
		
		
	}
	
	public User bookfallback(int userId, Exception ex)
	{
		retryCount++;
		log.info("This a fallback method for down service {}" + ex.getMessage());
		Ratings rating1 = Ratings.builder().ratingid(1).rating(1).feedback("Yes").bookid(1).build();
	
		List<Ratings> fullratinglist = new ArrayList<>();
		fullratinglist.add(rating1);
		Book book = Book.builder().bookid(1).bookname("test Book").userid(12).ratings(fullratinglist).build();
		List<Book> booklist =new ArrayList<>();
			booklist.add(book);
		User user = User.builder().id(12).name("Mock User").email("mock@test.com").books(booklist).build();
		return user;
		
		
	}
	
	@PostMapping("/{userid}/books/{bookid}/ratings")
	public User addratingtouserbook(@PathVariable("userid") int userid,@PathVariable("bookid") int bookid, @RequestBody Ratings ratings)
	{
		
		//user1 = new User();
		user1.setId(1);
		user1.setName("Javed");
		user1.setEmail("test@test.com");
		
		
		//user2 = new User();
		user2.setId(2);
		user2.setName("Akram");
		user2.setEmail("test1@test.com");
		users=new ArrayList<>();
		
		users.add(user1);
		users.add(user2);
		
		User user3=users.stream().filter(e -> e.getId()==userid).findAny().get();
		
		
		//Book book3=user3.getBooks().stream().filter(e -> e.getBookid()==bookid).findAny().get();
		
		List<Book> newbooks = new ArrayList<>();
		Book checkbook = bookservice.getbookbyid(bookid);
		List<Ratings> ratingsfull = new ArrayList<>();
		ratingsfull.add(ratings);
		checkbook.setRatings(ratingsfull);
		newbooks.add(checkbook);
		user3.setBooks(newbooks);
		
		
		return user3;
		
	}
	


}
