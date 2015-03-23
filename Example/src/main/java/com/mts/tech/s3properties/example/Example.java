package com.mts.tech.s3properties.example;

import com.amazonaws.auth.AWSCredentials;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.mts.mccann.lidl.youcook.events.Events;
import com.mts.mccann.lidl.youcook.utils.NumberUtils;
import com.mts.tech.s3properties.S3Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Example {

    static final Logger Log = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) throws InterruptedException, IOException {

        S3Properties properties = new S3Properties(new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "<ACCESS_KEY>";
            }

            @Override
            public String getAWSSecretKey() {
                return "<SECRET_KEY>";
            }
        }, "<REGION>");

        properties.load("<S3_BUCKET>", "<S3_KEY>");
    }
}
