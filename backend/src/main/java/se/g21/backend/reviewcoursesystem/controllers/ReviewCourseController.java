package se.g21.backend.reviewcoursesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import se.g21.backend.reviewcoursesystem.entities.Rating;
import se.g21.backend.reviewcoursesystem.entities.Improvement;
import se.g21.backend.reviewcoursesystem.entities.ReviewCourse;

import se.g21.backend.enrollcoursesystem.repository.EnrollCourseRepository;
import se.g21.backend.reviewcoursesystem.repository.ReviewCourseRepository;
import se.g21.backend.reviewcoursesystem.repository.RatingRepository;
import se.g21.backend.reviewcoursesystem.repository.ImprovementRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.201:8080")
@RestController
//inject object มาก่อนใช้งานโดยการประกาศ
public class ReviewCourseController {
    @Autowired
    private final ReviewCourseRepository reviewCourseRepository;
    @Autowired
    private EnrollCourseRepository enrollCourseRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ImprovementRepository improvementRepository;
    
    //ให้ controller มาเชื่อม repository
    ReviewCourseController(ReviewCourseRepository reviewCourseRepository,EnrollCourseRepository enrollCourseRepository,RatingRepository ratingRepository,ImprovementRepository improvementRepository) {
        this.reviewCourseRepository = reviewCourseRepository;
        this.enrollCourseRepository = enrollCourseRepository;
        this.ratingRepository = ratingRepository;
        this.improvementRepository = improvementRepository;
    }

    @GetMapping("/reviewCourse/")
    public Collection<ReviewCourse> ReviewCourse() {
        return reviewCourseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/reviewCourse/{enrollCourse_id}/{rating_id}/{improvement_id}/{comment}")
    public ReviewCourse newReviewCourse(ReviewCourse newReviewCourse,
    @PathVariable long enrollCourse_id,
    @PathVariable long rating_id,
    @PathVariable long improvement_id,
    @PathVariable String comment,String datetime) {

    EnrollCourse enrollCourse = enrollCourseRepository.findById(enrollCourse_id);
    Rating rating = ratingRepository.findById(rating_id);
    Improvement improvement = improvementRepository.findById(improvement_id);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date recorddate = new Date();
    try {
        recorddate = formatter.parse((String) datetime);
    } catch (Exception e) {
        System.out.println(e);
    }
    
    
    newReviewCourse.setEnrollCourse(enrollCourse);
    newReviewCourse.setRating(rating);
    newReviewCourse.setImprovement(improvement);
    newReviewCourse.setComment(comment);
    newReviewCourse.setReviewDate(recorddate);
    
    
    return reviewCourseRepository.save(newReviewCourse); //บันทึก Objcet review
    
    }
}