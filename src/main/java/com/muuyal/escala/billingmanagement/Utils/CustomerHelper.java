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

        String result = "";

        Set<Payment> payments = paymentDao.findByCustomer(customer.getId());
        //Set<Contract> contracts = contractDao.findByCustomer(customer.getId());

        for ( Payment payment : payments ){

            System.out.println("--- Conmtract: " + payment.getContractId()+" ---");
            System.out.println("--- Customer: " + payment.getCustomerId()+" ---");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + payment.getPaymentDate());
            System.out.println("--------------------!!!!!!!!!" + payment.getPaymentDate().toString());

            long limit = 0L;

            Set<Contract> CustomerPayment = contractDao.findByProjectAndCustomer(payment.getContractId(), payment.getCustomerId());
            for ( Contract contract : CustomerPayment ) {

                System.out.println("--- paymentSchedule: " + contract.getPaymentSchedule()+"---");

                if( contract.getPaymentSchedule().equals("Semanal") ){
                    limit = 7L;
                }else {
                    if ( contract.getPaymentSchedule().equals("Quincenal") ){
                        limit = 15L;
                    }else{
                        if ( contract.getPaymentSchedule().equals("Mensual") ){
                            limit = 30L;
                        }
                    }
                }

                System.out.println(limit);

                Boolean status = paymentDao.FindStatusOk(customer.getId(), contract.getId(), limit);

                if( status == true ){
                    result = "A tiempo";
                }else{
                    result = "Atrasado";
                }

            }
        }
        return result;
    }
}

        /*for (Payment payment : payments){

            Set<Contract> CustomerPayment = contractDao.findByProjectAndCustomer(payment.getContractId(), payment.getCustomerId());
            for( Contract contract : CustomerPayment ){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                //            payment.getPaymentDate();



                try {
                    System.out.println("--------------------!!!!!!!!!" + DateFormat.parse(payment.getPaymentDate().toString()));

                    LocalDate from = LocalDate.parse(DateFormat.parse(payment.getPaymentDate().toString()).toString());
                    LocalDate to = LocalDate.now();

                    long days = ChronoUnit.DAYS.between(from, to);

                    Long limit = 0L;

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



                    Boolean status = paymentDao.FindStatusOk(customer.getId(), contract.getId(), limit);

                    if (status == true){
                        result = "Al dia";
                    }else{
                        result = "Atrasado";
                    }*/
            /*}
        }
        return result;
    }
}*/
