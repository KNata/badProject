package Model;

import java.sql.Date;

public class BookOrder {

    private int orderID;
    private String book_isbn;
    private String reader_card;
    private Date dateOfLoan;
    private Date returnDate;
    private int howMuchDays;


    public int getOrderID() {
        return orderID;
    }

    public String getBook() {
        return book_isbn;
    }

    public String getReader() {
        return reader_card;
    }

    public Date getDateOfLoan() {
        return dateOfLoan;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public int getHowMuchDays() {
        return howMuchDays;
    }

    public static Builder newBuilder() {
        return new BookOrder().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return book_isbn == bookOrder.book_isbn &&
                reader_card == bookOrder.reader_card &&
                dateOfLoan == bookOrder.dateOfLoan &&
                returnDate == bookOrder.returnDate &&
                orderID == bookOrder.orderID &&
                howMuchDays == bookOrder.howMuchDays;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book_isbn == null) ? 0 : book_isbn.hashCode());
        result = prime * result + ((reader_card == null) ? 0 : reader_card.hashCode());
        result = prime * result + ((dateOfLoan == null) ? 0 : dateOfLoan.hashCode());
        result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
        result = prime * result + howMuchDays;
        result = prime * result + orderID;
        return result;
    }

    @Override
    public String toString() {
        return "BookOrder [" +
                ", orderID = " + orderID +
                "book = " + book_isbn +
                ", reader = " + reader_card +
                ", dateOfLoan = " + dateOfLoan +
                ", returnDate = " + returnDate +
                ", howMuchDays = " + howMuchDays +
                ']';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setOrderID(int anOrderID) {
            orderID = anOrderID;
            return this;
        }

        public Builder setBook(String aBook) {
            book_isbn = aBook;
            return this;
        }

        public Builder setReader(String aReader) {
            reader_card = aReader;
            return this;
        }

        public Builder setDateOfLoan(Date aDate) {
            dateOfLoan = aDate;
            return this;
        }

        public Builder setDateOfReturn(Date aDateOfReturn) {
            returnDate = aDateOfReturn;
            return this;
        }

        public Builder setDaysOfLoan(int aCountDaysOfLoan) {
            howMuchDays = aCountDaysOfLoan;
            return this;
        }

        public BookOrder build() {
            return BookOrder.this;
        }
    }
}
