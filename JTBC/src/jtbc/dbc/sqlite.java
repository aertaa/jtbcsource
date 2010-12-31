/*     */ package jtbc.dbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.Statement;
/*     */ import jtbc.cls;
/*     */ 
/*     */ public class sqlite
/*     */   implements dbc
/*     */ {
/*     */   private int rState;
/*     */   private String connStr;
/*     */   private String eMessage;
/*     */ 
/*     */   public sqlite()
/*     */   {
/*   8 */     this.rState = 0;
/*     */ 
/*  10 */     this.connStr = "";
/*     */ 
/*  12 */     this.eMessage = "";
/*     */   }
/*     */ 
/*     */   public void Execute(String paramString) {
/*  16 */     String str = paramString;
/*  17 */     if (str == null)
/*     */       return;
/*     */     try
/*     */     {
/*  21 */       Class.forName("org.sqlite.JDBC").newInstance();
/*  22 */       Connection localConnection = DriverManager.getConnection(this.connStr);
/*  23 */       Statement localStatement = localConnection.createStatement();
/*  24 */       localStatement.executeUpdate(str);
/*  25 */       localStatement.close();
/*  26 */       localConnection.close();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*  30 */       this.eMessage = localException.getMessage();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setConnStr(String paramString)
/*     */   {
/*  37 */     String str = paramString;
/*  38 */     this.connStr = str;
/*     */   }
/*     */ 
/*     */   public int Executes(String paramString)
/*     */   {
/*  43 */     int i = -1;
/*  44 */     String str = paramString;
/*  45 */     if (str != null)
/*     */     {
/*     */       try
/*     */       {
/*  49 */         Class.forName("org.sqlite.JDBC").newInstance();
/*  50 */         Connection localConnection = DriverManager.getConnection(this.connStr);
/*  51 */         Statement localStatement = localConnection.createStatement();
/*  52 */         i = localStatement.executeUpdate(str);
/*  53 */         localStatement.close();
/*  54 */         localConnection.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*  58 */         i = -101;
/*  59 */         this.eMessage = localException.getMessage();
/*     */       }
/*     */     }
/*  62 */     return i;
/*     */   }
/*     */ 
/*     */   public int getRState()
/*     */   {
/*  67 */     return this.rState;
/*     */   }
/*     */ 
/*     */   public Object[] getDataAry(String paramString)
/*     */   {
/*  72 */     this.rState = 0;
/*  73 */     String str = paramString;
/*  74 */     Object[] arrayOfObject = null;
/*  75 */     if (str != null)
/*     */     {
/*     */       try
/*     */       {
/*  79 */         Class.forName("org.sqlite.JDBC").newInstance();
/*  80 */         Connection localConnection = DriverManager.getConnection(this.connStr);
/*  81 */         Statement localStatement = localConnection.createStatement();
/*  82 */         ResultSet localResultSet = localStatement.executeQuery(str);
/*  83 */         ResultSetMetaData localResultSetMetaData = localResultSet.getMetaData();
/*  84 */         int i = localResultSetMetaData.getColumnCount();
/*  85 */         while (localResultSet.next())
/*     */         {
/*  87 */           Object[][] arrayOfObject1 = new Object[i][2];
/*  88 */           for (int j = 0; j < i; ++j)
/*     */           {
/*  90 */             arrayOfObject1[j][0] = localResultSetMetaData.getColumnName(j + 1);
/*  91 */             arrayOfObject1[j][1] = localResultSet.getObject(j + 1);
/*     */           }
/*  93 */           arrayOfObject = cls.mergeAry(arrayOfObject, arrayOfObject1);
/*     */         }
/*  95 */         localResultSet.close();
/*  96 */         localStatement.close();
/*  97 */         localConnection.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 101 */         this.rState = 1;
/* 102 */         arrayOfObject = null;
/* 103 */         this.eMessage = localException.getMessage();
/*     */       }
/*     */     }
/* 106 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public Object getValue(Object[][] paramArrayOfObject, int paramInt)
/*     */   {
/* 112 */     Object[][] arrayOfObject = paramArrayOfObject;
/* 113 */     int i = paramInt;
/* 114 */     Object localObject = arrayOfObject[i][1];
/* 115 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Object getValue(Object[][] paramArrayOfObject, String paramString)
/*     */   {
/* 121 */     Object[][] arrayOfObject = paramArrayOfObject;
/* 122 */     String str = paramString;
/* 123 */     Object localObject = cls.getAryValue(arrayOfObject, str);
/* 124 */     return localObject;
/*     */   }
/*     */ 
/*     */   public String getEMessage()
/*     */   {
/* 129 */     return this.eMessage;
/*     */   }
/*     */ }