package app.trading.stock.repositories;

import app.trading.stock.entity.Symbols;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolsRepository extends JpaRepository<Symbols, String> {
}
