package com.websystique.springmvc.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.Company;
import com.websystique.springmvc.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    

    
    
    //-------------------Retrieve All companies--------------------------------------------------------
     
    @RequestMapping(value = "/company/", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> listAllcompanies() {
        List<Company> companies = userService.findAllCompanies();
        if(companies.isEmpty()){
            return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Company--------------------------------------------------------
     
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> getCompany(@PathVariable("id") long id) {
        System.out.println("Fetching Company with id " + id);
        Company company = userService.findById(id);
        if (company == null) {
            System.out.println("Company with id " + id + " not found");
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Company--------------------------------------------------------
     
    @RequestMapping(value = "/company/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody Company company,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Company " + company.getcompanyName());
 
        if (userService.isCompanyExist(company)) {
            System.out.println("A Company with name " + company.getcompanyName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveCompany(company);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Company --------------------------------------------------------
     
    @RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
        System.out.println("Updating Company " + id);
         
        Company currentCompany = userService.findById(id);
         
        if (currentCompany==null) {
            System.out.println("Company with id " + id + " not found");
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
 
        currentCompany.setcompanyName(company.getcompanyName());
         currentCompany.setOwner(company.getOwner());
        currentCompany.setAddress(company.getAddress());
        currentCompany.setCity(company.getCity());
        currentCompany.setCountry(company.getCountry());
        currentCompany.setPhoneNum(company.getPhoneNum());
        currentCompany.setEmail(company.getEmail());
         
        userService.updateCompany(currentCompany);
        return new ResponseEntity<Company>(currentCompany, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Company --------------------------------------------------------
     
    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Company with id " + id);
 
        Company company = userService.findById(id);
        if (company == null) {
            System.out.println("Unable to delete. Company with id " + id + " not found");
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteCompanyById(id);
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All companies --------------------------------------------------------
     
    @RequestMapping(value = "/company/", method = RequestMethod.DELETE)
    public ResponseEntity<Company> deleteAllCompanies() {
        System.out.println("Deleting All companies");
 
        userService.deleteAllCompanies();
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }
 
}