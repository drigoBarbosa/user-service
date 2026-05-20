package com.drigobarbosa.userservice.producers;

import com.drigobarbosa.userservice.dtos.EmailDto;
import com.drigobarbosa.userservice.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText("Bem vindo! " +
                "\nMuito obrigado por se cadastrar na nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
