package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.Loan;
import model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberTest {

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member("John Doe", "123");
    }

    @Test
    public void testMemberInitialization() {
        assertEquals("John Doe", member.getName());
        assertEquals("123", member.getId());
    }

    @Test
    public void testAddLoan() {
        Loan loan = new Loan(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 5), member);
        member.addLoan(loan);
        assertEquals(1, member.getLoans().size());
    }
}

