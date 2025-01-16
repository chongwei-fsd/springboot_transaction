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
    public ResponseEntity<Transaction>createTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

}
