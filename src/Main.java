import java.util.*;
enum Region {
    R1, R2, R3, R4
} 
 interface TaxStrategy {
    double calculateTax(double price);
} class R1Tax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.10; }
}
class R2Tax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.20; }
}
class R3Tax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.15; }
}
class R4Tax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.05; }
}
class TaxStrategyFactory {
    private static final Map<Region, TaxStrategy> map = new HashMap<>();
    static {
        map.put(Region.R1, new R1Tax());
        map.put(Region.R2, new R2Tax());
        map.put(Region.R3, new R3Tax());
        map.put(Region.R4, new R4Tax());
    }
    public static TaxStrategy getStrategy(Region region) {
        return map.get(region);
    }
}

class CountryRegionMapper {
    private static final Map<String, Region> map = new HashMap<>();
    static {
        map.put("usa", Region.R1);
        map.put("germany", Region.R2);
        map.put("france", Region.R2);
        map.put("india", Region.R3);
        map.put("japan", Region.R3);
        map.put("china", Region.R3);
        map.put("kenya", Region.R4);
        map.put("nigeria", Region.R4);
    }

    public static Region getRegion(String country) {
        return map.getOrDefault(country.toLowerCase(), Region.R3);
    }
    public static Set<String> getCountries() {
        return map.keySet();
    }
}
class CarPriceCalculator {
    private double basePrice;
    public CarPriceCalculator(double basePrice) {
        this.basePrice = basePrice;
    }
    public void generateBill(String country) {
        Region region = CountryRegionMapper.getRegion(country);
        TaxStrategy strategy = TaxStrategyFactory.getStrategy(region);
        double tax = strategy.calculateTax(basePrice);
        double finalPrice = basePrice + tax;
        System.out.println("\n------ CAR BILL ------");
        System.out.println("Country: " + country);
        System.out.println("Region: " + region);
        System.out.println("Base Price: " + basePrice);
        System.out.println("Tax: " + tax);
        System.out.println("Final Price: " + finalPrice);
        System.out.println("----------------------");
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Car Base Price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("\nAvailable Countries:");
        for (String c : CountryRegionMapper.getCountries()) {
            System.out.println("- " + c);
        }

        System.out.print("\nEnter Country: ");
        String country = sc.nextLine();

        CarPriceCalculator calculator = new CarPriceCalculator(price);
        calculator.generateBill(country);

        sc.close();
    }
}