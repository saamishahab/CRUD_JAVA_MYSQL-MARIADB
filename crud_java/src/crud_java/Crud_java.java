/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_java;

/**
 *
 * @author SaamiShahab
 */
import java.sql.*;
import java.util.Scanner;
public class Crud_java {

    /**
     * @param args the command line arguments
     */
    public static Connection con;
    public static Statement stm;
    public static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            String url ="jdbc:mariadb://localhost:3306/dbinventory_620";
            String user="root";
            String pass="";
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil");
            
            showMenu();
        } catch (Exception e) {
            System.err.println("koneksi gagal " +e.getMessage());
        }
        
    }
    
    static void showMenu() 
    {
//        Scanner sc = new Scanner (System.in);
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Inventory");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");
        try 
        {
            int pilihan = sc.nextInt();
            switch (pilihan) 
            {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertInventory();
                    break;
                case 2:
                    showInventory();
                    break;
                case 3:
                    updateInventory();
                    break;
                case 4:
                    deleteInventory();
                    break;
                case 5:
                    System.exit(1);
                break;
                default:
                    System.out.println("Pilihan salah!");
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    static void insertInventory() {
        try {
            Scanner sc1 = new Scanner (System.in);
            System.out.print("Kode: ");
            String kode = sc1.nextLine().trim();
            System.out.print("Nama: ");
            String nama = sc1.nextLine().trim();
            System.out.print("Qty: ");
            String qty = sc1.nextLine().trim();
            System.out.print("Harga: ");
            String harga = sc1.nextLine().trim();
            
            int total = Integer.valueOf(harga) * Integer.valueOf(qty);
                
            String sql = "INSERT INTO tbl_item (kode,nama,qty,harga,total) VALUES ('"+kode+"', '"+nama+"', '"+qty+"','"+harga+"','"+total+"')";
            sql = String.format(sql);

            stm.execute(sql);
            
            System.out.println(nama+" Telah Disimpan!");
            
            showInventory();
            showMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void showInventory() {
         try{
            stm = con.createStatement();
            stm.executeQuery("select * from tbl_item");
            ResultSet data = stm.getResultSet();
            
            while(data.next())
            {
                System.out.println("ID  : "+data.getString("id"));
                System.out.println("Kode  : "+data.getString("kode"));
                System.out.println("Nama  : "+data.getString("nama"));
                System.out.println("QTY   : "+data.getString("qty"));
                System.out.println("Harga : "+data.getString("harga"));
                System.out.println("Total : "+data.getString("total"));
                System.out.println("");
            }
            
            showMenu();
        }
        catch(Exception e)
        {
            System.out.println("Error in getData"+e);
        }
    }
    
    static void updateInventory() {
        
        try {
            Scanner sc1 = new Scanner (System.in);
            System.out.print("Masukkan ID item yang ingin di update: ");
            String id = sc1.nextLine().trim();
            System.out.print("Kode: ");
            String kode = sc1.nextLine().trim();
            System.out.print("Nama: ");
            String nama = sc1.nextLine().trim();
            System.out.print("Qty: ");
            String qty = sc1.nextLine().trim();
            System.out.print("Harga: ");
            String harga = sc1.nextLine().trim();
            
            int total = Integer.valueOf(harga) * Integer.valueOf(qty);
                
            String sql = "UPDATE tbl_item set kode='"+kode+"', nama='"+nama+"', qty="+qty+", harga="+harga+", total="+total+" where id="+id;
            sql = String.format(sql);

            stm.execute(sql);
            
            System.out.println(nama+" Telah Diupdate!");
            
            showInventory();
            showMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    static void deleteInventory() {
        
        try {
            Scanner sc1 = new Scanner (System.in);
            System.out.print("Masukkan ID item yang ingin di hapus: ");
            String id = sc1.nextLine().trim();
            String sql = "delete from tbl_item where id="+id;
            sql = String.format(sql);

            stm.execute(sql);
            
            System.out.println(id+" Telah Dihapus!");
            
            showInventory();
            showMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
