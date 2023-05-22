package com.s8.binance.service;

public interface IEmailService {

    public void sendMail(String to, String subject, String body);
}
