
module org.example.usecase {
    exports org.example.usecase;
    exports org.example.exception;
    exports org.example.port;

    requires org.example.domain;
    requires org.apache.commons.lang3;
    requires lombok;
}