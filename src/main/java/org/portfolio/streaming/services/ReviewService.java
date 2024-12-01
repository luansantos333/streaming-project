package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final UserUtil userUtil;


    public ReviewService(ReviewRepository reviewRepository, UserService userService, UserUtil userUtil) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.userUtil = userUtil;
    }

    /*

    @Transactional
    public UserReviewDTO newReview (UserReviewDTO reviewDTO) {

        Review review = new Review();
        User user = new User();
        copyDTOToEntity(reviewDTO, review, user);
        Review save = reviewRepository.save(review);
        return new UserReviewDTO(review, user);

    }

*/
    @Transactional (readOnly = true)
    public UserReviewMinDTO findByReviewId (Long id) {

        UserReviewProjection userReviewProjection = reviewRepository.searchReviewAndUserByReviewId(id).orElseThrow(() -> new ResourceNotFoundException("No review found with this id"));

        return new UserReviewMinDTO(userReviewProjection);
    }

    /*
    @Transactional (readOnly = true)
    public Page<UserReviewDTO> findAllReviews (Long rating) {



    }
*/


    /*
    private void copyDTOToEntity (UserReviewDTO dto, Review reviewEntity, User userEntity ) {
        reviewEntity.setReview(dto.getReview());
        reviewEntity.setRating(dto.getRating());
        UserDTO byEmail = userService.findByEmail(userUtil.getLoggedUsername());
        userEntity.setEmail(byEmail.getEmail());
        userEntity.setName(byEmail.getName());
        userEntity.setPassword(byEmail.getPassword());
        reviewEntity.setUserReview(userEntity);

    }

*/
}
