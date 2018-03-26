package com.controller;

import com.common.BaseController;
import com.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Controller
@RequestMapping("/filelist")
public class FileListController extends BaseController {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String list(HttpServletRequest re) {
        ///获取上传文件的目录
        String uploadFilepath = re.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println(uploadFilepath);
        // //存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<>();
        listfile(new File(uploadFilepath), fileNameMap);
        return JsonUtil.maptoJson(fileNameMap);
    }

    public void listfile(File file, Map<String, String> map) {
        //如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            for (File f : files) {
                //递归
                listfile(f, map);
            }
        } else {
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            String old=file.getPath().substring(file.getPath().indexOf("WEB-INF"));
            map.put(file.getName(), realName);
//            map.put(BaseController.string2Json(old), realName);
        }
    }

}
