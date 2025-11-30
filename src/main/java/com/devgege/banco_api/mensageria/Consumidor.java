package com.devgege.banco_api.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumidor {

    @RabbitListener(queues = "boas-vindas")
    public void receberMensagem(String mensagem) {
        System.out.println("📧 [CONSUMIDOR] Recebi: " + mensagem);
        System.out.println("   -> Simulando envio de e-mail de boas-vindas...");
    }
}
