package backend.repository;

import backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Iterable<Student> findAllByNameContaining(String name);

    @Query("select st from Student as st where st.name like concat('%',:name,'%') ")
    Iterable<Student> findByNameFull(@Param("name") String name);
}
