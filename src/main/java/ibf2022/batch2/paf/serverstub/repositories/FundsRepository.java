package ibf2022.batch2.paf.serverstub.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.exceptions.FundsTransferException;
import ibf2022.batch2.paf.serverstub.models.Account;

@Repository
public class FundsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_USER_BY_NAME = """
        select * from accounts where full_name = ?
        """;


    private static final String UPDATE_ACCT_BY_NAME = """
        update accounts set balance = balance + ? where full_name = ?
            """;


    public Optional<Account> findAccountByName(String name){
        Account acc = jdbcTemplate.queryForObject(FIND_USER_BY_NAME, BeanPropertyRowMapper.newInstance(Account.class), name);

        if(null == acc){
            return Optional.empty();
        }
        return Optional.of(acc);
    }

    public void updateBalance(String acctName, float amount)throws FundsTransferException{
        if(jdbcTemplate.update(UPDATE_ACCT_BY_NAME, amount, acctName) <= 0){
            throw new FundsTransferException("Cannot proceed with transfer");
        }
    }
    public void deposit(String acctName, float amount) throws FundsTransferException{
       updateBalance(acctName, amount);
    }

    public void withdraw(String acctName, float amount) throws FundsTransferException{
        updateBalance(acctName, -amount);
    }
}
