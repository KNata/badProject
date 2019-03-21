package Model;

import java.util.ArrayList;
import java.sql.Date;

public class Book {

    private int isbn;
    private String author;
    private String title;
    private String annotation;
    private String ownLocation;
    private Date yearOfPublishing;
    private int bookCount;
    private ArrayList<String> keyWordsList;

    public static Builder newBuilder() {
        return new Book().new Builder();
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getOwnLocation() {
        return ownLocation;
    }

    public Date getYearOfPublishing() {
        return yearOfPublishing;
    }

    public int getBookCount() {
        return bookCount;
    }

    public ArrayList<String> getKeyWordsList() {
        return keyWordsList;
    }


    public class Builder {

        private Builder() {
        }

        public Builder setIsbn(int anIsbn) {
            isbn = anIsbn;
            return this;
        }

        public Builder setAuthor(String anAuthor) {
            author = anAuthor;
            return this;
        }

        public Builder setTitle(String aTitle) {
            title = aTitle;
            return this;
        }

        public Builder setYearOfPublishing(Date aYearOfPublishing) {
            yearOfPublishing = aYearOfPublishing;
            return this;
        }

        public Builder setAnnotation(String anAnnotation) {
            annotation = anAnnotation;
            return this;
        }


        public Builder setBookCount(int aBookCount) {
            bookCount = bookCount;
            return this;
        }

        public Builder setKeyWordsList(ArrayList<String> aKeyWordsList) {
            keyWordsList = aKeyWordsList;
            return this;
        }


        public Book build() {
            return Book.this;
        }
    }

}
