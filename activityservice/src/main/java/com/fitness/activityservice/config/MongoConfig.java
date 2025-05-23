package com.fitness.activityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing // This annotation enables auditing in MongoDB
public class MongoConfig {
}

/*
@EnableMongoAuditing Annotation:

This is the most important part of this class.
It enables MongoDB auditing features in Spring Data MongoDB.
Auditing allows automatic tracking of:
Who created or modified an entity
When an entity was created or last modified
Empty Class Body:

The class has no methods or fields because the annotations alone provide the necessary configuration.
This is a common pattern for simple configuration classes in Spring.
 */