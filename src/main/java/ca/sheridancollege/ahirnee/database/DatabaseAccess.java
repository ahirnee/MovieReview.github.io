package ca.sheridancollege.ahirnee.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ca.sheridancollege.ahirnee.beans.MovieReview;

@Repository
public class DatabaseAccess {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MovieReview> getAllMovieReviews() {
        String query = "SELECT * FROM ReviewForMovie";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            MovieReview movieReview = new MovieReview();
            movieReview.setId(rs.getLong("id"));
            movieReview.setMovieName(rs.getString("movie_name"));
            movieReview.setReview(rs.getString("movie_review"));
            movieReview.setCurrentDateTime(rs.getTimestamp("movie_review_dateTime").toLocalDateTime());
            return movieReview;
        });
    }

    public MovieReview getMovieReviewById(Long id) {
        String query = "SELECT * FROM ReviewForMovie WHERE id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> mapMovieReview(rs));  
    }

    public void updateMovieReview(MovieReview updatedReview) {
        String query = "UPDATE ReviewForMovie SET movie_name=?, movie_review=? WHERE id=?";
        jdbcTemplate.update(query, updatedReview.getMovieName(), updatedReview.getReview(), updatedReview.getId());
    }

    public void deleteMovieReview(Long id) {
        String query = "DELETE FROM ReviewForMovie WHERE id=?";
        jdbcTemplate.update(query, id);
    }
    
    public void insertMovieReview(MovieReview newReview) {
        String query = "INSERT INTO ReviewForMovie (movie_name, movie_review, movie_review_dateTime) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, newReview.getMovieName(), newReview.getReview(), newReview.getCurrentDateTime());
    }
    
    private MovieReview mapMovieReview(ResultSet rs) throws SQLException {
        MovieReview movieReview = new MovieReview();
        movieReview.setId(rs.getLong("id"));
        movieReview.setMovieName(rs.getString("movie_name"));
        movieReview.setReview(rs.getString("movie_review"));
        movieReview.setCurrentDateTime(rs.getTimestamp("movie_review_dateTime").toLocalDateTime());
        return movieReview;
    }
}
