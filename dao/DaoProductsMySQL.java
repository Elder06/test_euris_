package com.euris.test.dao;

import com.euris.test.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class DaoProductsMySQL extends util.BasicDao implements IDaoProducts {


    public DaoProductsMySQL(
         @Value("${db.address}") String dbAddress,
         @Value("${db.user}")String user,
         @Value("${db.psw}")   String password) {
        super(dbAddress, user, password);
    }

    private Product productFromMap(Map<String, String> map) {
        Product p = new Product();
        p.fromMap(map);
        return p;
    }

    /**
     * @return Metodo che ritorna una lista di di oggetti
     */
    @Override
    public List<Product> products() {

        List<Product>res = new ArrayList<>();
        List<Map<String,String>>data = getAll("SELECT * FROM products");

        for(Map<String,String>map : data){
            res.add(productFromMap(map));
        }
        return res;
    }
    /**
     * @param serial_number
     * @return Metodo che ritorna un singolo oggetto con ingresso un numero seriale per la ricerca
     */
    @Override
    public Product product(String serial_number) {
        Product res = new Product();
        Map<String,String>data = getOne("SELECT * FROM products WHERE serial_number= ?",serial_number );

        if(data != null){
            res = productFromMap(data);
            return res;
        }else{
            return null;
        }
    }
    /**
     * @param  p
     * @return Metodo con in ingresso un oggetto di tipo Product consente di aggiungere un nuovo oggetto
     * ritorna true se l' aggiunta è andata a buon fine
     */

    @Override
    public boolean add(Product p) {
       return executeAndIsModified("INSERT INTO products (serial_number, name, price) value (?,?,?)"
                ,p.getSerial_number(), p.getname(), p.getPrice());
    }
    /**
     * @param  serial_number
     * @return Metodo che prende in ingresso un numero seriale che identifica l' oggetto da eliminare
     * ritorna ture se l'eliminazione è andata a buon fine
     */

    @Override
    public boolean delete(String serial_number) {
        return executeAndIsModified("DELETE FROM products WHERE serial_number=?", serial_number);
    }
    /**
     * @param  p
     * @return Metodo con in ingresso un oggetto di tipo Product che consente di andare a modificare un oggetto
     * già esistente, ritorna true se la modifica è andata a buon fine
     */

    @Override
    public boolean update(Product p) {
        return executeAndIsModified("UPDATE products SET name=?, price=? WHERE serial_number=?",
              p.getname(),p.getPrice(),p.getSerial_number());
    }
    /**
     * @param  serial_number
     * @return Metodo per visualizzare il prezzo
     */

    @Override
    public String price(String serial_number) {
        Product p = product(serial_number);
        return p.getPrice();
    }
    /**
     * @param  serial_number
     * @param  serial_B
     * @return Metodo che date le due stringhe in ingresso permette di effettuare l' addizione
     */

    @Override
    public String sum(String serial_number, String serial_B) {
        String priceA = price(serial_number);
        String priceB = price(serial_B);

        String[] p1 = priceA.split("[,]");
        String[] p2 = priceB.split("[,]");

        int[] a = new int[3];
        int[] b = new int[3];

        for(int i = 0; i <3; i++){
            a[i] = Integer.parseInt(p1[i]);
            b[i] = Integer.parseInt(p2[i]);
        }
         int Q = a[0] * 20 * 12 + a[1] * 12 +a[2];
         int W =  b[0] * 20 * 12 + b[1] * 12 +b[2];
         int num = Q + W;

         int z = Math.abs(num%12);
         int x = Math.abs(num/12 %20);
         int y = num/12/20;
         String sum = (y + "p" + x + "s" + z +"d");

         return  sum ;
    }
    /**
     * @param  serial_number
     * @param  serial_B
     * @return Metodo che date le due stringhe in ingresso permette di effettuare la sottrazione
     */

    @Override
    public String diff(String serial_number, String serial_B) {
        String priceA = price(serial_number);
        String priceB = price(serial_B);

        String[] p1 = priceA.split("[,]");
        String[] p2 = priceB.split("[,]");

        int[] a = new int[3];
        int[] b = new int[3];

        for(int i = 0; i <3; i++){
            a[i] = Integer.parseInt(p1[i]);
            b[i] = Integer.parseInt(p2[i]);
        }
        int Q = a[0] * 20 * 12 + a[1] * 12 +a[2];
        int W =  b[0] * 20 * 12 + b[1] * 12 +b[2];
        int num = Q - W;

        int z = Math.abs(num%12);
        int x = Math.abs(num/12 %20);
        int y = num/12/20;
        String diff = (y + "p" + x + "s" + z +"d");

        return  diff ;
    }
    /**
     * @param  serial_number
     * @param  multiplier
     * @return Metodo che dato in ingresso una stringa e un moltiplicatore, permette
     * di effettuare la moltiplicazione
     */

    @Override
    public String multy(String serial_number, int multiplier) {
        String priceA = price(serial_number);

        String[] p1 = priceA.split("[,]");

        int[] a = new int[3];

        for(int i = 0; i <3; i++){
            a[i] = Integer.parseInt(p1[i]);

        }
        int r = a[0]*multiplier;
        int r1 = a[1]*multiplier;
        int r2 = a[2]*multiplier;


        int Q = r * 20 * 12 + r1 * 12 +r2;

        int num = Q;

        int z = Math.abs(num%12);
        int x = Math.abs(num/12 %20);
        int y = num/12/20;


        String multy = (z + "p" + x + "s" + y +"d");

        return  multy;
    }
    /**
     * @param  serial_number
     * @param  divider
     * @return Metodo che dato in ingresso una stringa e un divosore, permette
     * di calcolare il quoziente ed il modulo
     */

    @Override
    public String div(String serial_number, int divider) {
        String priceA = price(serial_number);

        String[] p1 = priceA.split("[,]");

        int[] a = new int[3];

        for(int i = 0; i <3; i++){
            a[i] = Integer.parseInt(p1[i]);

        }
        int Q = a[0] * 20 * 12 + a[1] * 12 +a[2];

        int num = Q;

        int z = Math.abs(num%12);
        int x = Math.abs(num/12 %20);
        int y = num/12/20;
        int zQuotient = z / divider;
        int xQuotient = x / divider;
        int yQuotient = y / divider;
        int zRest = z % divider;
        int xRest = x % divider;
        int yRest = y % divider;

        String quotient = (zQuotient + "p" + xQuotient + "s" + yQuotient +"d");
        String rest = (zRest + "p" + xRest + "s" + yRest +"d");
        return quotient + "rest : " + "(" + rest + ")";
    }



}
