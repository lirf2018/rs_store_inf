package com.yufan.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 功能名称: 图片处理类
 * 开发人: lirf
 * 开发时间: 2015下午9:37:58
 * 其它说明：
 */
public class ImageUtil {

    private static String filePath = "";

    public static void main(String[] args) {
        String path = ImageUtil.class.getResource("/").getPath();
        System.out.println(path);

    }

    /**
     * 将文件存到本地
     */
    public String uploadFileToLocalhost(File file) {
        try {
            //E:\tomcat\apache-tomcat-8.0.15\webapps\rs_store_manage
//            String path=request.getSession().getServletContext().getRealPath("/");
            String path = ImageUtil.class.getResource("/").getPath();
            String p[] = path.split("webapps");

            // 文件按 年/月 目录保存
            String date = DatetimeUtil.getNow("yyyy-MM-dd");
            String dates[] = date.split("-");
            //图片根文件夹
            String root = p[0] + "webapps/image/";
            String img = dates[0] + "/" + dates[1] + "/";
            String filename = DatetimeUtil.getNow("yyyyMMdd") + System.currentTimeMillis() + ".jpg";
            String filePath = img + filename;
            System.out.println("-------->" + root + img);
            System.out.println("-------->" + filePath);
            //生成文件
            ImageUtil.getFile(ImageUtil.getBytes(file), root + img, filename);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据byte数组，生成文件
     *
     * @param bfile
     * @param filePath
     * @param fileName
     */
    public static boolean getFile(byte[] bfile, String filePath, String fileName) {
        try {
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            File file = null;
            try {
                File dir = new File(filePath);
                if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
                    dir.mkdirs();
                }
                file = new File(filePath + "/" + fileName);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bfile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 根据 InputStream 数组，生成文件
     *
     * @param in
     * @param filePath
     * @param fileName
     */
    public static void getFile(InputStream in, String filePath, String fileName) {
        try {
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            File file = null;
            try {
                File dir = new File(filePath);
                if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                    dir.mkdirs();
                }
                file = new File(filePath + "/" + fileName);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                byte[] buffer = new byte[1024];
                int len = 0;
                // 从数据库中读取到指定的字节数组中
                while ((len = in.read(buffer)) != -1) {
                    // 从指定的数组中读取，然后输出来，
                    // 所以这里buffer好象是连接inputStream和outputStream的一个东西
                    bos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获得指定文件的byte数组
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    // 获得指定文件的byte数组
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                // delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    // //删除文件夹
    // //param folderPath 文件夹完整绝对路径
    // public static void delFolder(String folderPath) {
    // try {
    // delAllFile(folderPath); //删除完里面所有内容
    // String filePath = folderPath;
    // filePath = filePath.toString();
    // java.io.File myFilePath = new java.io.File(filePath);
    // myFilePath.delete(); //删除空文件夹
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * Blob写文件
     *
     * @param filePath
     * @param blob
     */
    static void getBlobToFile(String filePath, java.sql.Blob blob) {
        InputStream ins = null;
        OutputStream fout = null;
        try {
            ins = blob.getBinaryStream();
            //输出到文件
            File file = new File(filePath);
            fout = new FileOutputStream(file);
            //将BLOB数据写入文件
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = ins.read(b)) != -1) {
                fout.write(b, 0, len);
            }
            fout.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
