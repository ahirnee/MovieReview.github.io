package ca.sheridancollege.ahirnee.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class MovieReview {
	
	private Long id;
	private String movieName;
	private String review;
	private LocalDateTime currentDateTime;

}
