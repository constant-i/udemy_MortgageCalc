package com.javalessons.task3.task;

public class Calculator {
    private static final int MONTH_IN_YEAR = 12;        // месяцев в году

    private int years;                                  // на сколько лет выдаётся кредит
    private float loanAmount;                           // величина кредита
//    private float interestRate;                         // ежегодный процент по кредиту
    private float extraMonthlyPayment;                  // сумма досрочного погашения
    private int month;                                  // на сколько месяцев выдаётся кредит
    private float staringBalance;
    private float interestRatePerMonth;                 // ежемесячный % выраженный в дробях
    private float endingBalance;                        // остаток задолженности по основному кредиту
    private float totalInterest;                        // сумма переплаты


    float getExtraMonthlyPayment() {
        return extraMonthlyPayment;
    }

    float getTotalInterest() {
        return totalInterest;
    }

    public float getStaringBalance() {
        return staringBalance;
    }

    void setStaringBalance(float staringBalance) {
        this.staringBalance = staringBalance;
    }

    public int getMonth() {
        return month;
    }

    void setTotalInterest(float totalInterest) {
        this.totalInterest = totalInterest;
    }

    void setEndingBalance(float endingBalance) {
        this.endingBalance = endingBalance;
    }

    // конструктор 3 параметрами
    Calculator(int years, float loanAmount, float interestRate) {
        this.years = years;
//        this.interestRate = interestRate;
        this.loanAmount = loanAmount;
        month = years * MONTH_IN_YEAR;
        interestRatePerMonth = (interestRate / MONTH_IN_YEAR) * 0.01F;    // ежемесячный % выраженный в дробях
        endingBalance = loanAmount;

    }

    // конструктор 4 параметрами
    public Calculator(int years, float loanAmount, float interestRate, float extraMonthlyPayment) {
        this(years, loanAmount, interestRate);
        this.extraMonthlyPayment = extraMonthlyPayment;
    }


    // метод возвращает величину расчитанного аннуитентного платежа
    float Payment() {
        double i = interestRatePerMonth;
        int n = month;
        // !!! это работает !!!!
        double payment = loanAmount * (i * Math.pow((1 + i), n)) / (Math.pow((1 + i), n) - 1);
        // !!! это то-же работает !!!!
        //double payment = (loanAmount/*endingBalance*/ * i) / (1 - Math.pow((1 + i), -n));
        return (float) payment;
    }

    // метод возвращает величину идущую на оплату процентов в текущем периоде
    float Interest() {
        return endingBalance * interestRatePerMonth;
    }

    float Principal() {
        return Payment() + extraMonthlyPayment - Interest();
    }

    float EndingBalance(){
        return endingBalance - Principal();
    }

    float getEndingBalance() {
        return endingBalance;
    }
}
