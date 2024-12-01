package userclientdiscovery.UserServiceClient.User;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import userclientdiscovery.UserServiceClient.extras.Book;


@Getter
@Setter
@NoArgsConstructor
public class User {
	
	private int id;
	private String name;
	private String email;
    private List<Book> books;
	

}
