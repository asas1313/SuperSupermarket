import exceptions.NotEnoughChangeException;
import exceptions.PayNotAcceptedException;
import exceptions.SoldOutException;
import model.StoredProduct;
import service.InputReader;
import service.SupermarketService;
import service.SupermarketServiceImpl;

public class SuperSupermarket {
    public static void main(String[] args) {

        SupermarketService supermarket = SupermarketServiceImpl.getInstance().openSupermarket();

        System.out.println("------------------");
        System.out.println("Initial Product Inventory:\n" + supermarket.productInventory());
        System.out.println("Initial Cash Inventory:\n" + supermarket.cashInventory());
        System.out.println("------------------");

        //noinspection InfiniteLoopStatement
        while (true) {

            System.out.println("What would You like to buy? Type in the name of the desired product.");
            System.out.println(supermarket.getPriceList());

            String name = InputReader.readLine();

            StoredProduct item;
            try {
                item = supermarket.buyItem(name);
                float price = item.getProduct().getPrice();
                supermarket.payAmount(price);
                supermarket.providePurchase(item);
            } catch (SoldOutException | NotEnoughChangeException | PayNotAcceptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("------------------");
            System.out.println("Updated Product Inventory:\n" + supermarket.productInventory());
            System.out.println("Updated Cash Inventory:\n" + supermarket.cashInventory());
            System.out.println("------------------");


        }
    }
}