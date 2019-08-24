package life.majiang.community.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private String access_id;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;




}
