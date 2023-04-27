package dev.aminnorouzi.movieservice;

import dev.aminnorouzi.movieservice.model.Genre;
import dev.aminnorouzi.movieservice.repository.GenreRepository;
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
public class MovieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(GenreRepository genreRepository) {
        return args -> {
            genreRepository.save(new Genre(28, "Action"));
            genreRepository.save(new Genre(12, "Adventure"));
            genreRepository.save(new Genre(16, "Animation"));
            genreRepository.save(new Genre(35, "Comedy"));
            genreRepository.save(new Genre(80, "Crime"));
            genreRepository.save(new Genre(99, "Documentary"));
            genreRepository.save(new Genre(18, "Drama"));
            genreRepository.save(new Genre(10751, "Family"));
            genreRepository.save(new Genre(14, "Fantasy"));
            genreRepository.save(new Genre(36, "History"));
            genreRepository.save(new Genre(27, "Horror"));
            genreRepository.save(new Genre(10402, "Music"));
            genreRepository.save(new Genre(9648, "Mystery"));
            genreRepository.save(new Genre(10749, "Romance"));
            genreRepository.save(new Genre(878, "Science Fiction"));
            genreRepository.save(new Genre(10770, "TV Movie"));
            genreRepository.save(new Genre(53, "Thriller"));
            genreRepository.save(new Genre(10752, "War"));
            genreRepository.save(new Genre(37, "Western"));
            genreRepository.save(new Genre(10759, "Action & Adventure"));
            genreRepository.save(new Genre(10762, "Kids"));
            genreRepository.save(new Genre(10763, "News"));
            genreRepository.save(new Genre(10764, "Reality"));
            genreRepository.save(new Genre(10765, "Sci-Fi & Fantasy"));
            genreRepository.save(new Genre(10766, "Soap"));
            genreRepository.save(new Genre(10767, "Talk"));
            genreRepository.save(new Genre(10768, "War & Politics"));
        };
    }
}
