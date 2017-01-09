package kr_stud.controllers;

import kr_stud.dao.MarkDao;
import kr_stud.models.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarkController {
    @Autowired
    private MarkDao markDao;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Mark createMark(@RequestBody Mark student) {
        markDao.save(student);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteMark(@PathVariable long id) {
        markDao.delete(id);

        return "{}";
    }
}
