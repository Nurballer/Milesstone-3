package com.example.ticketondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TicketonDemoApplication implements CommandLineRunner {

	final
	JdbcTemplate jdbcTemplate;

	public TicketonDemoApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {

		SpringApplication.run(TicketonDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE IF EXISTS concerts");
		jdbcTemplate.execute("DROP TABLE IF EXISTS cinemas");
		jdbcTemplate.execute("DROP TABLE IF EXISTS events");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS events(" +
				"id SERIAL, price float, age_restriction integer, date date, event_type VARCHAR(255)," +
				"PRIMARY KEY (id))");
		jdbcTemplate.execute("create table IF NOT EXISTS concerts(event_id integer, address varchar(255), starting_time varchar(255), duration varchar(255), " +
				"CONSTRAINT fk_event FOREIGN KEY(event_id) REFERENCES events(id))");
		jdbcTemplate.execute("create table IF NOT EXISTS concerts(" +
				"event_id integer, address varchar(255), starting_time varchar(255), duration varchar(255)," +
				"CONSTRAINT fk_event FOREIGN KEY(event_id) REFERENCES events(id))");



	}
}
