module org.example.config {
    exports org.example.config;

    requires org.example.usecase;
    requires org.example.domain;
    requires org.example.jug;
//    requires org.example.uuid;
    requires org.example.db.simple;
//    requires org.example.db.hazelcast;
    requires org.example.encoder;
}