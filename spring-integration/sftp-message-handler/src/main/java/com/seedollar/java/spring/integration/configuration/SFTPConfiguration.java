package com.seedollar.java.spring.integration.configuration;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/6/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration")
@PropertySource("classpath:sftp.properties")
public class SFTPConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public MessagingTemplate sftpMessagingTemplateInvoker() {
        return new MessagingTemplate(startSftpChannel());
    }

    @Bean
    public MessageChannel startSftpChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel endSftpChannel() {
        return new QueueChannel();
    }

    @Bean
    public SessionFactory<ChannelSftp.LsEntry> cachingSessionFactory() {
        DefaultSftpSessionFactory defaultSftpSessionFactory = new DefaultSftpSessionFactory(true);
        defaultSftpSessionFactory.setHost(environment.getProperty("remote.file.storage.sftp.host"));
        defaultSftpSessionFactory.setPort(Integer.parseInt(environment.getProperty("remote.file.storage.sftp.port")));
        defaultSftpSessionFactory.setUser(environment.getProperty("remote.file.storage.sftp.user"));
        defaultSftpSessionFactory.setPassword(environment.getProperty("remote.file.storage.sftp.password"));
        defaultSftpSessionFactory.setAllowUnknownKeys(true);
        CachingSessionFactory<ChannelSftp.LsEntry> cachingSessionFactory = new CachingSessionFactory<ChannelSftp.LsEntry>(defaultSftpSessionFactory, 10);
        cachingSessionFactory.setSessionWaitTimeout(Long.parseLong(environment.getProperty("remote.file.storage.sftp.session.wait.timeout")));
        return cachingSessionFactory;
    }
}
