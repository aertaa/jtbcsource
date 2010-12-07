/*     */ package jtbc.dbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.Statement;
/*     */ import jtbc.cls;
/*     */ 
/*     */ public class access
/*     */   implements dbc
/*     */ {
/*     */   private int rState;
/*     */   private String connStr;
/*     */   private String eMessage;
/*     */ 
/*     */   public access()
/*     */   {
/*   8 */     this.rState = 0;
/*     */ 
/*  10 */     this.connStr = "";
/*     */ 
/*  12 */     this.eMessage = "";
/*     */   }
/*     */ 
/*     */   public void Execute(String paramString) {
/*  16 */     String str1 = paramString;
/*  17 */     if (str1 == null)
/*     */       return;
/*     */     try
/*     */     {
/*  21 */       String str2 = cls.getLRStr(this.connStr, "#", "leftr");
/*  22 */       String str3 = cls.getLRStr(this.connStr, "#", "rightr");
/*  23 */       String str4 = cls.getParameter(str3, "username");
/*  24 */       String str5 = cls.getParameter(str3, "password");
/*  25 */       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
/*  26 */       Connection localConnection = DriverManager.getConnection(str2, str4, str5);
/*  27 */       Statement localStatement = localConnection.createStatement();
/*  28 */       localStatement.executeUpdate(str1);
/*  29 */       localStatement.close();
/*  30 */       localConnection.close();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*  34 */       this.eMessage = localException.getMessage();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setConnStr(String paramString)
/*     */   {
/*  41 */     String str = paramString;
/*  42 */     this.connStr = str;
/*     */   }
/*     */ 
/*     */   public int Executes(String paramString)
/*     */   {
/*  47 */     int i = -1;
/*  48 */     String str1 = paramString;
/*  49 */     if (str1 != null)
/*     */     {
/*     */       try
/*     */       {
/*  53 */         String str2 = cls.getLRStr(this.connStr, "#", "leftr");
/*  54 */         String str3 = cls.getLRStr(this.connStr, "#", "rightr");
/*  55 */         String str4 = cls.getParameter(str3, "username");
/*  56 */         String str5 = cls.getParameter(str3, "password");
/*  57 */         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
/*  58 */         Connection localConnection = DriverManager.getConnection(str2, str4, str5);
/*  59 */         Statement localStatement = localConnection.createStatement();
/*  60 */         i = localStatement.executeUpdate(str1);
/*  61 */         localStatement.close();
/*  62 */         localConnection.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*  66 */         i = -101;
/*  67 */         this.eMessage = localException.getMessage();
/*     */       }
/*     */     }
/*  70 */     return i;
/*     */   }
/*     */ 
/*     */   public int getRState()
/*     */   {
/*  75 */     return this.rState;
/*     */   }
/*     */ 
/*     */   public Object[] getDataAry(String paramString)
/*     */   {
/*  80 */     this.rState = 0;
/*  81 */     String str1 = paramString;
/*  82 */     Object[] arrayOfObject = null;
/*  83 */     if (str1 != null)
/*     */     {
/*     */       try
/*     */       {
/*  87 */         String str2 = cls.getLRStr(this.connStr, "#", "leftr");
/*  88 */         String str3 = cls.getLRStr(this.connStr, "#", "rightr");
/*  89 */         String str4 = cls.getParameter(str3, "username");
/*  90 */         String str5 = cls.getParameter(str3, "password");
/*  91 */         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
/*  92 */         Connection localConnection = DriverManager.getConnection(str2, str4, str5);
/*  93 */         Statement localStatement = localConnection.createStatement();
/*  94 */         ResultSet localResultSet = localStatement.executeQuery(str1);
/*  95 */         ResultSetMetaData localResultSetMetaData = localResultSet.getMetaData();
/*  96 */         int i = localResultSetMetaData.getColumnCount();
/*  97 */         while (localResultSet.next())
/*     */         {
/*  99 */           Object[][] arrayOfObject1 = new Object[i][2];
/* 100 */           for (int j = 0; j < i; ++j)
/*     */           {
/* 102 */             arrayOfObject1[j][0] = localResultSetMetaData.getColumnName(j + 1);
/* 103 */             arrayOfObject1[j][1] = localResultSet.getObject(j + 1);
/*     */           }
/* 105 */           arrayOfObject = cls.mergeAry(arrayOfObject, arrayOfObject1);
/*     */         }
/* 107 */         localResultSet.close();
/* 108 */         localStatement.close();
/* 109 */         localConnection.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 113 */         this.rState = 1;
/* 114 */         arrayOfObject = null;
/* 115 */         this.eMessage = localException.getMessage();
/*     */       }
/*     */     }
/* 118 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public Object getValue(Object[][] paramArrayOfObject, int paramInt)
/*     */   {
/* 124 */     Object[][] arrayOfObject = paramArrayOfObject;
/* 125 */     int i = paramInt;
/* 126 */     Object localObject = arrayOfObject[i][1];
/* 127 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Object getValue(Object[][] paramArrayOfObject, String paramString)
/*     */   {
/* 133 */     Object[][] arrayOfObject = paramArrayOfObject;
/* 134 */     String str = paramString;
/* 135 */     Object localObject = cls.getAryValue(arrayOfObject, str);
/* 136 */     return localObject;
/*     */   }
/*     */ 
/*     */   public String getEMessage()
/*     */   {
/* 141 */     return this.eMessage;
/*     */   }
/*     */ }