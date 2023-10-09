package com.commerce.commerce.services;
import com.commerce.commerce.adapters.Authorizer;
import com.commerce.commerce.adapters.Dispatcher;
import com.commerce.commerce.dto.TransactionDTO;
import com.commerce.commerce.entities.User;
import com.commerce.commerce.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final UserService userService;
    private final Authorizer authorizationGateway;

    private final Dispatcher notificationSender;

    @Autowired
    public TransactionService(UserService userService, @Qualifier("Auth") Authorizer authorizer, @Qualifier("Notifier") Dispatcher notifier){
        this.userService = userService;
        this.authorizationGateway = authorizer;
        this.notificationSender = notifier;
    }

    public void performTransaction(TransactionDTO request) throws Exception {
        try{
            User payee = this.userService.findById(request.getPayee());
            User payer = this.userService.findById(request.getPayer());
            Double amount = request.getAmount();

            if (payer.getType() == UserType.SHOPKEEPER) {
                throw new Exception("O pagador não pode ser lojista");
            }

            if (payer.getBalance() < amount) {
                throw new Exception("O saldo do usuário é menor do que o valor a ser transferido");
            }

            payer.setBalance(payer.getBalance() - amount);
            payee.setBalance(payee.getBalance() + amount);

            if (!this.authorizationGateway.authorizeTransaction()) {
                throw new Exception("Transação bloqueada!");
            }
            this.userService.save(payer);
            this.userService.save(payee);

            this.notificationSender.sendNotification(payee.getEmail(), payee.getName(), amount);
        }catch(Exception e){
            throw new Exception("Transaction service - " + e.getMessage());
        }
    }
}
