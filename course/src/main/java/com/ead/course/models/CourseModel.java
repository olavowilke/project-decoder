package com.ead.course.models;


import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 250)
    private String description;
    @Column
    private String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;
    @Column(nullable = false)
    private UUID userInstructor;

    /*
    Annotating the property with OneToMany and ManyToOne on both sides will ensure a bi-directional
    relationship.

    The fetch = FetchType.LAZY tells Hibernate to lazily load courses for a given module.
    This means when we retrieve a course from the database, Hibernate won't return the associated modules
    for that course in the same call. A separate request is made to the database only when the
    application explicitly asks for course.getModules().

    The mappedBy = "course" attribute tells us the course table is the owning side of the relationship.

    When creating associated elements, Set is recommended over List, due to Hibernates perfomance
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ModuleModel> modules;

    /*
    FetchMode.SELECT
        Runs individual sql queries to retrieve each module from the specific course.
    FetchMode.JOIN
        Runs a single query to retrieve the course and its modules
    FetchMode.SUBSELECT
        Will execute two queries: One that will retrieve the course and another one to retrieve
        all its modules

    Bidirectional one-to-many and both many-to-one association mappings are fine. But you should avoid unidirectional
    one-to-many associations in your domain model. Otherwise, Hibernate might create unexpected tables and execute
    more SQL statements than you expected.
     */

}
