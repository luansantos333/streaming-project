package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.dtos.UserReviewDTO;
import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.DatabaseException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;


    public ReviewService(ReviewRepository reviewRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    @Transactional
    public UserReviewDTO newReview(UserReviewDTO reviewDTO) {

        Review review = new Review();
        User user = new User();
        copyDTOToEntity(reviewDTO, review, user);
        Review save = reviewRepository.save(review);
        return new UserReviewDTO(review, user);

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
    public UserReviewDTO updateReview (UserReviewDTO dto, Long id) {

        try {
            Review review = reviewRepository.getReferenceById(id);
            User user = new User();
            copyDTOToEntity(dto, review, user);
            review = reviewRepository.save(review);
            return new UserReviewDTO(review, user);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No review found with this id");

        }

    }


    private void copyDTOToEntity(UserReviewDTO dto, Review reviewEntity, User userEntity) {
        reviewEntity.setReview(dto.getReview());
        reviewEntity.setRating(dto.getRating());
        UserDTO byEmail = userService.findByEmail(userService.authenticated().getUsername());
        userEntity.setEmail(byEmail.getEmail());
        userEntity.setName(byEmail.getName());
        userEntity.setPassword(byEmail.getPassword());
        reviewEntity.setUserReview(userEntity);

    }


}
