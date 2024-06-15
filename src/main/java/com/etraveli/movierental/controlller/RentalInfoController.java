package com.etraveli.movierental.controlller;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.service.MovieService;
import com.etraveli.movierental.service.RentalInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/rentals")
public class RentalInfoController {

    private final RentalInfoService rentalInfoService;

    public RentalInfoController(RentalInfoService rentalInfoService) {
        this.rentalInfoService = rentalInfoService;
    }

    @GetMapping("/statement")
    public ResponseEntity<String> generateStatement(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(rentalInfoService.generateStatement(customer));
    }

}
