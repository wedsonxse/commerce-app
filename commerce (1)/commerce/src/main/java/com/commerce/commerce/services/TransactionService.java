package com.commerce.commerce.services;
import com.commerce.commerce.adapters.Authorizer;
import com.commerce.commerce.adapters.Dispatcher;
import com.commerce.commerce.dto.TransactionDTO;
import com.commerce.commerce.entities.User;
import com.commerce.commerce.entities.UserType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final UserService userService;
    private final Authorizer authorizationGateway;
    private final Dispatcher notificationSender;
    private Session session = null;
    private Transaction transaction = null;

    @Autowired
    public TransactionService(UserService userService, @Qualifier("Auth") Authorizer authorizer, @Qualifier("Notifier") Dispatcher notifier){
        this.userService = userService;
        this.authorizationGateway = authorizer;
        this.notificationSender = notifier;
    }

    public void performTransaction(TransactionDTO request) throws Exception {
        try{
            session = session.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User payee = this.userService.findById(request.getPayee());
            User payer = this.userService.findById(request.getPayer());
            Double amount = request.getAmount();

            this.validatePayerTypeIsNotCustomer(payer);
            this.validatePayerHasEnoughCredits(payer,amount);
            this.authorizeTransaction();

            this.concludeTransaction(payer, payee, amount);

            transaction.commit();
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new Exception("Transaction service - " + e.getMessage());
        }finally{
            if(session != null){
                session.close();
            }
        }
    }
    private void validatePayerTypeIsNotCustomer(User payer) throws Exception {
        if (payer.getType() == UserType.SHOPKEEPER) {
            throw new Exception("O pagador não pode ser lojista");
        }
    }

    private void validatePayerHasEnoughCredits(User payer, Double amount) throws Exception {
        if (payer.getBalance() < amount) {
            throw new Exception("O saldo do usuário é menor do que o valor a ser transferido");
        }
    }

    private void authorizeTransaction() throws Exception {
        if (!this.authorizationGateway.authorizeTransaction()) {
            throw new Exception("Transação bloqueada!");
        }
    }

    private void concludeTransaction(User payer, User payee, Double amount) throws Exception {
        try{
            payer.setBalance(payer.getBalance() - amount);
            payee.setBalance(payee.getBalance() + amount);

            this.userService.save(payer);
            this.userService.save(payee);

            this.notificationSender.sendNotification(payee.getEmail(), payee.getName(), amount);
        }catch (Exception e){
            throw new Exception("Ocoreu um problema durante a conclusão da transação: " + e.getMessage());
        }
    }
}
