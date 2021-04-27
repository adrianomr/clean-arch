module org.example.spring {
    requires org.example.config;
    requires org.example.usecase;
    requires org.example.controller;
    requires spring.web;
    requires spring.beans;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;

    exports org.example.spring;
    exports org.example.spring.config;
    opens org.example.spring.config to spring.core;
}