package com.academy.hdemo.controller;

import com.academy.hdemo.dto.Account;
import com.academy.hdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/add")
    public void newAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }
    @GetMapping()
    public List allAccount() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/detail/{id}")
    public Account detail(@PathVariable long id) {
        return accountService.findById(id);
    }

    @PutMapping("/update/{id}")
    void updateAccount(@PathVariable long id, @ModelAttribute Account updateAccount, ModelAndView model) {
        accountService.update(id,updateAccount);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable long id) {
        accountService.deleteAccount(id);
    }


    @PostMapping("/transaction")
    public String transferMoney(@RequestParam("fromAccountId") long from,@RequestParam("toAccountId") long to,@RequestParam("amount")  long amount){
       return accountService.transferMoney(from,to,amount);
    }

}
