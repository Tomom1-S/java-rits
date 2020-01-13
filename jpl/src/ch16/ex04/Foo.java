package ch16.ex04;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

/* JPL p.340 の例 */
@ClassInfo(
        created = "Jan 31 2005",
        createdBy = "James Gosling",
        lastModified = "Jan 31 2005",
        lastModifiedBy = "James Gosling",
        revision = @Revision(major = 3)
)

@RuntimeAnnotation(
        foo = "FOO",
        bar = "BAR",
        revision = @Revision
)

public class Foo {
    private final String name;
    private final UUID uuid;

    public Foo(String name) {
        this.name = name;
        this.uuid = UUID.nameUUIDFromBytes(name.getBytes());
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @MethodInfo(
            created = "Jan 01 2020",
            createdBy = "John Smith",
            description = "Show name & UUID"
    )
    public void showInfo() {
        System.out.println("Name: " + name + ", ID: " + uuid);
    }

}
