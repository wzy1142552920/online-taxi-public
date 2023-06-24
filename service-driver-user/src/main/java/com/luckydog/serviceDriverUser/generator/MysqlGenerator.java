package com.luckydog.serviceDriverUser.generator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 11:10
 */

/**
 * 自动生成代码工具类
 */

public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8"
                , "root", "root")
                .globalConfig(builder -> {
                    builder.author("LuckyDog").fileOverride().outputDir("D:\\wzy_workspace\\project\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.luckydog.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "D:\\wzy_workspace\\project\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\luckydog\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("car");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
