package totalPackage;

import javax.inject.Inject;

public class Reviewer {

    private String name;
    @Inject
    public Reviewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
