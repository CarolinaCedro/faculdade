package io.github.carolinacedro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;


@SpringBootApplication
public class FaculdadeRedisExerciseApplication {

    @Bean
    public Jedis RedisInstance() {
        System.out.println("Instancia Redis Iniciada");
        return new Jedis("localhost", 6379);
    }


    public static void main(String[] args) {
        SpringApplication.run(FaculdadeRedisExerciseApplication.class, args);
    }

}
