package io.github.ihelin.seven.generator.dao;


import java.util.List;
import java.util.Map;

/**
 * MySQL代码生成器
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-07 12:43
 */
public interface MySQLGeneratorDao {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
