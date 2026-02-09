package com.glint.disttransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.transaction.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class AtomikosJtaConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager tm = new UserTransactionManager();
        tm.setForceShutdown(false);
        return tm;
    }

    @Bean
    public UserTransactionImp atomikosUserTransaction() throws Throwable {
        UserTransactionImp ut = new UserTransactionImp();
        ut.setTransactionTimeout(300);
        return ut;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() throws Throwable {
        return new JtaTransactionManager(
                (jakarta.transaction.UserTransaction) atomikosUserTransaction(),
                (TransactionManager) atomikosTransactionManager()
        );
    }
}
