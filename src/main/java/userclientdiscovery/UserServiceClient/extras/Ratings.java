package userclientdiscovery.UserServiceClient.extras;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ratings {

	
	
	private int ratingid;
	private int rating;
	private String feedback;
	private int bookid;
}