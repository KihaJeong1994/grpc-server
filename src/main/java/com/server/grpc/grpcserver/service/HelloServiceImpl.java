package com.server.grpc.grpcserver.service;

import com.server.grpc.grpcserver.HelloRequest;
import com.server.grpc.grpcserver.HelloResponse;
import com.server.grpc.grpcserver.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase{
    
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver){
        String greeting = new StringBuilder()
                                .append("Hello, ")
                                .append(request.getFirstName())
                                .append(" ")
                                .append(request.getLastName())
                                .toString();
        HelloResponse response = HelloResponse.newBuilder()
                                .setGreeting(greeting)
                                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted(); // specify that we've finished dealing with the RPC, else the connection will be hung
        // does not return HelloResponse. Instead, it takes the second argument as StreamObserver<HelloResponse>,
        // which is a response observer, a call back for the server to call with its response.
        // => client gets an option to make a blocking call or a non-blocking call(? why)
    }
}
