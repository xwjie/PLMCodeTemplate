package cn.xiaowenjie.codetemplate.daos;

import cn.xiaowenjie.codetemplate.entity.Config;
import cn.xiaowenjie.codetemplate.ConfigsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigDao {
    long countByExample(ConfigsExample example);
    long count();

    int deleteByExample(ConfigsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExample(ConfigsExample example);

    Config selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigsExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigsExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}