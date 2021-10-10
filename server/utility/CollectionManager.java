package server.utility;

import data.Ticket;
import server.FileHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to work with collection.
 */

public class CollectionManager {
    private HashSet<Ticket> ticketCollection = new HashSet<Ticket>();
    private LocalDateTime lastSaveTime;
    private LocalDateTime lastInitializationTime;
    private FileHelper fileHelper;

    public CollectionManager(FileHelper fileHelper) {
        this.lastInitializationTime = null;
        this.lastSaveTime = null;
        this.fileHelper = fileHelper;

        loadCollection();
    }

    public HashSet<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public LocalDateTime getLastInitializationTime() {
        return lastInitializationTime;
    }

    public String collectionType() {
        return ticketCollection.getClass().getName();
    }

    public Ticket getLastElement() {
        final Iterator itr = ticketCollection.iterator();
        Ticket lastElement = (Ticket) itr.next();
        while(itr.hasNext()) {
            lastElement = (Ticket) itr.next();
        }
        return lastElement;
    }
    public int collectionSize() {
        return ticketCollection.size();
    }
    public Ticket getById(Long id) {
        for (Ticket ticket : ticketCollection) {
            if (ticket.getId() == id) return ticket;
        }
        return null;
    }

    public long createNextId() {
        if (ticketCollection.isEmpty()) return 1L;
        return ticketCollection.size() + 1;
    }

    public void removeFromCollection(Ticket ticket) {
        ticketCollection.remove(ticket);
    }

    public Ticket getByValue(Ticket ticketValue) {
        for (Ticket ticket : ticketCollection) {
            if (ticket.equals(ticketValue)) return ticket;
        }
        return null;
    }

    public void removeGreater(Ticket ticketCompare) {
            ticketCollection.removeIf(ticket -> ticket.compareTo(ticketCompare) > 0);
    }

    public void addToCollection(Ticket ticket) {
        ticketCollection.add(ticket);
    }

    public void deleteFromCollection(Ticket ticket) {
        ticketCollection.remove(ticket);
    }

    public void deleteCollection() {
        ticketCollection.clear();
    }

    /*public String nameFilter(String nameFilter) {
        String info = "";
        for (Ticket ticket : ticketCollection) {
            if (ticket.getName().equals(nameFilter)) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }*/

    public String nameFilter(String nameFilter) {
        String info = "";
        for (Ticket ticket : ticketCollection) {
            String name = ticket.getName();
            Pattern pattern = Pattern.compile(nameFilter);
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }

    public String priceLess(double priceLess) {
        String info = "";
        for (Ticket ticket : ticketCollection) {
            if (ticket.getPrice() < priceLess) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }

    public void sortDiscount() {
        int i = 0;
        int[] discounts = new int[ticketCollection.size()];
        long[] id = new long[ticketCollection.size()];
        for (Ticket ticket : ticketCollection) {
            discounts[i] = ticket.getDiscount();
            id[i] = ticket.getId();
            i++;
        }
        for (i = 0; i < discounts.length; i++) {
            for (int j = i + 1; j < discounts.length; j++) {
                if (discounts[i] > discounts[j]) {
                    int tmp = discounts[i];
                    discounts[i] = discounts[j];
                    discounts[j] = tmp;
                    long tmpL = id[i];
                    id[i] = id[j];
                    id[j] = tmpL;
                    j = i + 1;
                }
            }
        }
        for (i = 0; i < discounts.length; i++) {
            System.out.println("Билет №" +id[i] + ": " +discounts[i]);
        }
    }

    public String showCollection() {
        if (ticketCollection.isEmpty()) return "Коллекция пуста!";
        return ticketCollection.stream().reduce("", (sum, m) -> sum += m + "\n\n", (sum1, sum2) -> sum1 + sum2).trim();
    }

    public void saveCollection () throws IOException {
        fileHelper.saveCollectionToFile(ticketCollection);
        lastSaveTime = LocalDateTime.now();
    }

    private void loadCollection() {
        ticketCollection = fileHelper.readCollectionFromFile();
        lastInitializationTime = LocalDateTime.now();
    }

    public String toString() {
        if (ticketCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Ticket ticket : ticketCollection) {
            info += ticket;
            if (!ticket.equals(null)) info += "\n\n";
        }
        return info;
    }

}
