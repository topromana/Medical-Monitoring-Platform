package application.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "activity_id")
    private Long activity_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "activity")
    private String activity;
    @Column(name = "start_time")
    private Long start_time;
    @Column(name = "end_time")
    private Long end_time;

    public Activity() {
    }

    public Activity(User user, String activity, Long start, Long end) {
        this.user = user;
        this.activity = activity;
        this.start_time = start;
        this.end_time = end;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }
}
