package org.simplycodestudio.TWINO.Task.loanapplication.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

@RestController
public class LoanResource {

    //@Autowired
    private LoanDaoService service;
    @Autowired
    public LoanResource(LoanDaoService service) {
        this.service = service;
    }


    @GetMapping("/loans")
    public List<Loan> retrieveAllLoans() {

        return service.findAll();
    }

    @GetMapping("/loans/{id}")
    public EntityModel<Loan> retrieveLoans(@PathVariable int id){
        Loan loan = service.findOne(id);
        if (loan==null) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel<Loan> resource = new EntityModel<Loan>(loan);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllLoans());
        resource.add(linkTo.withRel("all-users "));

        return resource;
    }

    @DeleteMapping("/loans/{id}")
    public void deleteLoans(@PathVariable int id){
        Loan loan = service.deleteById(id);
        if (loan==null) {
            throw new UserNotFoundException("id-" + id);
        }
    }


    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@Valid @RequestBody Loan loan, @Valid @RequestBody Proposal proposal, HttpServletRequest request) {
        Loan savedLoan = service.save(loan);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLoan.getId()).toUri();

        return ResponseEntity.created(location).build();

    }




}
