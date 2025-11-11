package com.dealership.backend.config;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.UUID;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
            .scalar(GraphQLScalarType.newScalar()
                    .name("UUID")
                    .description("UUID scalar")
                    .coercing(new Coercing<UUID, String>() {
                        @Override
                        public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                            if (dataFetcherResult instanceof UUID) {
                                return ((UUID) dataFetcherResult).toString();
                            }
                            throw new CoercingSerializeException("Expected UUID but was " + dataFetcherResult + "!");
                        }

                        @Override
                        public UUID parseValue(Object input) throws CoercingParseValueException {
                            if (input instanceof String) {
                                try {
                                    return UUID.fromString((String) input);
                                } catch (IllegalArgumentException e) {
                                    throw new CoercingParseValueException("Invalid UUID format: " + input + "!");
                                }
                            }
                            throw new CoercingParseValueException("Expected String but was " + input + "!");
                        }

                        @Override
                        public UUID parseLiteral(Object input) throws CoercingParseLiteralException {
                            if (input instanceof String) {
                                try {
                                    return UUID.fromString((String) input);
                                } catch (IllegalArgumentException e) {
                                    throw new CoercingParseLiteralException("Invalid UUID format: " + input + "!");
                                }
                            }
                            throw new CoercingParseLiteralException("Expected String but was " + input + "!");
                        }
                    })
                    .build());
    }
}