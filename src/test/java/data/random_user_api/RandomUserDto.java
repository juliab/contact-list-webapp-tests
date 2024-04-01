package data.random_user_api;

import java.util.Random;
import java.util.List;

import model.Contact;

/**
 * This class represents a random user from the Random User API.
 */
public class RandomUserDto {
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private String phone;
    private String cell;
    private Login login;
    private Dob dob;
    private Registered registered;
    private Id id;
    private Picture picture;
    private String nat;

    public Contact toContact() {
        return new Contact(getName().getFirst(),
                getName().getLast(),
                getDob().getFormattedDate(),
                getEmail(),
                generatePhoneNumber(), // phone is generated because web service returns a phone number in a different
                                       // format
                getLocation().getStreet().getNumber() + " " + getLocation().getStreet().getName(),
                "",
                getLocation().getCity(),
                getLocation().getState(),
                getLocation().getPostcode(),
                getLocation().getCountry());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    private String generatePhoneNumber() {
        Random random = new Random();
        int areaCode = random.nextInt(800) + 200;
        int centralOfficeCode = random.nextInt(800) + 200;
        int stationCode = random.nextInt(9000) + 1000;
        return String.format("%03d-%03d-%04d", areaCode, centralOfficeCode, stationCode);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    static class Login {
        private String uuid;
        private String username;
        private String password;
        private String salt;
        private String md5;
        private String sha1;
        private String sha256;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public String getSha256() {
            return sha256;
        }

        public void setSha256(String sha256) {
            this.sha256 = sha256;
        }
    }

    static class Dob {
        private String date;
        private int age;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String getFormattedDate() {
            return date.substring(0, 10);
        }
    }

    static class Registered {
        private String date;
        private int age;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    static class Id {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    static class Picture {
        private String large;
        private String medium;
        private String thumbnail;

        public String getLarge() {
            return this.large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return this.medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getThumbnail() {
            return this.thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    static class Name {
        private String title;
        private String first;
        private String last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first.trim();
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last.trim();
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    static class Location {
        private Street street;
        private String city;
        private String state;
        private String country;
        private String postcode;
        private Coordinates coordinates;
        private TimeZone timezone;

        public Street getStreet() {
            return street;
        }

        public void setStreet(Street street) {
            this.street = street;
        }

        public String getCity() {
            return city.trim();
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            String result = state.trim();

            // Trim this property value to 20 characters to be compliant with the
            // application rules
            if (result.length() > 20) {
                result = result.substring(0, 20);
            }

            return result;
        }

        public String getCountry() {
            return country.trim();
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostcode() {

            // API sometimes returns invalid postcodes for Canada, so one of hardcoded
            // postcodes is returned instead
            if (country.equals("Canada")) {
                Random random = new Random();

                List<String> postCodes = List.of("G0T 6E6", "H4C 4L4", "L9S 3S8", "J5W 2S1",
                        "E7B 8X7", "H1W 6G0", "G8P 9Y2", "K8B 8G0", "K7G 4A3", "V0X 0Y4", "G8P 6L7",
                        "G3G 8C8", "P2B 5X0", "S9V 2N3", "E5A 5G6", "L7G 7Y8", "A1W 1G0", "E1B 6K0",
                        "T3Z 9L6", "J1X 6J7", "T9G 7H3", "H9R 3G2", "E4H 0G2", "J9T 9G7", "L0N 2V9", "B5A 0A4");

                return postCodes.get(random.nextInt(postCodes.size()));
            }
            return postcode.trim();
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public TimeZone getTimezone() {
            return timezone;
        }

        public void setTimezone(TimeZone timezone) {
            this.timezone = timezone;
        }

        static class Coordinates {
            private String latitude;
            private String longitude;

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }

        static class TimeZone {
            private String offset;
            private String description;

            public String getOffset() {
                return this.offset;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public String getDescription() {
                return this.description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        static class Street {
            private int number;
            private String name;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getName() {
                return name.trim();
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
