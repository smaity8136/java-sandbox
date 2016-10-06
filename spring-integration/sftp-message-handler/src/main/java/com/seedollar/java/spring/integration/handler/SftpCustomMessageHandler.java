package com.seedollar.java.spring.integration.handler;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by seedollar on 10/6/16.
 */
@Component
public class SftpCustomMessageHandler extends SftpMessageHandler {

    private final SessionFactory<ChannelSftp.LsEntry> sessionFactory;
    private final Environment environment;

    public SftpCustomMessageHandler(SessionFactory<ChannelSftp.LsEntry> sessionFactory, Environment environment) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        this.environment = environment;

        setAutoCreateDirectory(true);
        setRemoteDirectoryExpression(new LiteralExpression(environment.getProperty("remote.file.storage.sftp.remote.file.directory")));
        // We provide a custom fileNameGenerator which uses the messageID as the file name
        setFileNameGenerator(new FileNameGenerator() {
            @Override
            public String generateFileName(Message<?> message) {
                // Retrieve the message ID and use that as the file name
                String fileName = (String) message.getHeaders().get("fileName");
                // retrieve the content type and use that as the file extension.
                String fileType = (String) message.getHeaders().get("fileType");
                StringBuilder fileNameBuilder = new StringBuilder(fileName);
                fileNameBuilder.append(".");
                fileNameBuilder.append(fileType.toLowerCase());
                return fileNameBuilder.toString();
            }
        });
    }
}
