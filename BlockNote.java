/*java -cp "sqlite-jdbc-3.46.1.0.jar:slf4j-api-1.7.36.jar:." BlockNote*/
// import java.lang.Thread.State;
import java.util.Scanner;
import java.sql.*;

public class BlockNote {
    public static void main(String[] args) {
        BlockNote blockNote = new BlockNote();
        blockNote.open();
        blockNote.insert();
        blockNote.select();
        blockNote.close();
    }
    
    Connection conn = null;

    void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Controlador no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }   

    void insert() {
        if (conn == null) {
            System.err.println("Error al insertar usuario: conexión a la base de datos cerrada");
            return;
        }

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduzca su nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.println("Introduzca su telefono: ");
            String phone = scanner.nextLine();
            scanner.close();

            String query = "INSERT INTO users (name, phone) " + 
                        "VALUES ('"+ username +"', '"+ phone +"')";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("Usuario insertado correctamente");
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    void select() {
        if (conn == null) {
            System.err.println("Error al seleccionar usuarios: conexión a la base de datos cerrada");
            return;
        }

        try {
            String query = 
                        "SELECT id, name, phone " + 
                        "FROM users "+
                        "ORDER BY id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                System.out.println(id + "\t|" + name + "\t|" + phone);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al seleccionar usuarios: " + e.getMessage());
        }
    }

    void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}