package kr_stud.controllers;

import kr_stud.dao.CountryDao;
import kr_stud.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryDao countryDao;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    public List<Country> findAll() {
        return (List<Country>) countryDao.findAll();
    }
}
