/*     */ package jtbc;
/*     */ 
/*     */ import javax.servlet.ServletContext;
/*     */ import jtbc.dbc.dbc;
/*     */ 
/*     */ public class dbcache
/*     */ {
/*     */   public conf conf;
/*     */ 
/*     */   public void updateCache(String paramString, Object[][] paramArrayOfObject)
/*     */   {
/*  11 */     String str = cls.getString(paramString);
/*  12 */     Object[][] arrayOfObject1 = paramArrayOfObject;
/*  13 */     if (arrayOfObject1 == null)
/*     */       return;
/*  15 */     Object[] arrayOfObject2 = null;
/*  16 */     Object[] arrayOfObject3 = getAppArys(str);
/*  17 */     Integer localInteger = cls.getNum(this.conf.jt.itake("global.config.dbcache-" + str + "-topx", "cfg"), Integer.valueOf(0));
/*  18 */     if (localInteger.intValue() == 0) localInteger = cls.getNum(this.conf.jt.itake("global.config.dbcache-topx", "cfg"), Integer.valueOf(0));
/*  19 */     if (localInteger.intValue() == 0) localInteger = Integer.valueOf(1000);
/*  20 */     arrayOfObject2 = cls.mergeAry(arrayOfObject2, arrayOfObject1);
/*  21 */     if (arrayOfObject3 != null)
/*     */     {
/*  23 */       for (int i = 0; i < arrayOfObject3.length; ++i)
/*     */       {
/*  25 */         Object[][] arrayOfObject4 = (Object[][])(Object[][])arrayOfObject3[i];
/*  26 */         if (arrayOfObject4 != arrayOfObject1) arrayOfObject2 = cls.mergeAry(arrayOfObject2, arrayOfObject4);
/*  27 */         if (i >= localInteger.intValue() - 1) break;
/*     */       }
/*     */     }
/*  30 */     if (!(this.conf.isApp.equals("1"))) return; this.conf.application.setAttribute(this.conf.getAppKey("dbcache-" + str), arrayOfObject2);
/*     */   }
/*     */ 
/*     */   public void deleteCache(String paramString1, String paramString2, Integer paramInteger)
/*     */   {
/*  36 */     String str1 = cls.getString(paramString1);
/*  37 */     String str2 = cls.getString(paramString2);
/*  38 */     Integer localInteger1 = paramInteger;
/*  39 */     Object[] arrayOfObject1 = getAppArys(str1);
/*  40 */     if (arrayOfObject1 == null)
/*     */       return;
/*  42 */     Integer localInteger2 = Integer.valueOf(0);
/*  43 */     Object[] arrayOfObject2 = null;
/*  44 */     for (int i = 0; i < arrayOfObject1.length; ++i)
/*     */     {
/*  46 */       Object[][] arrayOfObject = (Object[][])(Object[][])arrayOfObject1[i];
/*  47 */       for (int j = 0; j < arrayOfObject.length; ++j)
/*     */       {
/*  49 */         if (!(cls.cfnames(str2, "id").equals((String)arrayOfObject[j][0])))
/*     */           continue;
/*  51 */         if (localInteger1.equals((Integer)arrayOfObject[j][1])) localInteger2 = Integer.valueOf(1);
/*     */         else arrayOfObject2 = cls.mergeAry(arrayOfObject2, arrayOfObject);
/*     */       }
/*     */     }
/*     */ 
/*  56 */     if ((localInteger2.intValue() != 1) || 
/*  58 */       (!(this.conf.isApp.equals("1")))) return; this.conf.application.setAttribute(this.conf.getAppKey("dbcache-" + str1), arrayOfObject2);
/*     */   }
/*     */ 
/*     */   public Object[][] getAry(String paramString1, String paramString2, Integer paramInteger)
/*     */   {
/*  65 */     Object localObject = (Object[][])null;
/*  66 */     String str1 = cls.getString(paramString1);
/*  67 */     String str2 = cls.getString(paramString2);
/*  68 */     Integer localInteger = paramInteger;
/*  69 */     Object[] arrayOfObject = getAppArys(str1);
/*  70 */     if (arrayOfObject != null)
/*     */     {
/*  72 */       for (int i = 0; i < arrayOfObject.length; ++i)
/*     */       {
/*  74 */         Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[i];
/*  75 */         for (int j = 0; j < arrayOfObject1.length; ++j)
/*     */         {
/*  77 */           if ((!(cls.cfnames(str2, "id").equals((String)arrayOfObject1[j][0]))) || 
/*  79 */             (!(localInteger.equals((Integer)arrayOfObject1[j][1]))))
/*     */             continue;
/*  81 */           localObject = arrayOfObject1;
/*  82 */           break;
/*     */         }
/*     */ 
/*  86 */         if (localObject != null) break;
/*     */       }
/*     */     }
/*  89 */     if (localObject == null) localObject = getDBAry(str1, str2, localInteger);
/*  90 */     updateCache(str1, (Object[][])localObject);
/*  91 */     return ((Object[][])localObject);
/*     */   }
/*     */ 
/*     */   public Object[][] getDBAry(String paramString1, String paramString2, Integer paramInteger)
/*     */   {
/*  96 */     Object[][] arrayOfObject = (Object[][])null;
/*  97 */     String str1 = cls.getString(paramString1);
/*  98 */     String str2 = cls.getString(paramString2);
/*  99 */     Integer localInteger = paramInteger;
/* 100 */     dbc localdbc = db.newInstance(this.conf);
/* 101 */     String str3 = "select * from " + str1 + " where " + cls.cfnames(str2, "id") + "=" + localInteger;
/* 102 */     Object[] arrayOfObject1 = localdbc.getDataAry(str3);
/* 103 */     if (arrayOfObject1 != null) arrayOfObject = (Object[][])(Object[][])arrayOfObject1[0];
/* 104 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public Object[] getAppArys(String paramString)
/*     */   {
/* 109 */     Object[] arrayOfObject = null;
/* 110 */     String str = cls.getString(paramString);
/* 111 */     if (!(cls.isEmpty(str).booleanValue())) arrayOfObject = (Object[])(Object[])this.conf.application.getAttribute(this.conf.getAppKey("dbcache-" + str));
/* 112 */     //System.out.println(this.conf.getAppKey("dbcache-" + str));
			  return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public Object getValue(String paramString1, String paramString2, Integer paramInteger, String paramString3)
/*     */   {
/* 117 */     Object localObject = null;
/* 118 */     String str1 = cls.getString(paramString1);
/* 119 */     String str2 = cls.getString(paramString2);
/* 120 */     Integer localInteger = paramInteger;
/* 121 */     String str3 = cls.getString(paramString3);
/* 122 */     Object[][] arrayOfObject = getAry(str1, str2, localInteger);
/* 123 */     localObject = cls.getAryValue(arrayOfObject, str3);
/* 124 */     return localObject;
/*     */   }
/*     */ 
/*     */   public dbcache(conf paramconf)
/*     */   {
/* 129 */     this.conf = paramconf;
/*     */   }
/*     */ }