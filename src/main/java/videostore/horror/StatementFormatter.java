package videostore.horror;

import java.util.List;
import java.util.stream.Collectors;

public class StatementFormatter {


   public String format(String customerName, List<Rental> rentals) {
      return formatHeader(customerName) +
             formatBody(rentals) +
             formatFooter(rentals);
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalFrequentRenterPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
   }

   private String formatBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
             "You earned " + computeTotalFrequentRenterPoints(rentals) + " frequent renter points";
   }

}