package transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transaction.entity.Transaction;
import transaction.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    // Create a Transaction
    public Transaction createTransaction(Transaction transaction) {
        Transaction newTransaction=new Transaction(transaction.getDescription(),transaction.getAmount());
        return repository.save(newTransaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // Get a single transaction by id
    public Transaction getTransactionById(int id){
        Optional<Transaction>findTransaction=repository.findById(id);
        return findTransaction.orElse(null);
    }

    // Update an existing transaction
    public Transaction updateTransaction(int id,Transaction updatedTransaction){
        return repository.findById(id)
                .map(t->{
                    t.setDescription(updatedTransaction.getDescription());
                    t.setAmount(updatedTransaction.getAmount());
                    t.setTransactionDate(updatedTransaction.updateDate());
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
