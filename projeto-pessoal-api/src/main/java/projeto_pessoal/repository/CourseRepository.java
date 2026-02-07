package projeto_pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_pessoal.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
