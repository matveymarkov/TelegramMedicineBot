package bot.bot.models;

import bot.bot.helpers.DoctorEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "username")
    String username;

    @Column(name = "telegram_id")
    String tgId;

    @Column(name = "name")
    String name;

    @Column(name = "doc")
    @Enumerated
    DoctorEnum doctorEnum;

    @Column(name = "mark")
   String mark;
}
