package dev.aminnorouzi.userservice;

import dev.aminnorouzi.userservice.model.Status;
import dev.aminnorouzi.userservice.model.User;
import dev.aminnorouzi.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User user = User.builder()
                    .telegramId(211676205L)
                    .telegramChatId(211676205L) // useless
                    .fullName("amin norouzi")
                    .email("amin@gmail.com")
                    .password("1234")
                    .status(Status.ACTIVE)
                    .build();

            repository.save(user);
        };
    }
}
