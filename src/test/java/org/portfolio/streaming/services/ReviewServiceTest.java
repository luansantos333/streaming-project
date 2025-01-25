package org.portfolio.streaming.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.portfolio.streaming.config.TestConfig;
import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.factories.ReviewFactory;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class ReviewServiceTest {


    @InjectMocks
    private ReviewService service;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserService userService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private UserRepository userRepository;
    private Long validReviewId;
    private Long nonExistentReviewId;
    private UserReviewMinDTO userReviewMinDTO;
    private Review review;
    private UserReviewProjection userReviewProjection;
    private UserReviewMinDTO updatedUserReviewMinDTO;

    @BeforeEach
    public void setUp() {


        validReviewId = 1L;
        nonExistentReviewId = 2L;
        userReviewMinDTO = ReviewFactory.getDefaultUserReviewMinDTO();
        review = ReviewFactory.getDefaultReview();
        userReviewProjection = ReviewFactory.getDefaultUserReviewProjection();
        updatedUserReviewMinDTO = new UserReviewMinDTO("ANOTHER USERNAME",
                userReviewMinDTO.getUserId(), "ANOTHER REVIEW", 0.0, userReviewMinDTO.getMovie());


        Mockito.when(userService.authenticated()).thenReturn(review.getUserReview());
        Mockito.when(userRepository.findByEmail(review.getUserReview().getEmail())).thenReturn(Optional.ofNullable(review.getUserReview()));
        Mockito.when(reviewRepository.save(ArgumentMatchers.any())).thenReturn(review);
        Mockito.when(movieRepository.findById(review.getMovie().getId())).thenReturn(Optional.ofNullable(review.getMovie()));
        Mockito.when(reviewRepository.searchReviewMovieAndUserByReviewId(validReviewId)).thenReturn(Optional.ofNullable(userReviewProjection));
        Mockito.doThrow(ResourceNotFoundException.class).when(reviewRepository).searchReviewsByMovieId(nonExistentReviewId);
        Mockito.doThrow(ResourceNotFoundException.class).when(reviewRepository).existsById(nonExistentReviewId);
        Mockito.when(reviewRepository.existsById(validReviewId)).thenReturn(true);
        Mockito.when(reviewRepository.getReferenceById(validReviewId)).thenReturn(review);
        Mockito.doThrow(ResourceNotFoundException.class).when(reviewRepository).getReferenceById(nonExistentReviewId);
        Mockito.when(movieRepository.getReferenceById(validReviewId)).thenReturn(review.getMovie());

    }


    @Test
    public void whenValidObjectSavedThenReturnUserReviewMinDTO() {

        UserReviewMinDTO savedReview = service.newReview(userReviewMinDTO);

        Assertions.assertEquals(userReviewMinDTO.getReview(), savedReview.getReview());
        Assertions.assertEquals(userReviewMinDTO.getUserId(), savedReview.getUserId());
        Assertions.assertEquals(userReviewMinDTO.getMovie(), savedReview.getMovie());
        Assertions.assertEquals(userReviewMinDTO.getRating(), savedReview.getRating());
        Assertions.assertEquals(userReviewMinDTO.getUsername(), savedReview.getUsername());

    }

    @Test
    public void whenValidReviewIdThenReturnUserReviewMinDTO() {

        UserReviewMinDTO byReviewId = service.findByReviewId(validReviewId);

        Assertions.assertEquals(byReviewId.getUsername(), userReviewMinDTO.getUsername());


    }

    @Test
    public void whenNonExistentReviewIdThenThrowResourceNotFoundException() {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findByReviewId(nonExistentReviewId);

        });


    }

    @Test
    public void whenIdIsValidDeleteReviewIfUserIsOwnerOrAdmin() {

        service.deleteReviewById(validReviewId);

    }


    @Test
    public void whenIdNonExistentOnDeleteReviewByIdThenThrowResourceNotFoundException() {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            service.deleteReviewById(nonExistentReviewId);

        });


    }

    @Test
    public void whenIdIsValidAndUserIsOwnerOrAdminThenUpdateReview () {


        UserReviewMinDTO update = service.updateReview(updatedUserReviewMinDTO, validReviewId);
        Assertions.assertNotEquals(userReviewMinDTO.getReview(), update.getReview());
        Assertions.assertEquals(userReviewMinDTO.getUsername(), update.getUsername());
        Assertions.assertNotEquals(userReviewMinDTO.getRating(), update.getRating());
        Assertions.assertEquals(userReviewMinDTO.getUserId(), update.getUserId());
        Assertions.assertEquals(userReviewMinDTO.getMovie(), update.getMovie());

    }

    @Test
    public void whenIdIsNonExistentOnUpdateReviewThenThrowResourceNotFoundException () {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            service.updateReview(updatedUserReviewMinDTO, nonExistentReviewId);

        });



    }


}
