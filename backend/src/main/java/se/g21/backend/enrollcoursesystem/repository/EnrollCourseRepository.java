package se.g21.backend.enrollcoursesystem.repository;

import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@CrossOrigin(origins = "http://172.17.0.201:8080")
@RepositoryRestResource
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Long> {
    EnrollCourse findById(long id);

    @Query( value = "SELECT * FROM ENROLL_COURSE INNER JOIN STUDENT ON ENROLL_COURSE.STUDENT_ID = STUDENT.STUDENT_ID WHERE STUDENT.FULLNAME LIKE :fullname ",
            nativeQuery = true)
    Collection<EnrollCourse> findByStudentName(@Param("fullname") String fullname);
}
