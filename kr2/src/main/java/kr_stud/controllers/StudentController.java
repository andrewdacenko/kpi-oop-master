package kr_stud.controllers;

import kr_stud.dao.StudentDao;
import kr_stud.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    public List<Student> findAll() {
        return (List<Student>) studentDao.findAll();
    }

    @RequestMapping(value = "/best", produces = "application/json")
    @ResponseBody
    public List<Student> getBestOfSecondCourse() {
        return (List<Student>) studentDao.findBestOfSecondCourse();
    }

    @RequestMapping(value = "/good", produces = "application/json")
    @ResponseBody
    public List<Student> getGoodForeign() {
        return (List<Student>) studentDao.findGoodForeign();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Student createStudent(@RequestBody Student student) {
        studentDao.save(student);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteStudent(@PathVariable long id) {
        studentDao.delete(id);

        return "{}";
    }
}
