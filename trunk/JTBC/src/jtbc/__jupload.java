package jtbc;

import java.io.FileOutputStream;

import com.jspsmart.upload.SmartUpload;

public class __jupload
{
  public conf conf;
  private String fileName;
  private byte[] fileContent;
  private int contentLength = 0;
  private SmartUpload smartuploadobj = new SmartUpload();
  public String getFileName()
  {
	  return this.fileName;
  }

  public int getContentLength()
  {
    return this.contentLength;
  }
  
  public Boolean saveAs(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(true);
    String str = paramString;
    try
    {
    	smartuploadobj.save(str);
    } catch (Exception localException) {
      localBoolean = Boolean.valueOf(false); }
    return localBoolean;
  }
  
  public __jupload(conf paramconf, String paramString)
  {
    this.conf = paramconf;
    String str1 = paramString;
    
	try {
		this.conf.request.setCharacterEncoding("GBK");
		smartuploadobj.initialize(this.conf.config, this.conf.request, this.conf.response);
		smartuploadobj.upload();
		this.contentLength = smartuploadobj.getSize();
		this.fileName =  smartuploadobj.getFiles().getFile(0).getFileName();
		System.out.println(this.fileName);
	}catch (Exception e) {
		
	}
  }
}