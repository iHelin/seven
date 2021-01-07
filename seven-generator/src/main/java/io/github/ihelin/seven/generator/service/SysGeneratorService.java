package io.github.ihelin.seven.generator.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.ihelin.seven.generator.dao.MySQLGeneratorDao;
import io.github.ihelin.seven.generator.utils.GenUtils;
import io.github.ihelin.seven.generator.utils.PageUtils;
import io.github.ihelin.seven.generator.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-07 12:43
 */
@Service
public class SysGeneratorService {

    @Autowired
    private MySQLGeneratorDao generatorDao;

    @Autowired
    private GenUtils genUtils;


    public PageUtils queryList(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = generatorDao.queryList(query);
        int total = (int) page.getTotal();
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    public Map<String, String> queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }


    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            genUtils.generatorCode(table, columns, zip);
        }

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public String getTemplateString(String tableName){
        //查询表信息
        Map<String, String> table = queryTable(tableName);
        //查询列信息
        List<Map<String, String>> columns = queryColumns(tableName);
        //生成代码
        return genUtils.getTemplateString(table, columns);
    }
}
