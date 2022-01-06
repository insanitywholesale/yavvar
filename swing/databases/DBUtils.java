package dbeshop;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtils {

    private static String driverClassName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/tester";
    private static String username = "tester";
    private static String passwd = "Apasswd";
    private static Connection dbConnection = null;
    private static Statement statement = null;
    private static int userID = -1;
    private static String createTablesQuery = "";
    private static String createAuditTablesQuery = "";
    private static String createAuditTriggersQuery = "";
    private static String createStoredProceduresQuery = "";
    private static String testDataQuery = "";

    public static void initDB() {
        loadSQL();
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
        try {
            //Load test data
            statement.execute(testDataQuery);
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("t_d exception: " + ex);
        }
        System.out.println("database set up successfully");
    }

    public static void loadSQL() {
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
        try {
            String sql = (BaseJFrame.class.getResource("test-stuff.sql")).getPath();
            Path fn = Path.of(sql);
            testDataQuery = Files.readString(fn);
        } catch (Exception ex) {
            System.out.println("file thing fail 6: " + ex);
        }
    }

    public static String addUser(String name, String email, String password, String fname, String lname) {
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

    public static String addAddress(String country, String city, String zip, String address, String phone) {
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

    public static void addAddressToUser(String uid, String aid) {
        try {
            ResultSet rs = statement.executeQuery("SELECT set_user_address('" + uid + "', '" + aid + "');");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("s_u_a exception: " + ex);
        }
    }

    public static String userLogin(String nickname, String password) {
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

    public static void addCategory(String name, String desc) {
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

    public static void addManufacturer(String name, String email, String address) {
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

    public static void addProduct(String title, String price, String desc, String weight) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_product_minimal('" + title + "', " + price + ", '" + desc + "', " + weight + ");");
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("a_p_m exception: " + ex);
        }
    }

    public static ArrayList<String> getAllProductNames() {
        ArrayList<String> productNames = new ArrayList<String>();
        try {
            ResultSet rs = statement.executeQuery("SELECT get_all_product_titles() AS PT;");
            while (rs.next()) {
                String prodTitle = rs.getString("PT");
                System.out.println("prod_title: " + prodTitle);
                productNames.add(prodTitle);
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("g_a_p_n exception: " + ex);
        }
        return productNames;
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            //TODO: it works but this is sub-optimal at best
            ResultSet rst = dbConnection.createStatement().executeQuery("SELECT get_all_product_titles() AS PT;");
            ResultSet rsp = dbConnection.createStatement().executeQuery("SELECT get_all_product_prices() AS PP;");
            ResultSet rsd = dbConnection.createStatement().executeQuery("SELECT get_all_product_descs() AS PD;");
            ResultSet rsw = dbConnection.createStatement().executeQuery("SELECT get_all_product_weights() AS PW;");
            while (rst.next() && rsp.next() && rsd.next() && rsw.next()) {
                String pt = rst.getString("PT");
                String pp = rsp.getString("PP");
                String pd = rsd.getString("PD");
                String pw = rsw.getString("PW");
                Product p = new Product(pt, pp, pd, pw);
                products.add(p);
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("g_a_p exception: " + ex);
        }
        return products;
    }

    public static String createOrder(String uid) {
        try {
            ResultSet rs = statement.executeQuery("SELECT create_order_get_orderid(" + uid + ") AS OID;");
            while (rs.next()) {
                String oid = rs.getString("OID");
                System.out.println("c_o oid: " + oid);
                return oid;
            }
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("c_o exception: " + ex);
        }
        return "";
    }

    public static void addProductToOrder(String oid, String pid, String qty) {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_item_to_order(" + oid + ", " + pid + ", " + qty + ");");
        } catch (SQLException ex) {
            System.out.println("a_p_t_o exception: " + ex);
        }
    }

    public static void finalizeOrder(String oid) {
        try {
            ResultSet rs = statement.executeQuery("SELECT finalize_order(" + oid + ");");
        } catch (SQLException ex) {
            System.out.println("f_o exception: " + ex);
        }
    }
}

//EXAMPLES
//
/*
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
 */
