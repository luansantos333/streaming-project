package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.DatabaseException;
import org.portfolio.streaming.services.exceptions.ForbiddenException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    public ReviewService(ReviewRepository reviewRepository, UserService userService, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserReviewMinDTO newReview(UserReviewMinDTO dto) {

        Review review = new Review();
        User user = userRepository.findByEmail(userService.authenticated().getEmail()).orElseThrow(() -> new ResourceNotFoundException("No user found with this id"));
        Movie movie = new Movie();
        copyDTOToEntity(dto, review, movie);
        review.setUserReview(user);
        Review save = reviewRepository.save(review);
        return new UserReviewMinDTO(review, user, save.getMovie().getId());


    }


    @Transactional(readOnly = true)
    public UserReviewMinDTO findByReviewId(Long id) {

        UserReviewProjection userReviewProjection = reviewRepository.searchReviewAndUserByReviewId(id).orElseThrow(() -> new ResourceNotFoundException("No review found with this id"));

        return new UserReviewMinDTO(userReviewProjection);
    }

    @Transactional
    public void deleteReviewById(Long id) {

        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("No review found with this id");
        }
        try {
            reviewRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Couldn't delete movie because it has dependencies with other entities");
        }

    }


    @Transactional
    public UserReviewMinDTO updateReview(UserReviewMinDTO dto, Long id) {

        try {
            Review review = reviewRepository.getReferenceById(id);
            User user = userRepository.findByEmail(userService.authenticated().getEmail()).orElseThrow(() -> new ResourceNotFoundException("No user found with this id"));
            if (user.getId() != review.getUserReview().getId()) {
                throw new ForbiddenException("You can only update your own reviews");
            }

            Movie movie = movieRepository.getReferenceById(review.getMovie().getId());
            copyDTOToEntity(dto, review, movie);
            review = reviewRepository.save(review);
            return new UserReviewMinDTO(review, user, review.getMovie().getId());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No review found with this id");

        }

    }


    private void copyDTOToEntity(UserReviewMinDTO dto, Review reviewEntity, Movie movie) {
        reviewEntity.setReview(dto.getReview());
        reviewEntity.setRating(dto.getRating());
        movie = movieRepository.findById(dto.getMovie()).orElseThrow(() -> new ResourceNotFoundException("No movie found with this id"));
        reviewEntity.setMovie(movie);

    }


}
