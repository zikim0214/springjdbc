package hello.jdbc.connection;

public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}

// 상수로 모아둔거라서 객체 생성 하면 안된다. 그래서 abstract로 객체 생성 막아줌

