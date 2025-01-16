package transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transaction.entity.Transaction;
import transaction.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    // Create a Transaction
    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }


}
