package com.javalessons.task3.task.udemy_MortgageCalc.git;


public class MortgageCalc {

    public static void main(String[] args) {

        System.out.println("для начала работы введите через пробел срок кредитования, сумму, процент, суммарный доход, количество членов семьи, [опционально планируемая доп. плата]");
        final int SHORT_LENGTH = 5;
        final int HEIGHT_LENGTH = 6;

        if (args.length >= SHORT_LENGTH && args.length <= HEIGHT_LENGTH) {

            int years = Integer.parseInt(args[0]);
            float loanAmount = Float.parseFloat(args[1]);
            float interestRate = Float.parseFloat(args[2]);
            float summaryIncome = Float.parseFloat(args[3]);
            int familyMembers = Integer.parseInt(args[4]);

            float extraMonthlyPayment = 0.0f;

            if (args.length == HEIGHT_LENGTH) {
                System.out.println("берём входные параметры ветка 2");
                extraMonthlyPayment = Float.parseFloat(args[5]);
            }
            System.out.printf("y = %3d, loan = %12.2f, intrstRate = %7.2f, sumInc = %10.2f, " +
                            "members = %2d, extra = %10.2f %n",
                    years, loanAmount, interestRate, summaryIncome, familyMembers, extraMonthlyPayment);

            Calculator calc = new Calculator(years, loanAmount, interestRate, extraMonthlyPayment);
            calc.PrintMortgage(summaryIncome, familyMembers);
        }
    }
}

