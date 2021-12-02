package swingdemo;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

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
            System.out.println("jdbc_drv exception: " + ex);
        }
        try {
            //Establish connection
            dbConnection = DriverManager.getConnection(url, username, passwd);
            //Make database connection globally available
            statement = dbConnection.createStatement();
        } catch (SQLException ex) {
            //TODO: properly handle exception
            System.out.println("db_conn exception: " + ex);
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

    private String addUser() {
        try {
            ResultSet rs = statement.executeQuery("SELECT add_user_get_userid('usr', 'me@example.com', '1234', 'mef', 'mel') as UID;");
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

    public BaseJFrame() {
        initDB();
        String uid = addUser();
        if (uid.isEmpty()) {
            System.out.println("problem adding user");
        }
        String aid = addAddress("greece", "thessaloniki", "52525", "melenikou", "6900123456");
        if (aid.isEmpty()) {
            System.out.println("problem adding address");
        }
        String aid2 = addAddress("greece", "athina", "12345", "kapouekei", "210123456");
        if (aid.isEmpty()) {
            System.out.println("problem adding address 2");
        }
        addAddressToUser(uid, aid);
        uid = userLogin("usr", "1234");
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
    private static String createTablesQuery = "--create base tables\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION create_tables() RETURNS void AS $$\n"
            + "\n"
            + "    CREATE TABLE if not exists Addresses (\n"
            + "        AddressID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        AddressCountry VARCHAR NOT NULL,\n"
            + "        AddressCity VARCHAR NOT NULL,\n"
            + "        AddressZipcode VARCHAR NOT NULL,\n"
            + "        Address1 VARCHAR NOT NULL,\n"
            + "        Address2 VARCHAR,\n"
            + "        AddressPhone VARCHAR NOT NULL,\n"
            + "        AddressPhone2 VARCHAR\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists Users (\n"
            + "        UserID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        UserNickName VARCHAR UNIQUE NOT NULL,\n"
            + "        UserEmail VARCHAR NOT NULL,\n"
            + "        UserPassword VARCHAR NOT NULL,\n"
            + "        UserFirstName VARCHAR NOT NULL,\n"
            + "        UserLastName VARCHAR NOT NULL,\n"
            + "        UserAddressID INTEGER REFERENCES Addresses(AddressID),\n"
            + "        UserIsAdmin BOOLEAN NOT NULL DEFAULT FALSE\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists Categories (\n"
            + "        CategoryID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        CategoryName VARCHAR NOT NULL,\n"
            + "        CategoryDescription VARCHAR NOT NULL DEFAULT ''\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists Manufacturers (\n"
            + "        ManufacturerID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        ManufacturerName VARCHAR NOT NULL,\n"
            + "        ManufacturerEmail VARCHAR NOT NULL,\n"
            + "        ManufacturerAddressID INTEGER REFERENCES Addresses(AddressID)\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists Products (\n"
            + "        --ProductID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,\n"
            + "        ProductID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        ProductTitle VARCHAR NOT NULL,\n"
            + "        ProductPrice FLOAT NOT NULL,\n"
            + "        ProductCategoryID INTEGER REFERENCES Categories(CategoryID),\n"
            + "        ProductManufacturerID INTEGER REFERENCES Manufacturers(ManufacturerID),\n"
            + "        ProductDescription VARCHAR NOT NULL DEFAULT '',\n"
            + "        ProductVersion FLOAT NOT NULL DEFAULT 1.0,\n"
            + "        ProductWeight FLOAT,\n"
            + "        ProductWeightClass SMALLINT,\n"
            + "        ProductOnSale BOOLEAN NOT NULL DEFAULT FALSE\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists Orders (\n"
            + "        OrderID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        OrderUserID INTEGER REFERENCES Users(UserID) NOT NULL,\n"
            + "        OrderCost FLOAT NOT NULL DEFAULT 0.0,\n"
            + "        OrderShippingCost FLOAT NOT NULL DEFAULT 5.0,\n"
            + "        OrderTax FLOAT NOT NULL DEFAULT 24.0,\n"
            + "        OrderTotalCost FLOAT NOT NULL DEFAULT 0.0,\n"
            + "        OrderBillingAddressID INTEGER REFERENCES Addresses(AddressID),\n"
            + "        OrderShippingAddressID INTEGER REFERENCES Addresses(AddressID),\n"
            + "        OrderIsFinal BOOLEAN NOT NULL DEFAULT FALSE,\n"
            + "        OrderHasShipped BOOLEAN NOT NULL DEFAULT FALSE,\n"
            + "        OrderHasArrived BOOLEAN NOT NULL DEFAULT FALSE,\n"
            + "        OrderTrackingNumber VARCHAR\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists OrderedProducts (\n"
            + "        OrderedProductID SERIAL PRIMARY KEY NOT NULL,\n"
            + "        OrderID INTEGER NOT NULL,\n"
            + "        ProductID INTEGER NOT NULL,\n"
            + "        ProductQuantity INTEGER NOT NULL DEFAULT 1,\n"
            + "        ProductTotalCost FLOAT NOT NULL DEFAULT 0.0,\n"
            + "        Constraint Ordered_OrderID FOREIGN KEY(OrderID) REFERENCES Orders on delete cascade on update cascade,\n"
            + "        Constraint Ordered_ProductID FOREIGN KEY(ProductID) REFERENCES Products on delete cascade on update cascade\n"
            + "    );\n"
            + "\n"
            + "$$ LANGUAGE SQL;";

    private static String createAuditTablesQuery = "--create audit/log tables and triggers\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION create_audit_tables() RETURNS void AS $$\n"
            + "    CREATE TABLE if not exists UserAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        UserID INTEGER NOT NULL,\n"
            + "        UserNickName VARCHAR NOT NULL,\n"
            + "        UserEmail VARCHAR NOT NULL,\n"
            + "        UserPassword VARCHAR NOT NULL,\n"
            + "        UserFirstName VARCHAR NOT NULL,\n"
            + "        UserLastName VARCHAR NOT NULL,\n"
            + "        UserAddressID INTEGER,\n"
            + "        UserIsAdmin BOOLEAN NOT NULL\n"
            + "    );\n"
            + "    CREATE TABLE if not exists CategoryAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        CategoryID INTEGER NOT NULL,\n"
            + "        CategoryName VARCHAR NOT NULL,\n"
            + "        CategoryDescription VARCHAR NOT NULL\n"
            + "    );\n"
            + "    CREATE TABLE if not exists ManufacturerAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        ManufacturerID INTEGER NOT NULL,\n"
            + "        ManufacturerName VARCHAR NOT NULL,\n"
            + "        ManufacturerEmail VARCHAR NOT NULL,\n"
            + "        ManufacturerAddressID INTEGER\n"
            + "    );\n"
            + "    CREATE TABLE if not exists ProductAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        ProductID INTEGER NOT NULL,\n"
            + "        ProductTitle VARCHAR NOT NULL,\n"
            + "        ProductPrice FLOAT NOT NULL,\n"
            + "        ProductCategoryID INTEGER,\n"
            + "        ProductManufacturerID INTEGER,\n"
            + "        ProductDescription VARCHAR NOT NULL,\n"
            + "        ProductVersion FLOAT NOT NULL,\n"
            + "        ProductWeight FLOAT,\n"
            + "        ProductWeightClass SMALLINT,\n"
            + "        ProductOnSale BOOLEAN NOT NULL\n"
            + "    );\n"
            + "    CREATE TABLE if not exists OrderAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        OrderID INTEGER NOT NULL,\n"
            + "        OrderUserID INTEGER NOT NULL,\n"
            + "        OrderCost FLOAT NOT NULL,\n"
            + "        OrderShippingCost FLOAT NOT NULL,\n"
            + "        OrderTax FLOAT NOT NULL,\n"
            + "        OrderTotalCost FLOAT NOT NULL,\n"
            + "        OrderBillingAddressID INTEGER,\n"
            + "        OrderShippingAddressID INTEGER,\n"
            + "        OrderIsFinal BOOLEAN NOT NULL,\n"
            + "        OrderHasShipped BOOLEAN NOT NULL,\n"
            + "        OrderHasArrived BOOLEAN NOT NULL,\n"
            + "        OrderTrackingNumber VARCHAR\n"
            + "    );\n"
            + "    CREATE TABLE if not exists AddressAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        AddressID INTEGER NOT NULL,\n"
            + "        AddressCountry VARCHAR NOT NULL,\n"
            + "        AddressCity VARCHAR NOT NULL,\n"
            + "        AddressZipcode VARCHAR NOT NULL,\n"
            + "        Address1 VARCHAR NOT NULL,\n"
            + "        Address2 VARCHAR,\n"
            + "        AddressPhone VARCHAR NOT NULL,\n"
            + "        AddressPhone2 VARCHAR\n"
            + "    );\n"
            + "\n"
            + "    CREATE TABLE if not exists OrderedProductsAudit (\n"
            + "        OperationType VARCHAR(1) NOT NULL,\n"
            + "        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,\n"
            + "        OperatorID VARCHAR NOT NULL,\n"
            + "        OrderedProductID INTEGER NOT NULL,\n"
            + "        OrderID INTEGER NOT NULL,\n"
            + "        ProductID INTEGER NOT NULL,\n"
            + "        ProductQuantity INTEGER NOT NULL,\n"
            + "        ProductTotalCost FLOAT NOT NULL\n"
            + "    );\n"
            + "$$ LANGUAGE SQL;";

    private static String createAuditTriggersQuery = "-- create triggers for audit tables\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_order_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO OrderAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO OrderAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO OrderAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER order_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Orders\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_order_audit();\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_user_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO UserAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO UserAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO UserAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER user_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Users\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_user_audit();\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_address_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO AddressAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO AddressAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO AddressAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER address_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Addresses\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_address_audit();\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_category_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO CategoryAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO CategoryAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO CategoryAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER category_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Categories\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_category_audit();\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_manufacturer_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO ManufacturerAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO ManufacturerAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO ManufacturerAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER manufacturer_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Manufacturers\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_manufacturer_audit();\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION process_product_audit() RETURNS TRIGGER AS $$\n"
            + "    BEGIN\n"
            + "        IF (TG_OP = 'DELETE') THEN\n"
            + "            INSERT INTO ProductAudit SELECT 'D', now(), user, OLD.*;\n"
            + "            RETURN OLD;\n"
            + "        ELSEIF (TG_OP = 'UPDATE') THEN\n"
            + "            INSERT INTO ProductAudit SELECT 'U', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        ELSEIF (TG_OP = 'INSERT') THEN\n"
            + "            INSERT INTO ProductAudit SELECT 'I', now(), user, NEW.*;\n"
            + "            RETURN NEW;\n"
            + "        END IF;\n"
            + "        RETURN NULL;\n"
            + "    END;\n"
            + "$$ LANGUAGE plpgsql;\n"
            + "\n"
            + "CREATE TRIGGER product_audit\n"
            + "AFTER INSERT OR UPDATE OR DELETE ON Products\n"
            + "FOR EACH ROW EXECUTE PROCEDURE process_product_audit();";

    private static String createStoredProceduresQuery = "-- functions for performing eshop operations\n"
            + "\n"
            + "-- address functions\n"
            + "\n"
            + "--                                    (country  city     zipcode  addr1    addr2    phone1   phone2 )\n"
            + "CREATE OR REPLACE FUNCTION add_address(varchar, varchar, varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Addresses (\n"
            + "        AddressCountry,\n"
            + "        AddressCity,\n"
            + "        AddressZipcode,\n"
            + "        Address1,\n"
            + "        Address2,\n"
            + "        AddressPhone,\n"
            + "        AddressPhone2\n"
            + "    ) VALUES ($1, $2, $3, $4, $5, $6, $7) RETURNING AddressID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                               (country  city     zipcode  addr1    addr2    phone1 )\n"
            + "CREATE OR REPLACE FUNCTION add_address_with_addr2(varchar, varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Addresses (\n"
            + "        AddressCountry,\n"
            + "        AddressCity,\n"
            + "        AddressZipcode,\n"
            + "        Address1,\n"
            + "        Address2,\n"
            + "        AddressPhone\n"
            + "    ) VALUES ($1, $2, $3, $4, $5, $6) RETURNING AddressID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                (country  city     zipcode  addr1    phone1   phone2 )\n"
            + "CREATE OR REPLACE FUNCTION add_address_with_phone2(varchar, varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Addresses (\n"
            + "        AddressCountry,\n"
            + "        AddressCity,\n"
            + "        AddressZipcode,\n"
            + "        Address1,\n"
            + "        AddressPhone,\n"
            + "        AddressPhone2\n"
            + "    ) VALUES ($1, $2, $3, $4, $5, $6) RETURNING AddressID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                             (country  city     zipcode  addr1    phone1)\n"
            + "CREATE OR REPLACE FUNCTION add_address_minimal(varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Addresses (\n"
            + "        AddressCountry,\n"
            + "        AddressCity,\n"
            + "        AddressZipcode,\n"
            + "        Address1,\n"
            + "        AddressPhone\n"
            + "    ) VALUES ($1, $2, $3, $4, $5) RETURNING AddressID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "-- user functions\n"
            + "\n"
            + "--                                            (nick     email    passwd   fname    lname  )\n"
            + "CREATE OR REPLACE FUNCTION add_user_get_userid(varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Users (\n"
            + "        UserNickName,\n"
            + "        UserEmail,\n"
            + "        UserPassword,\n"
            + "        UserFirstName,\n"
            + "        UserLastName\n"
            + "    ) VALUES ($1, $2, $3, $4, $5) RETURNING UserID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                 (nick     email    passwd   fname    lname  )\n"
            + "CREATE OR REPLACE FUNCTION add_user(varchar, varchar, varchar, varchar, varchar) RETURNS void AS $$\n"
            + "    INSERT INTO Users (\n"
            + "        UserNickName,\n"
            + "        UserEmail,\n"
            + "        UserPassword,\n"
            + "        UserFirstName,\n"
            + "        UserLastName\n"
            + "    ) VALUES ($1, $2, $3, $4, $5);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                       (nick     email    passwd   fname    lname  )\n"
            + "CREATE OR REPLACE FUNCTION add_admin_user(varchar, varchar, varchar, varchar, varchar) RETURNS void AS $$\n"
            + "    INSERT INTO Users (\n"
            + "        UserNickName,\n"
            + "        UserEmail,\n"
            + "        UserPassword,\n"
            + "        UserFirstName,\n"
            + "        UserLastName,\n"
            + "        UserIsAdmin\n"
            + "    ) VALUES ($1, $2, $3, $4, $5, TRUE);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                   (nick     passwd )\n"
            + "CREATE OR REPLACE FUNCTION user_login(varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    SELECT UserID FROM Users WHERE UserNickName = $1 AND UserPassword = $2;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                   (userid )\n"
            + "CREATE OR REPLACE FUNCTION user_login(integer) RETURNS BOOLEAN AS $$\n"
            + "    SELECT UserIsAdmin FROM Users WHERE UserID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                         (userid   addrid )\n"
            + "CREATE OR REPLACE FUNCTION set_user_address(integer, integer) RETURNS void AS $$\n"
            + "    UPDATE Users\n"
            + "    SET UserAddressID = $2\n"
            + "    WHERE UserID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                        (userid )\n"
            + "CREATE OR REPLACE FUNCTION make_user_admin(integer) RETURNS void AS $$\n"
            + "    UPDATE Users\n"
            + "    SET UserIsAdmin = TRUE\n"
            + "    WHERE UserID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "-- order functions\n"
            + "\n"
            + "--                                                 (userid )\n"
            + "CREATE OR REPLACE FUNCTION create_order_get_orderid(integer) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Orders (\n"
            + "        OrderUserID\n"
            + "    ) VALUES ($1) RETURNING OrderID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                     (userid )\n"
            + "CREATE OR REPLACE FUNCTION create_order(integer) RETURNS void AS $$\n"
            + "    INSERT INTO Orders (\n"
            + "        OrderUserID\n"
            + "    ) VALUES ($1);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                          (orderid  baddrid  saddrid)\n"
            + "CREATE OR REPLACE FUNCTION set_order_address(integer, integer, integer) RETURNS void AS $$\n"
            + "    UPDATE Orders\n"
            + "    SET OrderBillingAddressID = $2, OrderShippingAddressID = $3\n"
            + "    WHERE OrderID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                          (orderid  prodid   prodqty)\n"
            + "CREATE OR REPLACE FUNCTION add_item_to_order(integer, integer, integer) RETURNS void AS $$\n"
            + "    INSERT INTO OrderedProducts (\n"
            + "        OrderID,\n"
            + "        ProductID,\n"
            + "        ProductQuantity\n"
            + "    ) VALUES ($1, $2, $3);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                       (orderid)\n"
            + "CREATE OR REPLACE FUNCTION finalize_order(integer) RETURNS void AS $$\n"
            + "    UPDATE Orders\n"
            + "    SET OrderIsFinal = TRUE\n"
            + "    WHERE OrderID = $1 AND OrderBillingAddressID IS NOT NULL AND OrderShippingAddressID IS NOT NULL;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION get_shipped_orders() RETURNS SETOF Orders AS $$\n"
            + "    SELECT * FROM Orders o WHERE o.OrderHasShipped = TRUE AND o.OrderHasArrived = FALSE;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION get_unshipped_orders() RETURNS SETOF Orders AS $$\n"
            + "    SELECT * FROM Orders o WHERE o.OrderHasShipped = FALSE AND o.OrderHasArrived = FALSE;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "-- product functions\n"
            + "\n"
            + "--                                    (title    price  catID    manufID  desc     vers   wght )\n"
            + "CREATE OR REPLACE FUNCTION add_product(varchar, float, integer, integer, varchar, float, float) RETURNS void AS $$\n"
            + "    INSERT INTO Products (\n"
            + "        ProductTitle,\n"
            + "        ProductPrice,\n"
            + "        ProductCategoryID,\n"
            + "        ProductManufacturerID,\n"
            + "        ProductDescription,\n"
            + "        ProductVersion,\n"
            + "        ProductWeight\n"
            + "    ) VALUES($1, $2, $3, $4, $5, $6, $7);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                      (title    price  manufID  desc     vers   wght )\n"
            + "CREATE OR REPLACE FUNCTION add_product_with_manufacturer(varchar, float, integer, varchar, float, float) RETURNS void AS $$\n"
            + "    INSERT INTO Products (\n"
            + "        ProductTitle,\n"
            + "        ProductPrice,\n"
            + "        ProductManufacturerID,\n"
            + "        ProductDescription,\n"
            + "        ProductVersion,\n"
            + "        ProductWeight\n"
            + "    ) VALUES($1, $2, $3, $4, $5, $6);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                  (title    price  catID    desc     vers   wght )\n"
            + "CREATE OR REPLACE FUNCTION add_product_with_category(varchar, float, integer, varchar, float, float) RETURNS void AS $$\n"
            + "    INSERT INTO Products (\n"
            + "        ProductTitle,\n"
            + "        ProductPrice,\n"
            + "        ProductCategoryID,\n"
            + "        ProductDescription,\n"
            + "        ProductVersion,\n"
            + "        ProductWeight\n"
            + "    ) VALUES($1, $2, $3, $4, $5, $6);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                            (title    price  desc     vers   wght )\n"
            + "CREATE OR REPLACE FUNCTION add_product_minimal(varchar, float, varchar, float, float) RETURNS void AS $$\n"
            + "    INSERT INTO Products (\n"
            + "        ProductTitle,\n"
            + "        ProductPrice,\n"
            + "        ProductDescription,\n"
            + "        ProductVersion,\n"
            + "        ProductWeight\n"
            + "    ) VALUES($1, $2, $3, $4, $5);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                             (prodid  catid   )\n"
            + "CREATE OR REPLACE FUNCTION set_product_category(integer, integer) RETURNS void AS $$\n"
            + "    UPDATE Products\n"
            + "    SET ProductCategoryID = $2\n"
            + "    WHERE ProductID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                 (prodid   manid  )\n"
            + "CREATE OR REPLACE FUNCTION set_product_manufacturer(integer, integer) RETURNS void AS $$\n"
            + "    UPDATE Products\n"
            + "    SET ProductManufacturerID = $2\n"
            + "    WHERE ProductID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                    (prodid )\n"
            + "CREATE OR REPLACE FUNCTION get_product(integer) RETURNS Products AS $$\n"
            + "    SELECT * FROM Products p WHERE p.ProductID = $1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION get_all_products() RETURNS SETOF Products AS $$\n"
            + "    SELECT * FROM Products;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION assign_weight_class() RETURNS void AS $$\n"
            + "    UPDATE Products\n"
            + "    SET ProductWeightClass = 5\n"
            + "    WHERE ProductWeight >= 20.0;\n"
            + "\n"
            + "    UPDATE Products\n"
            + "    SET ProductWeightClass = 4\n"
            + "    WHERE ProductWeight < 20.0;\n"
            + "\n"
            + "    UPDATE Products\n"
            + "    SET ProductWeightClass = 3\n"
            + "    WHERE ProductWeight < 10.5;\n"
            + "\n"
            + "    UPDATE Products\n"
            + "    SET ProductWeightClass = 2\n"
            + "    WHERE ProductWeight < 5.5;\n"
            + "\n"
            + "    UPDATE Products\n"
            + "    SET ProductWeightClass = 1\n"
            + "    WHERE ProductWeight < 1.1;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "-- category functions\n"
            + "\n"
            + "--                                     (name     desc   )\n"
            + "CREATE OR REPLACE FUNCTION add_category(varchar, varchar) RETURNS void AS $$\n"
            + "    INSERT INTO Categories (\n"
            + "        CategoryName,\n"
            + "        CategoryDescription\n"
            + "    ) VALUES ($1, $2);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                    (name     desc   )\n"
            + "CREATE OR REPLACE FUNCTION add_category_get_categoryid(varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Categories (\n"
            + "        CategoryName,\n"
            + "        CategoryDescription\n"
            + "    ) VALUES ($1, $2) RETURNING CategoryID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION get_all_categories() RETURNS SETOF Categories AS $$\n"
            + "    SELECT * FROM Categories;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "-- manufacturer functions\n"
            + "\n"
            + "--                                         (name     email  )\n"
            + "CREATE OR REPLACE FUNCTION add_manufacturer(varchar, varchar) RETURNS void AS $$\n"
            + "    INSERT INTO Manufacturers (\n"
            + "        ManufacturerName,\n"
            + "        ManufacturerEmail\n"
            + "    ) VALUES ($1, $2);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                      (name     email    addrid )\n"
            + "CREATE OR REPLACE FUNCTION add_manufacturer_with_address(varchar, varchar, integer) RETURNS void AS $$\n"
            + "    INSERT INTO Manufacturers (\n"
            + "        ManufacturerName,\n"
            + "        ManufacturerEmail,\n"
            + "        ManufacturerAddressID\n"
            + "    ) VALUES ($1, $2, $3);\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                            (name     email  )\n"
            + "CREATE OR REPLACE FUNCTION add_manufacturer_get_manufacturerid(varchar, varchar) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Manufacturers (\n"
            + "        ManufacturerName,\n"
            + "        ManufacturerEmail\n"
            + "    ) VALUES ($1, $2) RETURNING ManufacturerID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "--                                                                         (name     email    addrid )\n"
            + "CREATE OR REPLACE FUNCTION add_manufacturer_with_address_get_manufacturerid(varchar, varchar, integer) RETURNS INTEGER AS $$\n"
            + "    INSERT INTO Manufacturers (\n"
            + "        ManufacturerName,\n"
            + "        ManufacturerEmail,\n"
            + "        ManufacturerAddressID\n"
            + "    ) VALUES ($1, $2, $3) RETURNING ManufacturerID;\n"
            + "$$ LANGUAGE SQL;\n"
            + "\n"
            + "CREATE OR REPLACE FUNCTION get_all_manufacturers() RETURNS SETOF Manufacturers AS $$\n"
            + "    SELECT * FROM Manufacturers;\n"
            + "$$ LANGUAGE SQL;";
}
