package budgetAnalyzer;

import java.time.LocalDate;

public record Transaction(
                String description,
                double amount,
                Category category,
                LocalDate date) {

        public String toCsvRow(Transaction transaction) {
                return String.format(
                                "%s,%.2f,%s,%s",
                                this.description,
                                this.amount,
                                this.category.name(),
                                this.date);
        }
}
