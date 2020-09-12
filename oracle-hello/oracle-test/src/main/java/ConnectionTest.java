import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * コネクションプールテスト
 */
public class ConnectionTest {
    public static void main(String[] args) {
        System.out.println("start");

        for (int i = 0; i < 10; i++) {
            var child = new ChildConnection();
            child.start();
        }

        System.out.println("end");
    }
}
class ChildConnection extends Thread {
    public void run() {
        System.out.println("スレッドスタート");

        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:ORCLCDB");
        conf.setUsername("system");
        conf.setPassword("Oracle12");

        // データソースを生成、コネクションを取得
        try (HikariDataSource ds = new HikariDataSource(conf)) {
            try (Connection con = ds.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                    "select 1 as id from dual"
                );
                ResultSet rs = ps.executeQuery();
                rs.next();
                System.out.println(rs.getString("id"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("スレッドエンド");
    }
}
