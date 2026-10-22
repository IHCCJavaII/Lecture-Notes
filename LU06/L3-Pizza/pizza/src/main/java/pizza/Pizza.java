package pizza;

public class Pizza {
    private Topping topping;
    private int size;
    private boolean isExtraCheese;

    public Pizza(Topping topping, int size, boolean isExtraCheese) {
        this.topping = topping;
        this.size = size;
        this.isExtraCheese = isExtraCheese;
    }

    public Pizza() {
    }

    public Topping getTopping() {
        return topping;
    }

    public int getSize() {
        return size;
    }

    public String isExtraCheese() {
        return isExtraCheese ? "Yes" : "No";
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setExtraCheese(boolean isExtraCheese) {
        this.isExtraCheese = isExtraCheese;
    }

    @Override
    public String toString() {
        return "Pizza [topping=" + topping + ", size=" + size + ", isExtraCheese=" + isExtraCheese() + "]";
    }
}
