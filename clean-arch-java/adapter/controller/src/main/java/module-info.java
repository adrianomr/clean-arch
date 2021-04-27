module org.example.controller {
    exports org.example.controller;
    exports org.example.controller.model;

    requires org.example.usecase;
    requires org.example.domain;
}