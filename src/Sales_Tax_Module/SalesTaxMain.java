package Sales_Tax_Module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalesTaxMain {

    public static void main(String[] args){
        System.out.println("Please Enter the number Product " +
                "item for calculating price(including tax).");
        Scanner sc = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());

        while(n>0){
            String input = sc.nextLine();
            //System.out.println(str);
            productList.add(buildProductObject(input.trim()));
            n--;
        }

        //object Building completed now
        //System.out.println(productList);

        //Now perform actual sales tax calculation as per instruction given in assignment.
        calculateSalesTaxOfItem(productList);
    }

    private static void calculateSalesTaxOfItem(List<Product> productList) {
        //actual calculation logic start
        //Basic sales tax is applicable at a rate of 10% on all goods, except books, food,
        //and medical products that are exempt.

        BigDecimal totalSalesTaxOfAllGoodsItem = BigDecimal.ZERO;
        BigDecimal totalPriceOfAllGoodsItem = BigDecimal.ZERO;

        for(Product product:productList){

            if(product.getExempted()){
                product.setBasic_sales_tax(BigDecimal.ZERO);
            }else {
                product.setBasic_sales_tax(new BigDecimal("10.00"));
            }

            //Import duty is an additional sales tax
            //applicable on all imported goods at a rate of 5%, with no exemptions.
            if(product.getImported()){
                product.setImport_duty_sales_tax(new BigDecimal("5.00"));
            }else{
                product.setImport_duty_sales_tax(BigDecimal.ZERO);
            }

            //calculate price including tax;
            product.setTotalTax(product.getImport_duty_sales_tax().add(product.getBasic_sales_tax()));
            BigDecimal totalTaxAmount = calculatingTaxAmountForGoods(product.getPrice(),product.getTotalTax());
            product.setPrice(product.getPrice().add(totalTaxAmount));

            //final sum amount is also added for current goods item
            totalPriceOfAllGoodsItem = totalPriceOfAllGoodsItem.add(product.getPrice());

            //final total tax is also added for current goods item.
            totalSalesTaxOfAllGoodsItem = totalSalesTaxOfAllGoodsItem.add(totalTaxAmount);

        }

        //System.out.println(productList);
        for(Product product : productList){
            System.out.printf(product.getName()+": %.2f%n",product.getPrice());
        }

        System.out.printf("Sales Taxes: %.2f%n",totalSalesTaxOfAllGoodsItem);
        System.out.printf("Total: %.2f%n",totalPriceOfAllGoodsItem);

    }

    private static BigDecimal calculatingTaxAmountForGoods(BigDecimal price, BigDecimal totalTax) {
        BigDecimal taxAmount = roundUpTo_005(price.multiply(totalTax).divide(new BigDecimal("100.00")));
        return taxAmount;
    }

    private static BigDecimal roundUpTo_005(BigDecimal tax) {
        BigDecimal increment = new BigDecimal("0.05");
        return tax.divide(increment,0, RoundingMode.UP).multiply(increment);
    }


    private static Product buildProductObject(String input){
        Product item = new Product();
        item.setName("");
        String[] inputArray = input.split(" ");
        item.setQuantity(Integer.parseInt(inputArray[0]));
        for(int i=0;i<inputArray.length-1;i++){
            if(!inputArray[i].equals("at")){
                item.setName(item.getName()+inputArray[i]+" ");
            }
        }
        item.setName(item.getName().trim());

        item.setImported(input.contains("imported"));

        item.setExempted(checkForIsExempted(input));
        item.setPrice(new BigDecimal(inputArray[inputArray.length-1]));

        return item;
    }

    private static Boolean checkForIsExempted(String input) {
        //here I am try to cover all keywords as per input provided
        //But we can directly take input from User and we can set IsExempted true or not.
        if(input.contains("book")
                || input.contains("food")
                || input.contains("medical products")
                || input.contains("pills")
                || input.contains("syrup")
                || input.contains("chocolates")
                || input.contains("chocolate")
        ){
            return true;
        }
        return false;
    }
}

//Input output Instruction
/*
"C:\Program Files\Java\jdk-22\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.1.4\lib\idea_rt.jar=58195:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.1.4\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\volume e\java_projects\Makkajai_Dev_challenge_task _sauravsrivastava121\out\production\Makkajai_Dev_challenge_task _sauravsrivastava121" Sales_Tax_Module.SalesTaxMain
Please Enter the number Product item for calculating price(including tax).
//Input
3
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

output
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Process finished with exit code 0

**/