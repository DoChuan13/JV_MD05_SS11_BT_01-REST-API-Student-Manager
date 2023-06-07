package backend.service;

import backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Iterable<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    Optional<Student> findById(Long id);

    void save(Student student);

    void deleteById(Long id);

    Iterable<Student> findAllByNameContaining(String name);

    Iterable<Student> findByNameFull(String name);
}
