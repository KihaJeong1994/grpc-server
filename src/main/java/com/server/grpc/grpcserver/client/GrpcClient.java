package com.server.grpc.grpcserver.client;

import com.server.grpc.grpcserver.HelloRequest;
import com.server.grpc.grpcserver.HelloResponse;
import com.server.grpc.grpcserver.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8080)
                                    .usePlaintext()
                                    .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                                                    .setFirstName("kiha")
                                                    .setLastName("Jeong")
                                                    .build());
        System.out.println(helloResponse.getGreeting());
        channel.shutdown();
    }
}
