package Model;

import java.sql.Date;

public class BookOrder {

    private int orderID;
    private Book book;
    private Reader reader;
    private Date dateOfLoan;
    private Date returnDate;
    private int howMuchDays;


    public int getOrderID() {
        return orderID;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
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
        return book == bookOrder.book &&
                reader == bookOrder.reader &&
                dateOfLoan == bookOrder.dateOfLoan &&
                returnDate == bookOrder.returnDate &&
                orderID == bookOrder.orderID &&
                howMuchDays == bookOrder.howMuchDays;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        result = prime * result + ((reader == null) ? 0 : reader.hashCode());
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
                "book = " + book.toString() +
                ", reader = " + reader.toString() +
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

        public Builder setBook(Book aBook) {
            book = aBook;
            return this;
        }

        public Builder setReader(Reader aReader) {
            reader = aReader;
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
