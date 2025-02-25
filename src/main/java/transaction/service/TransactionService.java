package transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transaction.entity.Transaction;
import transaction.repository.TransactionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    // Create a Transaction
    public Transaction createTransaction(Transaction transaction) {
        Transaction existingDesc = repository.findByDescription(transaction.getDescription());
        if (existingDesc != null) { //description exists
            return null;
        }
        Transaction newTransaction = new Transaction(transaction.getDescription(), transaction.getAmount());
        return repository.save(newTransaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // Get a single transaction by id
    public Transaction getTransactionById(int id) {
        Optional<Transaction> findTransaction = repository.findById(id);
        return findTransaction.orElse(null);
    }

    // Update an existing transaction
    public Transaction updateTransaction(int id, Transaction updatedTransaction) {
        return repository.findById(id)
                .map(t -> {
                    t.setDescription(updatedTransaction.getDescription());
                    t.setAmount(updatedTransaction.getAmount());
                    t.updateDate();
                    return repository.save(t);
                }).orElse(null);
    }

    // Delete a Transaction
    public boolean deleteTransaction(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
