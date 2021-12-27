package dbeshop;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.nio.file.*;

public class BaseJFrame extends javax.swing.JFrame {

    private static String driverClassName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/tester";
    private static String username = "tester";
    private static String passwd = "Apasswd";
    private static Connection dbConnection = null;
    private static Statement statement = null;
    private static int userID = -1;

    private void initDB() {
        try {
            //Load database driver
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            //TODO: properly handle exception
            System.out.println("driver load exception: " + ex);
        }
        try {
            //Establish connection
            dbConnection = DriverManager.getConnection(url, username, passwd);
            //Make database connection globally available
            statement = dbConnection.createStatement();
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println(ex);
        }
        try {
            //Create base tables
            statement.execute(createTablesQuery);
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("c_t exception: " + ex);
        }
        try {
            //Create base tables
            statement.executeQuery("SELECT create_tables();");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("select c_t exception: " + ex);
        }
        try {
            //Create audit tables
            statement.execute(createAuditTablesQuery);
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("c_a_t exception: " + ex);
        }
        try {
            //Create audit tables
            statement.executeQuery("SELECT create_audit_tables();");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("select c_a_t exception: " + ex);
        }
        try {
            //Create audit triggers
            statement.execute(createAuditTriggersQuery);
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("c_a_trig exception: " + ex);
        }
        try {
            //Create stored procedures
            statement.execute(createStoredProceduresQuery);
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("c_s_p exception: " + ex);
        }
        System.out.println("database set up successfully");
    }

    private String addUser(String name, String email, String password, String fname, String lname) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_user_get_userid('" + name + "', '" + email + "', '" + password + "', '" + fname + "', '" + lname + "') as UID;");
            while (rs.next()) {
                String uid = rs.getString("UID");
                System.out.println("a_u_g_u uid: " + uid);
                return uid;
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_u_g_u exception: " + ex);
        }
        return "";
    }

    private String addAddress(String country, String city, String zip, String address, String phone) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_address_minimal('" + country + "', '" + city + "', '" + zip + "', '" + address + "', '" + phone + "') as AID;");
            while (rs.next()) {
                String aid = rs.getString("AID");
                System.out.println("a_a_m aid: " + aid);
                return aid;
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_a_m exception: " + ex);
        }
        return "";
    }

    private void addAddressToUser(String uid, String aid) {
        try {
            ResultSet rs = statement.executeQuery("SELECT set_user_address('" + uid + "', '" + aid + "');");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("s_u_a exception: " + ex);
        }
    }

    private String userLogin(String nickname, String password) {
        try {
            ResultSet rs = statement.executeQuery("SELECT user_login('" + nickname + "', '" + password + "') AS UID;");
            while (rs.next()) {
                String uid = rs.getString("UID");
                //TODO: handle null returned when password is wrong
                System.out.println("u_l uid: " + uid);
                return uid;
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("u_l exception: " + ex);
        }
        return "";
    }

    private void addCategory(String name, String desc) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_category_get_categoryid('" + name + "', '" + desc + "') AS CID;");
            while (rs.next()) {
                String cid = rs.getString("CID");
                //TODO: handle null returned when password is wrong
                System.out.println("a_c_g_c cid: " + cid);
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_c exception: " + ex);
        }
    }

    private void addManufacturer(String name, String email, String address) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_manufacturer_with_address_get_manufacturerid('" + name + "', '" + email + "', '" + address + "') AS MID;");
            while (rs.next()) {
                String mid = rs.getString("MID");
                //TODO: handle null returned when password is wrong
                System.out.println("a_m_g_m mid: " + mid);
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_m_g_m exception: " + ex);
        }
    }

    private void addProduct(String title, String price, String desc, String version, String weight) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_product_minimal('" + title + "', " + price + ", '" + desc + "', " + version + ", " + weight + ");");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_p_m exception: " + ex);
        }
    }

    private void loadSQL() {
        try {
            String sql = (BaseJFrame.class.getResource("eshopSQL.sql")).getPath();
            Path fn = Path.of(sql);
            String str = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 1: " + ex);
        }
        try {
            String sql = (BaseJFrame.class.getResource("eshop-tables.sql")).getPath();
            Path fn = Path.of(sql);
            createTablesQuery = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 2: " + ex);
        }
        try {
            String sql = (BaseJFrame.class.getResource("eshop-audittables.sql")).getPath();
            Path fn = Path.of(sql);
            createAuditTablesQuery = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 3: " + ex);
        }
        try {
            String sql = (BaseJFrame.class.getResource("eshop-audittriggers.sql")).getPath();
            Path fn = Path.of(sql);
            createAuditTriggersQuery = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 4: " + ex);
        }
        try {
            String sql = (BaseJFrame.class.getResource("eshop-operations.sql")).getPath();
            Path fn = Path.of(sql);
            createStoredProceduresQuery = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 5: " + ex);
        }
    }

    public BaseJFrame() {
        loadSQL();
        initDB();
        String uid = addUser("anhel", "daddyinherently@cock.li", "joemomma", "An", "Hel");
        if (uid.isEmpty()) {
            System.out.println("problem adding user");
        }
        String aid = addAddress("greece", "salonika", "52525", "melenikou", "6900123456");
        if (aid.isEmpty()) {
            System.out.println("problem adding address");
        }
        String aid2 = addAddress("greece", "athina", "82345", "kapouekei", "210123456");
        if (aid.isEmpty()) {
            System.out.println("problem adding address 2");
        }
        addAddressToUser(uid, aid);
        uid = userLogin("usr", "1234");
        try {
            userID = Integer.parseInt(uid);
        } catch (NumberFormatException ex) {
            System.out.println("uid str to int exception: " + ex);
        }
        addCategory("networking", "routers, switches and more");
        addManufacturer("maker1", "pr@maker1.gr", "2");
        addProduct("prodtitle", "50.0", "some prod", "3.2", "0.93");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EshopDB");
        setMinimumSize(new java.awt.Dimension(400, 300));
        setName("baseFrame"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Image img = new ImageIcon(BaseJFrame.class.getResource("shopping_cart1600.png")).getImage();
                JFrame f = new BaseJFrame();
                f.setIconImage(img);
                f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // Begin query variables
    // String variables for queries to be used when initializing a new database
    private static String createTablesQuery = "";
    private static String createAuditTablesQuery = "";
    private static String createAuditTriggersQuery = "";
    private static String createStoredProceduresQuery = "";
}
