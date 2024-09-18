package headlinesearch;

public class headlineObject {

    // instance variables
    private String link;
    private String headline;
    private String category;
    private String description;
    private String authors;
    private String date;

    // constructor for headlineObject
    public headlineObject(String link, String headline, String category, String description, String authors,
            String date) {
        this.link = link;
        this.headline = headline;
        this.category = category;
        this.description = description;
        this.authors = authors;
        this.date = date;
    }

    // accessor methods
    public String getLink() {
        return link;
    }

    public String getHeadLine() {
        return headline;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDate() {
        return date;
    }

    // string representation of headlineObject
    public String toString() {
        String s = "\n HeadLine Object";
        s += "\n\tLink: " + link;
        s += "\n\tHeadLine: " + headline;
        s += "\n\tCategory: " + category;
        s += "\n\tDescription " + description;
        s += "\n\tAuthors: " + authors;
        s += "\n\tDate: " + date;
        return s;
    }

    // returns true if this object equals other, false otherwise
    public boolean equals(Object other) {

        if (other == null || !(other instanceof headlineObject)) {
            return false;
        }
        headlineObject x = (headlineObject) other;
        return (link.equals(x.getLink()) &&
                headline.equals(x.getHeadLine()) &&
                category.equals(x.getCategory()) &&
                description.equals(x.getDescription()) &&
                authors.equals(x.getAuthors()) &&
                date.equals(x.getDate()));
    }
}