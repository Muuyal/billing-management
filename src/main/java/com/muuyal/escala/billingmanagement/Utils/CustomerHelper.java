package com.muuyal.escala.billingmanagement.Utils;

import com.muuyal.escala.billingmanagement.dao.impl.ContractDaoImp;
import com.muuyal.escala.billingmanagement.dao.impl.PaymentDaoImp;
import com.muuyal.escala.billingmanagement.dao.interfaces.ContractDao;
import com.muuyal.escala.billingmanagement.dao.interfaces.PaymentDao;
import com.muuyal.escala.billingmanagement.entities.Contract;
import com.muuyal.escala.billingmanagement.entities.Customer;
import com.muuyal.escala.billingmanagement.entities.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class CustomerHelper {

    PaymentDao paymentDao = new PaymentDaoImp();

    ContractDao contractDao = new ContractDaoImp();
    SimpleDateFormat DateFormat = new SimpleDateFormat("YYYY-MM-DD");


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

    public String getCustomerStatus(Customer customer) throws ParseException {

        String result = "A tiempo";

        Set<Payment> payments = paymentDao.findByCustomer(customer.getId());
        Set<Contract> contracts = contractDao.findByCustomer(customer.getId());

        for (Payment payment : payments){
            for (Contract contract : contracts){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                //            payment.getPaymentDate();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + payment.getPaymentDate());
                System.out.println("--------------------!!!!!!!!!" + payment.getPaymentDate().toString());
                try {
                    System.out.println("--------------------!!!!!!!!!" + DateFormat.parse(payment.getPaymentDate().toString()));

                    LocalDate from = LocalDate.parse(DateFormat.parse(payment.getPaymentDate().toString()).toString());
                    LocalDate to = LocalDate.now();

                    long days = ChronoUnit.DAYS.between(from, to);

                    Long limit = 0L;

                    switch (contract.getPaymentSchedule()){
                        case "Semanal":
                            limit = 7L;
                            break;

                        case "Quincenal":
                            limit = 15L;
                            break;

                        case "Mensual":
                            limit = 30L;
                            break;
                    }

                    Boolean status = paymentDao.FindStatusOk(customer.getId(), contract.getId(), limit);

                    if (status == true){
                        result = "Al dia";
                    }else{
                        result = "Atrasado";
                    }

                }catch (java.text.ParseException e){
                    e.getMessage();
                }

                //Long limit = 0L;

                /*System.out.println("--------------------!!!!!!!!!" + DateFormat.parse(payment.getPaymentDate().toString()));
                LocalDate from = LocalDate.parse(DateFormat.parse(payment.getPaymentDate().toString()).toString());
                LocalDate to = LocalDate.now();

                long days = ChronoUnit.DAYS.between(from, to);
                Long limit = 0L;

                /*if (days > 7L) {
                    result = "Atrasado";
                }

                switch (contract.getPaymentSchedule()){
                    case "Semanal":
                        limit = 7L;
                    break;

                    case "Quincenal":
                        limit = 15L;
                    break;

                    case "Mensual":
                        limit = 30L;
                    break;
                }

                Boolean status = paymentDao.FindStatusOk(customer.getId(), contract.getId(), limit);

                if (status == true){
                    result = "Al dia";
                }else{
                    result = "Atrasado";
                }*/

            }
        }
        return result;
    }

}
