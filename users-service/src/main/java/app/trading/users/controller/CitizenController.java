package app.trading.users.controller;

import app.trading.users.entity.Citizen;
import app.trading.users.service.CitizenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    private final CitizenService service;

    public CitizenController(CitizenService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Citizen create(@RequestBody Citizen citizen) {
        return service.create(citizen);
    }

    @GetMapping("/{id}")
    public Citizen get(@PathVariable Long id) {
        try {
            return service.get(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public Page<Citizen> list(Pageable pageable) {
        return service.list(pageable);
    }

    @PutMapping("/{id}")
    public Citizen update(@PathVariable Long id, @RequestBody Citizen citizen) {
        try {
            return service.update(id, citizen);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
