package budgetAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionIO {

    public static List<Transaction> load(Path file) throws IOException {
        var transactions = new ArrayList<Transaction>();
        var lines = Files.readAllLines(file);

        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }

            var values = line.split(",");
            if (values.length != 4) {
                throw new IllegalArgumentException("Invalid CSV row " + line);
            }

            transactions.add(new Transaction(
                    values[0].trim(),
                    Double.parseDouble(values[1].trim()),
                    Category.valueOf(values[2].trim().toUpperCase()),
                    LocalDate.parse(values[3].trim())));
        }

        return transactions;
    }

    public static void save(Path file, List<Transaction> transactions) throws IOException {
        var lines = new ArrayList<String>();

        for (var transaction : transactions) {
            lines.add(transaction.toCsvRow(transaction));
        }

        Files.write(file, lines);
    }
}
