package com.javalessons.task3.task.udemy_MortgageCalc.git;

class Calculator {
    private static final int MONTH_IN_YEAR = 12;        // месяцев в году

    private int years;                                  // на сколько лет выдаётся кредит
    private float loanAmount;                           // величина кредита
    private float extraMonthlyPayment;                  // сумма досрочного погашения
    private int month;                                  // на сколько месяцев выдаётся кредит
    private float staringBalance;
    private float interestRatePerMonth;                 // ежемесячный % выраженный в дробях
    private float endingBalance;                        // остаток задолженности по основному кредиту
    private float totalInterest;                        // сумма переплаты
    private int currentMonth = 0;

    private void setEndingBalance(float endingBalance) {
        this.endingBalance = endingBalance;
    }

    private float getEndingBalance() {
        return endingBalance;
    }

    private Calculator(int years, float loanAmount, float interestRate) {
        this.years = years;
        this.loanAmount = loanAmount;
        month = years * MONTH_IN_YEAR;
        interestRatePerMonth = (interestRate / MONTH_IN_YEAR) * 0.01F;    // ежемесячный % выраженный в дробях
        endingBalance = loanAmount;
    }

    Calculator(int years, float loanAmount, float interestRate, float extraMonthlyPayment) {
        this(years, loanAmount, interestRate);
        this.extraMonthlyPayment = extraMonthlyPayment;
    }

    // метод возвращает величину расчитанного аннуитентного платежа
    private float Payment() {
        double i = interestRatePerMonth;
        int n = month;
        // !!! это работает !!!!
        double payment = loanAmount * (i * Math.pow((1 + i), n)) / (Math.pow((1 + i), n) - 1);
        //double payment = (loanAmount * i) / (1 - Math.pow((1 + i), -n));
        return (float) payment;
    }

    // метод возвращает величину идущую на оплату процентов в текущем периоде
    private float Interest() {
        return endingBalance * interestRatePerMonth;
    }

    private float Principal() {
        return Payment() + extraMonthlyPayment - Interest();
    }

    private float EndingBalance(){
        return endingBalance - Principal();
    }

    // выводим график платежей
    void PrintMortgage (float summaryIncome, int familyMembers) {

        float payment = Payment();
        if (((summaryIncome * 0.5 > payment) && (familyMembers == 1)) ||
                ((summaryIncome * 0.45 > payment) && (familyMembers == 2)) ||
                ((summaryIncome * 0.35 > payment) && (familyMembers == 3)) ||
                ((summaryIncome * 0.30 > payment) && (familyMembers == 4)) ||
                ((summaryIncome * 0.25 > payment) && (familyMembers == 5))) {

            payment += extraMonthlyPayment;

            System.out.println("MONTH, STARING_BALANCE, PAYMENT,     INTEREST,  PRINCIPAL,   ENDING_BALANCE, TOTAL_INTEREST");
            do {
                currentMonth++;
                staringBalance = endingBalance;
                endingBalance = EndingBalance();
                float principal = Principal();
                float interest = Interest();
                totalInterest += interest;
                if (staringBalance < payment) {
                    payment = principal = staringBalance;
                    setEndingBalance(-0.0001F);
                    endingBalance = getEndingBalance();
                }
                System.out.printf("%3d; %13.2f; %14.2f; %10.2f; %10.2f; %12.2f; %12.2f%n",
                        currentMonth, staringBalance, payment, interest, principal, endingBalance, totalInterest);
            } while ((endingBalance >= 0) /*&& currentMonth < calc.getMonth()*/);

        } else {
            System.out.printf("К нашему сожалению кредит на %10.2f руб." +
                    "при доходе %9.2f руб. на срок %3d лет " +
                    "и ежемесечном платеже %8.2f руб. НЕВОЗМОЖЕН :(", loanAmount, summaryIncome, years, payment);
        }
    }
}
