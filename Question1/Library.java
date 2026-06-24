import java.time.LocalDate;
    import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void registerMember(Member member) { members.add(member); }

    public boolean lendBook(Book book, Member member) {
        if (!book.isAvailable()) {
            System.out.println("Book already on loan: " + book.getTitle());
            return false;
        }
        Loan loan = new Loan(book, member, LocalDate.now(), LocalDate.now().plusWeeks(2));
        member.getLoans().add(loan);
        book.setAvailable(false);
        return true;
    }

    public boolean returnBook(Book book, Member member) {
        Optional<Loan> loan = member.getLoans().stream()
            .filter(l -> l.getBook().equals(book)).findFirst();
        if (loan.isPresent()) {
            member.getLoans().remove(loan.get());
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public Book searchBookByTitle(String title) {
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Library[Books=" + books + ", Members=" + members + "]";
    }
}

    

