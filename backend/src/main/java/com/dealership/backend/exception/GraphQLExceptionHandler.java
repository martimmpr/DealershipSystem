package com.dealership.backend.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GraphQLExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleNoSuchElementException(NoSuchElementException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
            .errorType(graphql.ErrorType.DataFetchingException)
            .message("Vehicle not found: " + ex.getMessage() + "!")
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleIllegalArgumentException(IllegalArgumentException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
            .errorType(graphql.ErrorType.ValidationError)
            .message("Invalid argument: " + ex.getMessage() + "!")
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleGenericException(Exception ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
            .errorType(graphql.ErrorType.DataFetchingException)
            .message("Internal server error: " + ex.getMessage() + "!")
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
    }
}