package com.chason.util;

import com.chason.Application;
import com.chason.service.FiftyService;
import com.chason.service.WordsService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {

    /**
     *
     * @param index File index
     * @param type file type 1: fifty exercise 2: words exercise
     */
    public static void initData (String index, int type) {

        String fileName = null;

        if (type == 1) {
            fileName = FiftyService.PREFIX + index;
        } else if (type == 2) {
            fileName = WordsService.PREFIX +  index;
        }

        if (StringUtil.isEmpty(fileName)) {
            System.err.println("输入的文件不存在");
            System.exit(0);
        }

        String file = null;
        URL url = null;

        try {
            url = Application.class.getClassLoader().getResource(fileName);
            file = url.getPath();
        } catch (Exception e) {
            System.err.println("文件不存在！");
            System.exit(-1);
        }

        if (StringUtil.isEmpty(file)) {
            System.err.println("文件不存在！");
            System.exit(-1);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (CheckUtil.checkLine(line)) {
                    if (type == 1) {
                        FiftyService.resolveData(line);
                    } else {
                        WordsService.resolveData(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
