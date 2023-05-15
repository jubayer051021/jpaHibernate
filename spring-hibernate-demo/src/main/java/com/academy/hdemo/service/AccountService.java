package com.academy.hdemo.service;


import com.academy.hdemo.dao.AccountDAO;
import com.academy.hdemo.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    @Qualifier("AccountDAOImp2")
    AccountDAO accountDAO;

    public Account findById(long id) {
        return accountDAO.findByAccountId(id);
    }

    public void saveAccount(Account account) {
        accountDAO.save(account);
    }

    public void update(long id,Account account) {
        Account existingAccount=accountDAO.findByAccountId(id);
        if (existingAccount!=null){
            existingAccount.setFirstName(account.getFirstName());
            existingAccount.setSecondName(account.getSecondName());
            existingAccount.setBalance(account.getBalance());
            accountDAO.update(existingAccount);
        }
    }
    public void deleteAccount(long id) {
        Account existingAccount=accountDAO.findByAccountId(id);
        accountDAO.delete(existingAccount);
    }
      public String transferMoney(long from,long to, long amount) {
       Account fromAccount=accountDAO.findByAccountId(from);
       if (fromAccount==null){
           return "Sender Account is not exist";
       }
       Account toAccount=accountDAO.findByAccountId(to);
          if (toAccount==null){
              return "Receiver Account is not exist";
          }
      if(fromAccount.getBalance()<amount){
          return "Balance is insufficient";
      }
      fromAccount.setBalance(fromAccount.getBalance()-amount);
      toAccount.setBalance(toAccount.getBalance()+amount);
      accountDAO.update(fromAccount);
      accountDAO.update(toAccount);
      return "Successfully transaction";
    }
    public List getAllAccounts() {
        return accountDAO.accountList();
    }

}
