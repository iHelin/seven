/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.github.ihelin.seven.generator.config;

import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class DbConfig {

//    @Value("${renren.database: mysql}")
//    private String database;
//
//    @Autowired
//    private MySQLGeneratorDao mySQLGeneratorDao;
//
//    private static boolean mongo = false;

//    @Bean
//    @Primary
//    @Conditional(MongoNullCondition.class)
//    public GeneratorDao getGeneratorDao() {
//        if ("mysql".equalsIgnoreCase(database)) {
//            return mySQLGeneratorDao;
//        } else {
//            throw new RRException("不支持当前数据库：" + database);
//        }
//    }

//    @Bean
//    @Primary
//    @Conditional(MongoCondition.class)
//    public GeneratorDao getMongoDBDao(MongoDBGeneratorDao mongoDBGeneratorDao) {
//        mongo = true;
//        return mongoDBGeneratorDao;
//    }
//
//    public static boolean isMongo() {
//        return mongo;
//    }

}
