package kr_stud.controllers;

import kr_stud.dao.SubjectDao;
import kr_stud.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectDao subjectDao;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    public List<Subject> findAll() {
        return (List<Subject>) subjectDao.findAll();
    }
}
