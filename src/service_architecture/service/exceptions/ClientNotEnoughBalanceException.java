package service_architecture.service.exceptions;

public class ClientNotEnoughBalanceException  extends Exception{
    public ClientNotEnoughBalanceException(String str) {
        super(str);
    }
}
