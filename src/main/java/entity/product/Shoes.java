package entity.product;

import entity.enums.product.shoes.Color;
import entity.enums.product.ProductType;
import entity.enums.product.shoes.ShoesType;

import java.util.ArrayList;
import java.util.List;

public class Shoes extends Product {

    private final List<Integer> sizes = new ArrayList<>();
    private ShoesType type;
    private Color mainColor;

    public Shoes(String sellerName, String description, int quantity, float price, int size, ShoesType type, Color mainColor) {
        super(ProductType.SHOES, sellerName, description, quantity, price);
        this.type = type;
        this.mainColor = mainColor;
    }

    public ShoesType getType() {
        return type;
    }

    public void setType(ShoesType type) {
        this.type = type;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }
}
