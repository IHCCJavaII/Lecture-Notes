package budgetAnalyzer;

import java.time.LocalDate;

public class Transaction {
        private String description;
        private double amount;
        private Category category;
        private LocalDate date;
        // private Date date;

        public Transaction(String description, double amount, Category category, LocalDate date) {
                this.description = description;
                this.amount = amount;
                this.category = category;
                this.date = date;
        }

        public String getDescription() {
                return description;
        }

        public double getAmount() {
                return amount;
        }

        public Category getCategory() {
                return category;
        }

        public LocalDate getDate() {
                return date;
        }

        @Override
        public String toString() {
                return String.format("%s,%.2f,%s,%s,%n",
                                description, amount, category, date);

                // return description + "," + amount + "," + category + "," + date;
        }
}
