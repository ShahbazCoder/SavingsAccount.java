import java.util.*;

class Account {

    private String name;
    private long accNo;
    private double balanceAmount;

    public static long trans = 0;

    public Account() {
        this.name="";
        this.accNo=143;
        this.balanceAmount=0;
    }

    public Account(String name, long accNo, double balanceAmount) {
        this.name = name;
        this.accNo = accNo;
        this.balanceAmount = balanceAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccNo() {
        return accNo;
    }

    public void setAccNo(long accNo) {
        this.accNo = accNo;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void deposit(double d) {
        
        balanceAmount += d;

        trans++;
    }

    public void withdrawal(double w) {
        if(w<=balanceAmount){
       	balanceAmount -= w;

        trans++;
        }
    }

    public String toString() {
        return "Name:"+name+"\nAccount No.:"+ accNo+"\nAccount Balance:"+ balanceAmount;
    }
}

class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    public SavingsAccount(String name, long accNo, double balanceAmount, double interestRate) {
        super.setName(name);
        super.setAccNo(accNo);
        if (balanceAmount>=500){
            super.setBalanceAmount(balanceAmount);
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double computeInterest(){
        double SI= (super.getBalanceAmount()*interestRate)/100;
        super.setBalanceAmount(super.getBalanceAmount()+SI);
        return SI;
    }

    public void deposit(double d) {
        if (d>0){
            super.deposit(d);
        }
    }

    public void withdrawal(double w) {
        if (w<=super.getBalanceAmount()){
            super.withdrawal(w);
            if (super.getBalanceAmount()<=500){
                super.setBalanceAmount(super.getBalanceAmount()-10);
            }
        }
    }

    public String toString() {
        return super.toString()+"\nInterest : "+computeInterest()+"\nBalance Amount : "+super.getBalanceAmount();
    }
}

class CurrentAccount extends Account{
    private double overDraftLimit;

    public CurrentAccount(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public CurrentAccount(String name, long accNo, double balanceAmount, double over) {
        super.setName(name);
        super.setAccNo(accNo);
        if (balanceAmount>=500){
            super.setBalanceAmount(balanceAmount);
        }
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }


    public void deposit(double d) {
        if (d>0){
            super.deposit(d);
        }
    }

    public void withdrawal(double w) {
            if (super.getBalanceAmount()-w<=-overDraftLimit){
                super.setBalanceAmount(super.getBalanceAmount()-w-50);
                trans++;
            }
            else{
                super.withdrawal(w);
            }

    }

    public String toString() {
        return super.toString()+"\nBalance Amount : "+super.getBalanceAmount();
    }

}

class TestBankingSystem{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name, type;
        long accNo;
        double balanceAmount;

        System.out.print("Enter Account Holder's Name : ");
        name = sc.nextLine();

        System.out.print("Enter Account Type : ");
        type = sc.nextLine();

        System.out.print("Enter Account Number : ");
        accNo = sc.nextLong();

        System.out.print("Enter Balance Amount : ");
        balanceAmount = sc.nextDouble();

         
        System.out.println ("Enter interest rate: ");
        double interestRate = sc.nextDouble();
        System.out.println("----------------------Savings Account-------------------------");
        SavingsAccount S = new SavingsAccount(name,accNo,balanceAmount,interestRate);
        System.out.println(S);

        System.out.println ("Enter deposit amount : ");
        double deposit = sc.nextDouble();

        S.deposit(deposit);
        System.out.println(S.getBalanceAmount());

        System.out.println ("Enter the amount to withdraw: ");
        double withdrawal = sc.nextDouble();
        S.withdrawal(withdrawal);

        System.out.println("Balance:"+S.getBalanceAmount());

        /*S.withdrawal(500);
        System.out.println(S.getBalanceAmount());

        S.withdrawal(240);
        System.out.println(S.getBalanceAmount());

        S.withdrawal(500);
        System.out.println(S.getBalanceAmount());*/
        System.out.println("----------------------Current Account-------------------------");
        CurrentAccount C = new CurrentAccount(name,accNo,balanceAmount,5000);
        System.out.println(C);

        System.out.println ("Enter deposit amount : ");
        deposit = sc.nextDouble();

        C.deposit(deposit);
        System.out.println("Balance:"+C.getBalanceAmount());

        System.out.println ("Enter the amount to withdraw: ");
        withdrawal = sc.nextDouble();
        C.withdrawal(withdrawal);

        System.out.println("Balance:"+C.getBalanceAmount());

    }
}

