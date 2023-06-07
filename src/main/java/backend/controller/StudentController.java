package backend.controller;

import backend.dto.response.ResponseMessage;
import backend.model.Student;
import backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> showListStudent() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("Create Success"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailStudent(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student student1 = studentOptional.get();
        student.setId(student1.getId());
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student st = studentOptional.get();
        student.setId(st.getId());
        student.setName(student.getName() == null ? st.getName() : student.getName());
        student.setAge(student.getAge() == null ? st.getAge() : student.getAge());
        student.setSex(student.getSex() == null ? st.getSex() : student.getSex());
        student.setBirthDay(student.getBirthDay() == null ? st.getBirthDay() : student.getBirthDay());
        System.out.println(student);
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> showPageStudent(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> showListContaining(@PathVariable String name) {
        return new ResponseEntity<>(studentService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> showListNameFull(@RequestParam("name") String name) {
        return new ResponseEntity<>(studentService.findByNameFull(name), HttpStatus.OK);
    }
}
