package Model;

import java.util.Objects;

public class Reader {

    private String readerCard;
    private String readerName;
    private String email;
    private String password;
    private int phoneNumber;
    private String address;
    private String city;
    private String role;

    public static Builder newBuilder() {
        return new Reader().new Builder();
    }

    public String getReaderCard() {
        return readerCard;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return phoneNumber == reader.phoneNumber &&
                password == reader.password &&
                readerCard == reader.readerCard &&
                Objects.equals(readerName, reader.readerName) &&
                Objects.equals(address, reader.address) &&
                Objects.equals(phoneNumber, reader.phoneNumber) &&
                Objects.equals(role, reader.role);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((readerCard == null) ? 0 :  readerCard.hashCode());
        result = prime * result + ((readerName == null) ? 0 :  readerName.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + phoneNumber;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Reader [" +
                " readerCard = '" + readerCard + '\'' +
                ", readerName = '" + readerName + '\'' +
                ", email = '" + email + '\'' +
                ", password = " + password +
                ", phoneNumber = " + phoneNumber +
                ", address = '" + address + '\'' +
                ", city = '" + city + '\'' +
                ", role = " + role +
                ']';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setReaderCard(String aReaderCard) {
            readerCard = aReaderCard;
            return this;
        }

        public Builder setReaderName(String aReaderName) {
            readerName = aReaderName;
            return this;
        }

        public Builder setEmail(String anEmail) {
            email = anEmail;
            return this;
        }

        public Builder setAddress(String anAddress) {
            address = anAddress;
            return this;
        }

        public Builder setCity(String aCity) {
            city = aCity;
            return this;
        }

        public Builder setPhoneNumber(int aPhoneNumber) {
            phoneNumber = aPhoneNumber;
            return this;
        }

        public Builder setPassword(String aPassword) {
            password = aPassword;
            return this;
        }

        public Builder setRole(String aRole) {
            role = aRole;
            return this;
        }

        public Reader build() {
            return Reader.this;
        }
    }

    public enum ROLE {
        ADMIN, READER;
    }
}
