package userclientdiscovery.UserServiceClient.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import userclientdiscovery.UserServiceClient.extras.Ratings;



@FeignClient(name="RATINGS-SERVICE")
public interface RatingsService {
	
	
	
	
	@GetMapping("/ratings")
	List<RatingsService> getratings();
	
	@GetMapping("/ratings/{ratingid}")
	Ratings getratingbyid(@PathVariable("ratingid") int ratingid);

	

}
