package userclientdiscovery.UserServiceClient.extras;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Book {
	
	
	private int bookid;
	private String bookname;
	private int userid;
	private List<Ratings> ratings;

}