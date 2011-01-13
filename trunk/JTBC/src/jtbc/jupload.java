/*    */ package jtbc;
/*    */ 
/*    */ import java.io.FileOutputStream;
/*    */ import javax.servlet.ServletInputStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class jupload
/*    */ {
/*    */   public conf conf;
/*    */   private String fileName;
/*    */   private byte[] fileContent;
/* 12 */   private int contentLength = 0;
/*    */ 
/*    */   private int getBufferIndexOf(byte[] paramArrayOfByte, String paramString)
/*    */   {
/* 16 */     int i = -1;
/* 17 */     byte[] arrayOfByte1 = paramArrayOfByte;
/* 18 */     String str = paramString;
/* 19 */     if ((arrayOfByte1 != null) && (!(cls.isEmpty(str).booleanValue())))
/*    */     {
/* 21 */       byte[] arrayOfByte2 = str.getBytes();
/* 22 */       int j = arrayOfByte2.length;
/* 23 */       if (j > 0)
/*    */       {
/* 25 */         int k = 0;
/* 26 */         int l = -1;
/* 27 */         for (int i1 = 0; i1 < arrayOfByte1.length; ++i1)
/*    */         {
/* 29 */           if ((k < j) && (arrayOfByte1[i1] == arrayOfByte2[k]))
/*    */           {
/* 31 */             if (l == -1) l = i1;
/* 32 */             if (k == j - 1) i = l;
/* 33 */             k += 1;
/*    */           }
/*    */           else
/*    */           {
/* 37 */             k = 0;
/* 38 */             l = -1;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 43 */     return i;
/*    */   }
/*    */ 
/*    */   public String getFileName()
/*    */   {
/* 48 */     return this.fileName;
/*    */   }
/*    */ 
/*    */   public int getContentLength()
/*    */   {
/* 53 */     return this.contentLength;
/*    */   }
/*    */ 
/*    */   public Boolean saveAs(String paramString)
/*    */   {
/* 58 */     Boolean localBoolean = Boolean.valueOf(true);
/* 59 */     String str = paramString;
/*    */     try
/*    */     {
/* 62 */       FileOutputStream localFileOutputStream = new FileOutputStream(str);
/* 63 */       localFileOutputStream.write(this.fileContent);
/* 64 */       localFileOutputStream.close();
/*    */     } catch (Exception localException) {
/* 66 */       localBoolean = Boolean.valueOf(false); }
/* 67 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public jupload(conf paramconf, String paramString)
/*    */   {
/* 72 */     this.conf = paramconf;
/* 73 */     String str1 = paramString;
/* 74 */     int i = 0;
/* 75 */     int j = 0;
/* 76 */     int k = this.conf.request.getContentLength();
/* 77 */     if (k <= 0)
/*    */       return;
/* 79 */     byte[] arrayOfByte = new byte[k];
/* 80 */     Integer localInteger = Integer.valueOf(1);
/* 81 */     for (j = 0; j < k; j += i)
/*    */     {
/*    */       try
/*    */       {
/* 85 */         i = this.conf.request.getInputStream().read(arrayOfByte, j, k - j);
/*    */       } catch (Exception localException) {
/* 87 */         localInteger = Integer.valueOf(0); }
/*    */     }
/* 89 */     if (localInteger.intValue() != 1)
/*    */       return;
/* 91 */     String str2 = this.conf.request.getContentType();
/* 92 */     String str3 = new String(arrayOfByte);
/* 93 */     String str4 = cls.getLRStr(str2, "boundary=", "rightr");
/* 94 */     String[] arrayOfString = str3.split(str4);
/* 95 */     for (int l = 0; l < arrayOfString.length; ++l)
/*    */     {
/* 97 */       String str5 = arrayOfString[l];
/* 98 */       if (str5.indexOf("name=") == -1)
/*    */         continue;
/* 100 */       String str6 = cls.getLRStr(cls.getLRStr(str5, "name=\"", "rightr"), "\"", "left");		
/* 101 */       String str7 = cls.getLRStr(cls.getLRStr(str5, "filename=\"", "rightr"), "\"", "left");
/* 102 */       if (!(str6.equals(str1)))
/*    */         continue;
/* 104 */       this.fileName = cls.getLRStr(str7, "\\", "right");
/* 105 */       int i1 = 0;
/* 106 */       i1 = getBufferIndexOf(arrayOfByte, "name=\"" + str6 + "\";");
/* 107 */       for (int i2 = i1; i2 < k; ++i2)
/*    */       {
/* 109 */         if ((arrayOfByte[i2] != 13) || 
/* 111 */           (i2 + 3 >= k) || 
/* 113 */           (arrayOfByte[(i2 + 1)] != 10) || (arrayOfByte[(i2 + 2)] != 13) || (arrayOfByte[(i2 + 3)] != 10))
/*    */           continue;
/* 115 */         i1 = i2 + 4;
/* 116 */         break;
/*    */       }
/*    */ 
/* 121 */       int i2 = 0,i3;
/* 122 */       i2 = getBufferIndexOf(arrayOfByte, "name=\"" + str6 + "\";");
/* 123 */       for (i3 = i2; i3 < k; ++i3)
/*    */       {
/* 125 */         if ((arrayOfByte[i3] != 13) || 
/* 127 */           (i3 + 3 >= k) || 
/* 129 */           (arrayOfByte[(i3 + 1)] != 10) || (arrayOfByte[(i3 + 2)] != 45) || (arrayOfByte[(i3 + 3)] != 45))
/*    */           continue;
/* 131 */         i2 = i3;
/* 132 */         break;
/*    */       }
/*    */ 
/* 137 */       this.contentLength = (i2 - i1);
/* 138 */       if (this.contentLength < 0) this.contentLength = 0;
/* 139 */       if (this.contentLength <= 0)
/*    */         continue;
/* 141 */       this.fileContent = new byte[this.contentLength];
/* 142 */       for (i3 = 0; i3 < this.contentLength; ++i3) this.fileContent[i3] = arrayOfByte[(i1 + i3)];
/*    */     }
/*    */   }
/*    */ }