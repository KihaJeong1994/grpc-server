package com.server.grpc.grpcserver.service;

import org.lognet.springboot.grpc.GRpcService;

import com.server.grpc.grpcserver.HelloRequest;
import com.server.grpc.grpcserver.HelloResponse;
import com.server.grpc.grpcserver.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;

@GRpcService
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

    @Override
    public void helloTwice(HelloRequest request, StreamObserver<HelloResponse> responseObserver){
        String greeting1 = new StringBuilder()
                                .append("Hello1, ")
                                .append(request.getFirstName())
                                .append(" ")
                                .append(request.getLastName())
                                .toString();
        HelloResponse response1 = HelloResponse.newBuilder()
                                .setGreeting(greeting1)
                                .build();
        String greeting2 = new StringBuilder()
                                .append("Hello2, ")
                                .append(request.getFirstName())
                                .append(" ")
                                .append(request.getLastName())
                                .toString();
        HelloResponse response2 = HelloResponse.newBuilder()
                                .setGreeting(greeting2)
                                .build();
        responseObserver.onNext(response1);
        responseObserver.onNext(response2);
        responseObserver.onCompleted();                   
    }
}
