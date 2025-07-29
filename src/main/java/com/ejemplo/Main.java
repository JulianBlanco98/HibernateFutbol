import com.ejemplo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            if (session.isConnected()) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
            } else {
                System.out.println("❌ No se pudo conectar a la base de datos.");
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error durante la conexión:");
            e.printStackTrace();
        }
    }
}
