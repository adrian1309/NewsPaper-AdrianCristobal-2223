module NewsPaperAdrianCristobal{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.yaml.snakeyaml;
    requires lombok;
    requires java.logging;
    requires jakarta.inject;
    requires java.sql;
    requires jakarta.xml.bind;
    requires jakarta.cdi;
    requires weld.api;
    requires jakarta.annotation;
    requires jakarta.el;
    requires com.zaxxer.hikari;
    requires io.vavr;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires spring.jdbc;
    requires spring.tx;
    requires modelmapper;
    requires jakarta.jakartaee.web.api;

    //PSP


    opens gui;
    opens fx.controller to javafx.fxml;
    opens fx.controller.article to javafx.fxml;
    opens fx.controller.reader;
    opens fx.controller.newspaper to javafx.fxml;
    opens model.xml to jakarta.xml.bind;
    opens fx.controller.xml;
    opens config.file;
    opens model;
    opens config.jdbc;
    opens common;
    opens fx.controller.jdbc;
    opens fx.controller.spring;

    //PSP




    exports utils;
    exports dao.jdbc.impl;
    exports service.jdbc;
    exports fx.controller.article;
    exports dao.jdbc.connectionsJDBC;
    exports gui;
    exports fx.controller;
    exports model;
    exports service;
    exports fx.controller.newspaper;
    exports dao.files.IF;
    exports fx.controller.reader;
    exports model.xml;
    exports fx.controller.xml;
    exports dao.files.impl;
    exports service.files;
    exports config.jdbc;
    exports fx.controller.common;
    exports fx.controller.jdbc;
    exports fx.controller.spring;
    exports dao.spring.impl;
    exports service.spring;
    exports dao.jdbc;

    //PSP



//Modules jakarta.jakartaee.web.api and jakarta.cdi export package jakarta.enterprise.inject.se to module spring.jdbc


}