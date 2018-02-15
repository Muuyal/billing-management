package com.muuyal.escala.billingmanagement.Utils;

import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.PaymentDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class CustomerHelper {

    PaymentDao paymentDao = new PaymentDaoImp();

    ContractDao contractDao = new ContractDaoImp();

    public Double getCustomerDebt(Customer customer){

        Double result = 0D, debt = 0D, payed = 0D;

        Set<Payment> payments = paymentDao.findByCustomer(customer.getId());
        for (Payment payment : payments){
            payed = payed + payment.getPaymentAmount();
        }

        Set<Contract> contracts = contractDao.findByCustomer(customer.getId());
        for (Contract contract : contracts){
            debt = debt + contract.getFinalPrice();
        }

        result = debt - payed;
        return result;

    }

    public String getCustomerStatus(Customer customer){

        String result = "A tiempo";

        Set<Payment> payments = paymentDao.findByCustomer(customer.getId());
        for (Payment payment : payments){

            payment.getPaymentDate();
            LocalDate from = LocalDate.parse(payment.getPaymentDate().toString());
            LocalDate to = LocalDate.now();

            long days = ChronoUnit.DAYS.between(from, to);

            if (days > 7L) {
                result = "Atrasado";
            }

        }
        return result;
    }

}