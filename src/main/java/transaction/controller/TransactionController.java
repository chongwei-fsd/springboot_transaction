package transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transaction.entity.Transaction;
import transaction.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    //create a transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
    }

    // Get all transanctions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // Get a single transaction by id
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction != null //if exists
                ? ResponseEntity.ok(transaction)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // update a transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction updatedTransaction) {
        Transaction transaction = transactionService.updateTransaction(id, updatedTransaction);
        return transaction != null
                ? ResponseEntity.ok(transaction)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        boolean isDeleted = transactionService.deleteTransaction(id);
        return isDeleted
                ? ResponseEntity.ok("Transaction deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found.");
    }


}
