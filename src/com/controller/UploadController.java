package com.controller;

import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping(value = "/toupload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject toUpload(HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("text/plain;charset=UTF-8");
        String flag="";
        String info="";
        //获取文件要保存的目录
        String savepath = req.getServletContext().getRealPath("/WEB-INF/upload");

        //上传时生成临时文件保存目录
        String tempPath = req.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        File saveFile = new File(savepath);
        //判断文件保存目录是否存在
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        //判断临时目录是否存在，如不存在则创建。
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }
        String message = "";
        try {
            //使用apache 文件上传组件处理文件上传步骤
            //一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long pBytesRead, long pContentLength, int i) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //解决文件上传的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(req)) {
//                return flag="上传文件失败！";
                return JSONObject.fromObject("{\"error\":\"上传文件失败\"}");
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                } else {
                    //如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if (null == filename || filename.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是：" + fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = makeFileName(filename);
                    //得到文件的保存目录
                    String realSavePath = makePaht(saveFilename, savepath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    flag = "文件上传成功！";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
           flag="单个文件超出最大值！！！";
            return JSONObject.fromObject("{\"error\":\"单个文件超出最大值\"}");
        } catch (FileUploadBase.SizeLimitExceededException e) {

            e.printStackTrace();
            req.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
//            req.getRequestDispatcher("/message.jsp").forward(req, resp);
//            return flag="上传文件的总的大小超出限制的最大值！！！";
            return JSONObject.fromObject("{\"error\":\"上传文件的总的大小超出限制的最大值\"}");
        } catch (Exception e) {
            flag = "文件上传失败！";

            e.printStackTrace();
        }
        req.setAttribute("message", message);
        return JSONObject.fromObject("{\"error\":\"\"}");
    }

    /**
     * 146     * @Method: makeFileName
     * 147     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * <p>
     * 149     * @param filename 文件的原始名称
     * 150     * @return uuid+"_"+文件的原始名称
     * 151
     */
    private String makeFileName(String filename) {
        return UUID.randomUUID().toString().replace("-", "") + "_" + filename;
    }

    /**
     * 158      * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * 159     * @Method: makePath
     * 163     * @param filename 文件名，要根据文件名生成存储目录
     * 164     * @param savePath 文件存储路径
     * 165     * @return 新的存储目录
     * 166
     */
    private String makePaht(String filename, String savePath) {
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }


}
