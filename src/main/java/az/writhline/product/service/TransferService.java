package az.writhline.product.service;

import az.writhline.product.model.Account;
import az.writhline.product.repository.AccountRepository;
import jakarta.persistence.EntityManagerFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {
    private final AccountRepository accountRepository;



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transfer(long from, long to, double amount) {
        log.info("Thread {} - Getting account with id {}", Thread.currentThread().getName(), from);
        Account fromAccount = accountRepository.findById(from)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        log.info("Thread {} - Account with id {} is {}", Thread.currentThread().getName(), from, fromAccount);
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Not enough balance");
        }
        log.info("Sleeping...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Thread {} - Getting account with id {}", Thread.currentThread().getName(), to);
        Account toAccount = accountRepository.findById(to)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        log.info("Thread {} - Account with id {} is {}", Thread.currentThread().getName(), to, toAccount);
        log.info("Thread {} - Changing balance of account {}", Thread.currentThread().getName(), from);
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        log.info("Thread {} - Changing balance of account {}", Thread.currentThread().getName(), to);
        toAccount.setBalance(toAccount.getBalance() + amount);
        log.info("Thread {} - Saving account with id {}", Thread.currentThread().getName(), from);
        accountRepository.save(fromAccount);
        log.info("Thread {} - Saving account with id {}", Thread.currentThread().getName(), to);
        accountRepository.save(toAccount);
    }

}




