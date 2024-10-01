package az.writhline.product.controller;


import az.writhline.product.model.Account;
import az.writhline.product.repository.AccountRepository;
import az.writhline.product.service.TransferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
@Slf4j
public class TransferController {



    private final TransferService transferService;
    private final AccountRepository accountRepository;

    @GetMapping("/{id}")
    @Transactional
    public Account getAccount(@PathVariable Long id) {
        log.info("Thread {} - Getting account with id {}",Thread.currentThread().getName(), id);
        var acc=  accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        log.info("Thread {} - Getting account with id {} result {}",Thread.currentThread().getName(), id, acc);
        return acc;
    }

    @GetMapping("/update/{id}")
    @Transactional
    public void updateAccount(@PathVariable Long id) {
//        Account account = accountRepository.find(id);
//        account.setBalance(account.getBalance() + 1000);
//        accountRepository.save(account);
    }

    @GetMapping("/1/{amount}")
    public void transfer1(@PathVariable Integer amount) {
        transferService.transfer(1, 2, amount);
    }

    @GetMapping("/2/{amount}")
    public void transfer2(@PathVariable Integer amount) {
        transferService.transfer(2, 1, amount);
    }



}
