package com.chason.util;

import com.chason.Application;
import com.chason.service.english.EnglishWordsService;
import com.chason.service.japan.JpFiftyService;
import com.chason.service.japan.JpWordsService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {

    /**
     *
     * @param index File index
     * @param type file type
     *             1: fifty exercise
     *             2: jpWords exercise
     *             3: engWords exercise
     */
    public static void initData (String index, int type) {

        String fileName = null;

        switch (type) {
            case 1:
                fileName = JpFiftyService.PREFIX + index;
                break;
            case 2:
                fileName = JpWordsService.PREFIX +  index;
                break;
            case 3:
                fileName = EnglishWordsService.PRIFIX + index;
                break;
            default:
                break;
        }

        if (StringUtil.isEmpty(fileName)) {
            System.err.println("输入的文件不存在");
            System.exit(-1);
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

                    switch (type) {
                        case 1:
                            JpFiftyService.resolveData(line);
                            break;
                        case 2:
                            JpWordsService.resolveData(line);
                            break;
                        case 3:
                            EnglishWordsService.resolveData(line);
                            break;
                        default:
                            break;

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
