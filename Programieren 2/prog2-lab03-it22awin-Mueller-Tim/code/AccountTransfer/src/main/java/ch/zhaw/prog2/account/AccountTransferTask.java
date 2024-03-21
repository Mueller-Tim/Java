package ch.zhaw.prog2.account;

class AccountTransferTask implements Runnable {

    private final Account fromAccount;
    private final Account toAccount;
    private final int amount;
    private int totalTransfer = 0;

    public AccountTransferTask(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public int getTotalTransfer() {
        return this.totalTransfer;
    }

    @Override
    public void run() {
        transferWithoutDeadLock();
    }


    private void transfer() {
        // Account must not be overdrawn
        synchronized (toAccount){
            synchronized (fromAccount){
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.transferAmount(-amount);
                    toAccount.transferAmount(amount);
                    totalTransfer += amount;
                }
            }
        }
    }

    private void transferWithoutDeadLock(){
        boolean isLower = fromAccount.getId() < toAccount.getId();
        Account lowerAccount = isLower ? fromAccount : toAccount;
        Account higherAccount = !isLower ? fromAccount : toAccount;

        synchronized (lowerAccount){
            synchronized (higherAccount){
                if(fromAccount.getBalance() >= amount){
                    fromAccount.transferAmount(-amount);
                    toAccount.transferAmount(amount);
                    totalTransfer += amount;
                }
            }
        }
    }
}
