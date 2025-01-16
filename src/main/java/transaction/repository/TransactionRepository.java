package transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transaction.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByDescription(String desc);
}
