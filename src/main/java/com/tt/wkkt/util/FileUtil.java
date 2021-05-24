package com.tt.wkkt.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class FileUtil {
    public static void createFile(HttpServletResponse response, HSSFWorkbook workbook){
        String fileName="学生成绩表";
        try{
            // 捕获内存缓冲区的数据，转换成字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            // 获取内存缓冲中的数据
            byte[] content = out.toByteArray();
            // 将字节数组转化为输入流
            InputStream in = new ByteArrayInputStream(content);
            //通过调用reset（）方法可以重新定位。
            response.reset();
            // 如果文件名是英文名不需要加编码格式，如果是中文名需要添加"iso-8859-1"防止乱码
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            response.addHeader("Content-Length", "" + content.length);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin","*");/*必须要有这行，不然会出现跨域出错的问题*/
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
