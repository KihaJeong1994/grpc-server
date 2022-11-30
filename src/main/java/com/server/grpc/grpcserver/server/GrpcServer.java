package com.server.grpc.grpcserver.server;

import java.io.IOException;

import com.server.grpc.grpcserver.service.HelloServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException{
        Server server = ServerBuilder
                        .forPort(8080)
                        .addService(new HelloServiceImpl()).build();
        server.start();
        server.awaitTermination();
    }
}
