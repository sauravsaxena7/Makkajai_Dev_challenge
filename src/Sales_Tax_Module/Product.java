package Sales_Tax_Module;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal basic_sales_tax;
    private BigDecimal import_duty_sales_tax;
    private Boolean  isImported;
    private Boolean isExempted;
    private BigDecimal price;
    private BigDecimal totalCalculatedTaxOnCurrentItem;
    private BigDecimal totalTax;
    private int quantity;


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", basic_sales_tax=" + basic_sales_tax +
                ", import_duty_sales_tax=" + import_duty_sales_tax +
                ", isImported=" + isImported +
                ", isExempted=" + isExempted +
                ", price=" + price +
                ", totalCalculatedTaxOnCurrentItem=" + totalCalculatedTaxOnCurrentItem +
                ", totalTax=" + totalTax +
                ", quantity=" + quantity +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasic_sales_tax() {
        return basic_sales_tax;
    }

    public void setBasic_sales_tax(BigDecimal basic_sales_tax) {
        this.basic_sales_tax = basic_sales_tax;
    }

    public BigDecimal getImport_duty_sales_tax() {
        return import_duty_sales_tax;
    }

    public void setImport_duty_sales_tax(BigDecimal import_duty_sales_tax) {
        this.import_duty_sales_tax = import_duty_sales_tax;
    }

    public Boolean getImported() {
        return isImported;
    }

    public void setImported(Boolean imported) {
        isImported = imported;
    }

    public Boolean getExempted() {
        return isExempted;
    }

    public void setExempted(Boolean exempted) {
        isExempted = exempted;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalCalculatedTaxOnCurrentItem() {
        return totalCalculatedTaxOnCurrentItem;
    }

    public void setTotalCalculatedTaxOnCurrentItem(BigDecimal totalCalculatedTaxOnCurrentItem) {
        this.totalCalculatedTaxOnCurrentItem = totalCalculatedTaxOnCurrentItem;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
