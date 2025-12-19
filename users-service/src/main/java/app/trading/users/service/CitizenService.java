package app.trading.users.service;

import app.trading.users.entity.Citizen;
import app.trading.users.repository.CitizenRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class CitizenService {

    private final CitizenRepository repository;

    public CitizenService(CitizenRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Citizen create(Citizen citizen) {
        return repository.save(citizen);
    }

    @Transactional(readOnly = true)
    public Citizen get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Citizen not found: " + id));
    }

    @Transactional(readOnly = true)
    public Page<Citizen> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Citizen update(Long id, Citizen updated) {
        Citizen existing = get(id);
        existing.setNoi_o_tinh(updated.getNoi_o_tinh());
        existing.setNoi_o_phuong_xa(updated.getNoi_o_phuong_xa());
        existing.setNoi_o_dia_chi(updated.getNoi_o_dia_chi());
        existing.setPhone_number(updated.getPhone_number());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Citizen not found: " + id);
        }
        repository.deleteById(id);
    }
}
