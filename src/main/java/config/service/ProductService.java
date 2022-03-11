package config.service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Fushigidane", "https://vn.portal-pokemon.com/play/resources/pokedex/img/pm/cf47f9fac4ed3037ff2a8ea83204e32aff8fb5f3.png"));
        products.add(new Product(2, "Hitokage", "https://vn.portal-pokemon.com/play/resources/pokedex/img/pm/d0ee81f16175c97770192fb691fdda8da1f4f349.png"));
        products.add(new Product(3, "Zenigame", "https://vn.portal-pokemon.com/play/resources/pokedex/img/pm/5794f0251b1180998d72d1f8568239620ff5279c.png"));
        products.add(new Product(4, "Pikachu", "https://vn.portal-pokemon.com/play/resources/pokedex/img/pm/2b3f6ff00db7a1efae21d85cfb8995eaff2da8d8.png"));
    }

    public void edit(int index, Product product) {
        products.set(index, product);
    }

    public void add(Product product) {
        products.add(product);
    }

    public void delete(int index) {
        products.remove(index);
    }

    public static Product getProduct(int id) {
        return products.get(findIndexById(id));
    }

    public static int findIndexById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
