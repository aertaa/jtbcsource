package jtbc;

import java.io.FileOutputStream;

public class jupload
{
  public conf conf;
  private String fileName;
  private byte[] fileContent;
  private int contentLength = 0;
  /**
   * 返回参照字符串在被参照数组中第一次完全匹配的位置
   * @param paramArrayOfByte	被参照的数据数组,字节数组
   * @param paramString			参照的字符串
   * @return int				
   */
  private int getBufferIndexOf(byte[] paramArrayOfByte, String paramString)
  {
    int i = -1;
    byte[] arrayOfByte1 = paramArrayOfByte;
    String str = paramString;
    if ((arrayOfByte1 != null) && (!(cls.isEmpty(str).booleanValue())))
    {
    	byte[] arrayOfByte2 = str.getBytes();//参照字符串对应的字节数组
    	int j = arrayOfByte2.length;//参照字符串的长度
    	if (j > 0)
    	{
        int k = 0;
        int l = -1;
        for (int i1 = 0; i1 < arrayOfByte1.length; ++i1)
        {
          if ((k < j) && (arrayOfByte1[i1] == arrayOfByte2[k]))
          {
            if (l == -1) l = i1;
            if (k == j - 1) i = l;
            k += 1;
          }
          else
          {//不匹配的情况为,参照字符串长度过长或者对应字符内容不匹配
            k = 0;//不匹配,则从0开始计算参照字符串数组的位置
            l = -1;
          }
        }
      }
    }
    return i;
  }
  
  public String getFileName()
  {
	  return this.fileName;
  }

  public int getContentLength()
  {
    return this.contentLength;
  }
  /**
   * 输出文件
   * @param paramString 路径
   * @return
   */
  public Boolean saveAs(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(true);
    String str = paramString;
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(str);
      localFileOutputStream.write(this.fileContent);
      localFileOutputStream.close();
    } catch (Exception localException) {
      localBoolean = Boolean.valueOf(false); }
    return localBoolean;
  }
  /**
   * 上传文件的构造函数
   * 支持多文件上传
   * @param paramconf		
   * @param paramString		name属性对应的名称,例如name="zhang.xml"中的zhang.xml
   */
  public jupload(conf paramconf, String paramString)
  {  
	this.conf = paramconf;
    String str1 = paramString;
    int i = 0;
    int j = 0;
    int k = this.conf.request.getContentLength();//文件上传的全部内容
    if (k <= 0)
      return;
    byte[] arrayOfByte = new byte[k];
    Integer localInteger = Integer.valueOf(1);
    for (j = 0; j < k; j += i)
    {
      try
      {
        i = this.conf.request.getInputStream().read(arrayOfByte, j, k - j);//多次读取数组,保证文件内容全部获得
      } catch (Exception localException) {
        localInteger = Integer.valueOf(0); }
    }
    if (localInteger.intValue() != 1)
      return;
    String str2 = this.conf.request.getContentType();//文件类型
    String str3="";
    try
    {
    	str3 = new String(arrayOfByte,this.conf.charset);//数组转换为字符串
    }catch (Exception localException) {    	
    }
    String str4 = cls.getLRStr(str2, "boundary=", "rightr");//边界属性后面全部的内容,即划分边界的分隔符
    String[] arrayOfString = str3.split(str4);//按照边界拆分后的数组
   
    for (int l = 0; l < arrayOfString.length; ++l)
    {
      String str5 = arrayOfString[l];     
      if (str5.indexOf("name=") == -1)
        continue;
      String str6 = cls.getLRStr(cls.getLRStr(str5, "name=\"", "rightr"), "\"", "left");//name属性
      String str7 = cls.getLRStr(cls.getLRStr(str5, "filename=\"", "rightr"), "\"", "left");//fileName属性
      if (!(str6.equals(str1)))
        continue;
      this.fileName = cls.getLRStr(str7, "\\", "right");//获取并设置文件名称
      int i1 = 0;
      i1 = getBufferIndexOf(arrayOfByte, "name=\"" + str6 + "\";");
      for (int i2 = i1; i2 < k; ++i2)
      {
        if ((arrayOfByte[i2] != 13) || 
          (i2 + 3 >= k) || 
          (arrayOfByte[(i2 + 1)] != 10) || (arrayOfByte[(i2 + 2)] != 13) || (arrayOfByte[(i2 + 3)] != 10))
          continue;
        i1 = i2 + 4;//i1取值为在文件内容范围内的第一个(第一个字符为13，第二个字符为10，第三个字符为13，第四个字符为10)+4的位置
        break;
      }

      int i2 = 0,i3;
      i2 = getBufferIndexOf(arrayOfByte, "name=\"" + str6 + "\";");
      for (i3 = i2; i3 < k; ++i3)
      {
        if ((arrayOfByte[i3] != 13) || 
          (i3 + 3 >= k) || 
          (arrayOfByte[(i3 + 1)] != 10) || (arrayOfByte[(i3 + 2)] != 45) || (arrayOfByte[(i3 + 3)] != 45))
          continue;
        i2 = i3;//i2取值为在文件内容范围内的第一个(第一个字符为13，第二个字符为10，第三个字符为45，第四个字符为45)的位置
        break;
      }

      this.contentLength = (i2 - i1);
      if (this.contentLength < 0) this.contentLength = 0;//设置contentLength
      if (this.contentLength <= 0)
        continue;
      this.fileContent = new byte[this.contentLength];
      for (i3 = 0; i3 < this.contentLength; ++i3) this.fileContent[i3] = arrayOfByte[(i1 + i3)];//将文件内容复制到数组中
    }
  }
}