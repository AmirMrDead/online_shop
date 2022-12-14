package entity.product;

import entity.enums.product.ProductType;

public class Radio extends Product {

    private boolean isCdPlayer;
    private boolean isCassettePlayer;
    private boolean isFlashPlayer;

    public Radio(int sellerId, String description,
                 int quantity, float price, boolean isCdPlayer, boolean isCassettePlayer, boolean isFlashPlayer) {
        super(ProductType.ELECTRONIC_APPLIANCES, sellerId, description, quantity, price);
        this.isCdPlayer = isCdPlayer;
        this.isCassettePlayer = isCassettePlayer;
        this.isFlashPlayer = isFlashPlayer;
    }

    public boolean isCdPlayer() {
        return isCdPlayer;
    }

    public void setCdPlayer(boolean cdPlayer) {
        isCdPlayer = cdPlayer;
    }

    public boolean isCassettePlayer() {
        return isCassettePlayer;
    }

    public void setCassettePlayer(boolean cassettePlayer) {
        isCassettePlayer = cassettePlayer;
    }

    public boolean isFlashPlayer() {
        return isFlashPlayer;
    }

    public void setFlashPlayer(boolean flashPlayer) {
        isFlashPlayer = flashPlayer;
    }

    @Override
    public String toString() {
        return super.toString() +
                "isCdPlayer=" + isCdPlayer +
                ", isCassettePlayer=" + isCassettePlayer +
                ", isFlashPlayer=" + isFlashPlayer;
    }
}
