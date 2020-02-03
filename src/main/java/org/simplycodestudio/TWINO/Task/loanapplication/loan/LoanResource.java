package org.simplycodestudio.TWINO.Task.loanapplication.loan;



import org.simplycodestudio.TWINO.Task.loanapplication.exceptions.LoanNotFoundException;
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


    private LoanDaoService service;
    @Autowired
    public LoanResource(LoanDaoService service) {
        this.service = service;
    }


    @GetMapping("/loans")
    public List<Loan> retrieveAllLoans() {

        return service.findAllLoans();
    }

    @GetMapping("/props")
    public List<Proposal> retrieveAllProps() {

        return service.findAllProposals();
    }

    @GetMapping("/loans/{id}")
    public EntityModel<Loan> retrieveLoans(@PathVariable int id){
        Loan loan = service.findOneLoan(id);
        if (loan==null) {
            throw new LoanNotFoundException("id-" + id);
        }

        EntityModel<Loan> resource = new EntityModel<Loan>(loan);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllLoans());
        resource.add(linkTo.withRel("all-loans "));

        return resource;
    }



    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@Valid @RequestBody Loan loan, HttpServletRequest request) {
        Loan savedLoan = service.save(loan, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLoan.getId()).toUri();


        return ResponseEntity.created(location).build();

    }

    @PutMapping("/loans/{id}")
    public void prolongRepaymentDate(@PathVariable int id) {

        Loan updatedLoan= service.update(id);
        if (updatedLoan==null) {
            throw new LoanNotFoundException("id-" + id);
        }
    }




}
