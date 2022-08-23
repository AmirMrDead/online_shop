package view.seller;

import entity.Person;
import entity.Seller;
import entity.enums.product.ProductType;
import entity.enums.product.book.BookSubject;
import entity.enums.product.book.BookType;
import entity.enums.product.shoes.Color;
import entity.enums.product.shoes.ShoesType;
import entity.enums.product.tv.DisplayType;
import entity.product.*;
import service.*;
import service.electronic_appliances.RadioServiceImpl;
import service.electronic_appliances.TvServiceImpl;
import service.readable.BookServiceImpl;
import service.shoes.ShoesServiceImpl;

import java.util.*;

public class SellerMethods {

    private final Scanner scanner = new Scanner(System.in);
    private final SellerServiceImpl sellerService = new SellerServiceImpl();
    private final TvServiceImpl tvService = new TvServiceImpl();
    private final RadioServiceImpl radioService = new RadioServiceImpl();
    private final BookServiceImpl bookService = new BookServiceImpl();
    private final ShoesServiceImpl shoesService = new ShoesServiceImpl();

    public void signup() {
        PersonServiceImpl personService = new PersonServiceImpl();
        System.out.print("Enter your national code: ");
        String nationalCode = scanner.next();
        if (personService.loadByNationalCode(nationalCode) == null) {
            System.out.print("Enter your firstname: ");
            String firstname = scanner.next();
            System.out.print("Enter your lastname: ");
            String lastname = scanner.next();
            personService.save(firstname, lastname, nationalCode);
        }
        Person person = personService.loadByNationalCode(nationalCode);
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your company: ");
        String company = scanner.next();
        System.out.print("Enter your product type: ");
        ProductType type = ProductType.valueOf(scanner.next().toUpperCase());
        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.next();
            System.out.print("Again enter your password: ");
            String againPassword = scanner.next();
            if (Objects.equals(password, againPassword))
                break;
        }
        if (sellerService.save(username, password, type, person.getPersonId(), company)) {
            SellerMenu sellerMenu = new SellerMenu();
            sellerMenu.showMenu(sellerService.load(username, password));
        }
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        Seller seller = sellerService.load(username, password);
        if (seller != null) {
            SellerMenu sellerMenu = new SellerMenu();
            sellerMenu.showMenu(seller);
        } else {
            System.out.println("This username was not found with this password");
        }
    }

    public void addElectronicAppliances(Seller seller) {
        ProductServiceImpl productService = new ProductServiceImpl();
        System.out.println("Which item? (for now we have tv and radio): ");
        String type = scanner.next();
        Product product = saveProduct(seller, productService);
        if (Objects.equals(type.toLowerCase(), "tv")) {
            saveTv(product);
        } else if (Objects.equals(type.toLowerCase(), "radio")) {
            saveRadio(product);
        }
    }

    public void addReadable(Seller seller) {
        ProductServiceImpl productService = new ProductServiceImpl();
        Product product = saveProduct(seller, productService);
        saveBook(product);
    }

    public void addShoes(Seller seller) {
        ProductServiceImpl productService = new ProductServiceImpl();
        Product product = saveProduct(seller, productService);
        saveShoes(product);
    }

    public void showProductsInformation(Seller seller) {
        if (seller.getProductType() == ProductType.ELECTRONIC_APPLIANCES) {
            List<Tv> tvs = tvService.load(seller.getId());
            List<Radio> radios = radioService.load(seller.getId());
            for (Tv tv : tvs) {
                System.out.println(tv);
            }
            for (Radio radio : radios) {
                System.out.println(radio);
            }
        } else if (seller.getProductType() == ProductType.READABLE) {
            List<Book> books = bookService.load(seller.getId());
            for (Book book : books) {
                System.out.println(book);
            }
        } else if(seller.getProductType() == ProductType.SHOES){
            List<Shoes> shoesList = shoesService.load(seller.getId());
            for(Shoes shoes: shoesList){
                System.out.println(shoes);
            }
        }
    }

    private void saveShoes(Product product) {
        System.out.print("Enter shoes type (for now we have formal and sport and slippers): ");
        String type = scanner.next().toUpperCase();
        System.out.print("Enter main color of shoes ( for now we have blue, red, yellow, black, white, purple): ");
        String color = scanner.next().toUpperCase();
        int[] temp = new int[1000];
        int index = 0;
        while (true) {
            System.out.print("Enter size of shoes (Enter 0 if end) : ");
            int size = scanner.nextInt();
            if (size == 0)
                break;
            temp[index++] = size;
        }
        int[] sizes = new int[index];
        System.arraycopy(temp, 0, sizes, 0, index);
        shoesService.save(sizes, Color.valueOf(color), ShoesType.valueOf(type), product.getId());
    }

    private void saveBook(Product product) {
        System.out.print("Enter book type ( for now we have book and magazine): ");
        String type = scanner.next().toUpperCase();
        System.out.print("Enter book subject ( for now we have action, adventure, comic, " +
                "horror, fantasy, historical_fiction): ");
        String subject = scanner.next().toUpperCase();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter publisher name: ");
        String publisherName = scanner.nextLine();
        System.out.print("Enter number of pages");
        int number = scanner.nextInt();
        bookService.save(BookType.valueOf(type), BookSubject.valueOf(subject), number, authorName,
                publisherName, product.getId());
    }

    private void saveRadio(Product product) {
        boolean cdPlayer, cassettePlayer, flashPlayer;
        System.out.print("Is cd player? (yes or no): ");
        String isCdPlayer = scanner.next();
        System.out.print("Is cassette player? (yes or no): ");
        String isCassettePlayer = scanner.next();
        System.out.print("Is flash player? (yes or no): ");
        String isFlashPlayer = scanner.next();
        cdPlayer = isCdPlayer.equalsIgnoreCase("yes");
        cassettePlayer = isCassettePlayer.equalsIgnoreCase("yes");
        flashPlayer = isFlashPlayer.equalsIgnoreCase("yes");
        radioService.save(cdPlayer, cassettePlayer, flashPlayer, product.getId());
    }

    private void saveTv(Product product) {
        System.out.print("Enter inch: ");
        int inch = scanner.nextInt();
        System.out.print("Enter display type: ");
        String display = scanner.next().toUpperCase();
        tvService.save(inch, DisplayType.valueOf(display), product.getId());
    }

    private Product saveProduct(Seller seller, ProductServiceImpl productService) {
        System.out.print("Enter description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        float price = scanner.nextFloat();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        return productService.save(new Product(ProductType.ELECTRONIC_APPLIANCES, seller.getId()
                , description, quantity, price), seller.getId());
    }

}