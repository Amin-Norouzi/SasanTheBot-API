package dev.aminnorouzi.providerservice;

import dev.aminnorouzi.providerservice.model.Provider;
import dev.aminnorouzi.providerservice.repository.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProviderRepository repository) {
        return args -> {
            Provider avamovie = Provider.builder()
                    .title("avamovie")
                    .url("https://avamovie1.info/")
                    .query("?s=&advsearch=on&search_imdbid=")
                    .phrase("آوا مووی - مرجع دانلود فیلم و سریال با زیرنویس فارسی چسبیده")
                    .isBanned(false)
                    .isAvailable(true)
                    .build();

            Provider filmban = Provider.builder()
                    .title("filmban")
                    .url("https://filmban.click/")
                    .query("?s=")
                    .phrase("فیلم بان | فیلم بان | مرجع دانلود فیلم و سریال بدون سانسور")
                    .isBanned(false)
                    .isAvailable(true)
                    .build();

            Provider film2media = Provider.builder()
                    .title("film2media")
                    .url("https://www.f2m.kim/")
                    .query("?controller=search-movie&s=")
                    .phrase("فیلم 2 مدیا | دانلود فیلم و سریال با زیرنویس فارسی چسبیده")
                    .isBanned(false)
                    .isAvailable(true)
                    .build();

            Provider myhastidl = Provider.builder()
                    .title("myhastidl")
                    .url("http://myhastidl1.cam/")
                    .query("?s=")
                    .phrase("هستی دی ال: دانلود رایگان فیلم و سریال با لینک مستقیم")
                    .isBanned(false)
                    .isAvailable(true)
                    .build();

            repository.save(avamovie);
            repository.save(filmban);
            repository.save(film2media);
            repository.save(myhastidl);
        };
    }
}

