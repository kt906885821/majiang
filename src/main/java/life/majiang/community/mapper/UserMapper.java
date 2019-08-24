package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,access_id,token,gmt_create,gmt_modified,avatarUrl)values(#{name},#{access_id},#{token},#{gmt_create},#{gmt_modified},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token=#{token}")
    public User findByToken(@Param("cookieValue") String cookieValue);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);
}
