package ca.sheridancollege.ahirnee.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.ahirnee.beans.MovieReview;
import ca.sheridancollege.ahirnee.database.DatabaseAccess;

@Controller
public class ReviewController {
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<MovieReview> reviews = da.getAllMovieReviews();
		model.addAttribute("reviews", reviews);
		model.addAttribute("newReview", new MovieReview());
		
		return "index";
		
	}
	
	@PostMapping("/addReview")
	public String addReview(@ModelAttribute("newReview") MovieReview newReview) {
		newReview.setCurrentDateTime(LocalDateTime.now());
		da.insertMovieReview(newReview);
		
		return "working";
		
	}

	@GetMapping("/editReview/{id}")
	public String editReview(@PathVariable Long id, Model model) {
		MovieReview existingReview = da.getMovieReviewById(id);
		model.addAttribute("editReview", existingReview);
		
		return "editReview";
	}

	@PostMapping("/updateReview")
	public String updateReview(MovieReview updatedReview) {
		da.updateMovieReview(updatedReview);
		
		return "redirect:/";
	}
	
	@GetMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable Long id) {
		da.deleteMovieReview(id);
		
		return "redirect:/";
	}
}
