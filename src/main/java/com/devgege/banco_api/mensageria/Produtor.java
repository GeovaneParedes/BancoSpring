package com.devgege.banco_api.mensageria;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Produtor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Cria a fila automaticamente se não existir
    @Bean
    public Queue filaBoasVindas() {
        return new Queue("boas-vindas", true);
    }

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend("boas-vindas", mensagem);
        System.out.println("📤 [PRODUTOR] Mensagem enviada: " + mensagem);
    }
}
