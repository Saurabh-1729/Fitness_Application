package com.fitness.aiservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

// This helps in
@Configuration
@EnableMongoAuditing // This annotation enables auditing in MongoDB
public class MongoConfig {
}