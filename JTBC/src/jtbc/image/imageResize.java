/*     */ package jtbc.image;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public class imageResize
/*     */ {
/*     */   private int iWidth;
/*     */   private int iHeight;
/*     */   private int iScaleWidth;
/*     */   private int iDotsA;
/*     */   private int iDotsB;
/*     */   private double[] iContribA;
/*     */   private double[] iContribB;
/*     */   private double[] iContribC;
/*     */ 
/*     */   public Boolean saveImageAsJPEG(String paramString1, String paramString2, int paramInt1, int paramInt2)
/*     */   {
/*  17 */     Boolean localBoolean = Boolean.valueOf(false);
/*  18 */     String str1 = paramString1;
/*  19 */     String str2 = paramString2;
/*  20 */     int i = paramInt1;
/*  21 */     int j = paramInt2;
/*  22 */     localBoolean = saveImageAsJPEG(str1, str2, i, j, 1);
/*  23 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean saveImageAsJPEG(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  28 */     Boolean localBoolean = Boolean.valueOf(false);
/*     */     try
/*     */     {
/*  31 */       String str1 = paramString1;
/*  32 */       String str2 = paramString2;
/*  33 */       int i = paramInt1;
/*  34 */       int j = paramInt2;
/*  35 */       int k = paramInt3;
/*     */ 
/*  37 */       File localFile1 = new File(str1);
/*  38 */       File localFile2 = new File(str2);
/*  39 */       BufferedImage localBufferedImage = ImageIO.read(localFile1);
/*  40 */       int l = i;
/*  41 */       int i1 = j;
/*  42 */       if (k == 1)
/*     */       {
/*  44 */         int i2 = localBufferedImage.getWidth(null);
/*  45 */         int i3 = localBufferedImage.getHeight(null);
/*  46 */         if ((i2 > 0) && (i3 > 0))
/*     */         {
/*  48 */           if (i2 / i3 >= i / j)
/*     */           {
/*  50 */             if (i2 > i)
/*     */             {
/*  52 */               l = i;
/*  53 */               i1 = i3 * i / i2;
/*     */             }
/*     */             else
/*     */             {
/*  57 */               l = i2;
/*  58 */               i1 = i3;
/*     */             }
/*     */ 
/*     */           }
/*  63 */           else if (i3 > j)
/*     */           {
/*  65 */             l = i2 * j / i3;
/*  66 */             i1 = j;
/*     */           }
/*     */           else
/*     */           {
/*  70 */             l = i2;
/*  71 */             i1 = i3;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  76 */       localBufferedImage = imageZoomOut(localBufferedImage, l, i1);
/*  77 */       ImageIO.write(localBufferedImage, "JPEG", localFile2);
/*  78 */       localBoolean = Boolean.valueOf(true);
/*     */     } catch (Exception localException) {
/*     */     }
/*  81 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   private BufferedImage imageZoomOut(BufferedImage paramBufferedImage, int paramInt1, int paramInt2)
/*     */   {
/*  86 */     int i = paramInt1;
/*  87 */     int j = paramInt2;
/*  88 */     BufferedImage localBufferedImage1 = paramBufferedImage;
/*  89 */     this.iWidth = localBufferedImage1.getWidth();
/*  90 */     this.iHeight = localBufferedImage1.getHeight();
/*  91 */     this.iScaleWidth = i;
/*  92 */     callContrib();
/*  93 */     BufferedImage localBufferedImage2 = horizontalFiltering(localBufferedImage1, i);
/*  94 */     BufferedImage localBufferedImage3 = verticalFiltering(localBufferedImage2, j);
/*  95 */     return localBufferedImage3;
/*     */   }
/*     */ 
/*     */   private double lanczos(int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/* 100 */     int i = paramInt1;
/* 101 */     int j = paramInt2;
/* 102 */     int k = paramInt3;
/* 103 */     double d1 = paramDouble;
/*     */ 
/* 105 */     double d3 = 3.14159265358978D;
/* 106 */     double d2 = i * k / j;
/* 107 */     d2 = Math.sin(d2 * d3) / d2 * d3 * Math.sin(d2 * d3 / d1) / d2 * d3 / d1;
/* 108 */     return d2;
/*     */   }
/*     */ 
/*     */   private void callContrib()
/*     */   {
/* 113 */     double d1 = 3.0D;
/* 114 */     this.iDotsB = (int)(this.iWidth * d1 / this.iScaleWidth);
/* 115 */     this.iDotsA = (this.iDotsB * 2 + 1);
/*     */     try
/*     */     {
/* 118 */       this.iContribA = new double[this.iDotsA];
/* 119 */       this.iContribB = new double[this.iDotsA];
/* 120 */       this.iContribC = new double[this.iDotsA]; } catch (Exception localException) {
/*     */     }
/* 122 */     int i = this.iDotsB,j;
/* 123 */     this.iContribA[i] = 1.0D;
/* 124 */     double d2 = 0.0D;
/* 125 */     for (j = 1; j <= i; ++j)
/*     */     {
/* 127 */       this.iContribA[(i + j)] = lanczos(j, this.iWidth, this.iScaleWidth, d1);
/* 128 */       d2 += this.iContribA[(i + j)];
/*     */     }
/* 130 */     for (j = i - 1; j >= 0; --j)
/*     */     {
/* 132 */       this.iContribA[j] = this.iContribA[(i * 2 - j)];
/*     */     }
/* 134 */     d2 = d2 * 2.0D + 1.0D;
/* 135 */     for (j = 0; j <= i; ++j)
/*     */     {
/* 137 */       this.iContribB[j] = (this.iContribA[j] / d2);
/*     */     }
/* 139 */     for (j = i + 1; j < this.iDotsA; ++j)
/*     */     {
/* 141 */       this.iContribB[j] = this.iContribB[(i * 2 - j)];
/*     */     }
/*     */   }
/*     */ 
/*     */   private void calContribB(int paramInt1, int paramInt2)
/*     */   {
/* 147 */     double d = 0.0D;
/* 148 */     int i = paramInt1;
/* 149 */     int j = paramInt2;
			  int k;
/* 150 */     for (k = i; k <= j; ++k)
/*     */     {
/* 152 */       d += this.iContribA[k];
/*     */     }
/* 154 */     for (k = i; k <= j; ++k)
/*     */     {
/* 156 */       this.iContribC[k] = (this.iContribA[k] / d);
/*     */     }
/*     */   }
/*     */ 
/*     */   private int getRedValue(int paramInt)
/*     */   {
/* 162 */     int i = paramInt;
/* 163 */     int j = i & 0xFF0000;
/* 164 */     j >>= 16;
/* 165 */     return j;
/*     */   }
/*     */ 
/*     */   private int getGreenValue(int paramInt)
/*     */   {
/* 170 */     int i = paramInt;
/* 171 */     int j = i & 0xFF00;
/* 172 */     j >>= 8;
/* 173 */     return j;
/*     */   }
/*     */ 
/*     */   private int getBlueValue(int paramInt)
/*     */   {
/* 178 */     int i = paramInt;
/* 179 */     int j = i & 0xFF;
/* 180 */     return j;
/*     */   }
/*     */ 
/*     */   private int comRGB(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 185 */     int i = 0;
/* 186 */     int j = paramInt1;
/* 187 */     int k = paramInt2;
/* 188 */     int l = paramInt3;
/* 189 */     i = (j << 16) + (k << 8) + l;
/* 190 */     return i;
/*     */   }
/*     */ 
/*     */   private int horizontalFilter(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, double[] paramArrayOfDouble)
/*     */   {
/* 195 */     int i = paramInt5;
/* 196 */     int j = 0;
/* 197 */     int k = paramInt1;
/* 198 */     int l = paramInt2;
/* 199 */     int i1 = paramInt3;
/* 200 */     int i2 = paramInt4;
/* 201 */     double[] arrayOfDouble = paramArrayOfDouble;
/* 202 */     BufferedImage localBufferedImage = paramBufferedImage;
/* 203 */     double d1 = 0.0D;
/* 204 */     double d2 = 0.0D;
/* 205 */     double d3 = 0.0D;
/*     */ 
/* 207 */     int i3 = k; for (int i4 = i1; i3 <= l; ++i4)
/*     */     {
/* 209 */       j = localBufferedImage.getRGB(i3, i);
/* 210 */       d1 += getRedValue(j) * arrayOfDouble[i4];
/* 211 */       d2 += getGreenValue(j) * arrayOfDouble[i4];
/* 212 */       d3 += getBlueValue(j) * arrayOfDouble[i4];
/*     */ 
/* 207 */       ++i3;
/*     */     }
/*     */ 
/* 214 */     j = comRGB(clip((int)d1), clip((int)d2), clip((int)d3));
/* 215 */     return j;
/*     */   }
/*     */ 
/*     */   private BufferedImage horizontalFiltering(BufferedImage paramBufferedImage, int paramInt)
/*     */   {
/* 220 */     int i = paramInt;
/* 221 */     BufferedImage localBufferedImage1 = paramBufferedImage;
/* 222 */     int j = localBufferedImage1.getWidth();
/* 223 */     int k = localBufferedImage1.getHeight();
/* 224 */     int l = 0;
/* 225 */     BufferedImage localBufferedImage2 = new BufferedImage(i, k, 1);
/*     */ 
/* 227 */     for (int i1 = 0; i1 < i; ++i1)
/*     */     {
/*     */       int i3;
/*     */       int i6;
/* 231 */       int i4 = (int)(i1 * j / i + 0.5D);
/* 232 */       int i5 = 0;
/*     */ 
/* 234 */       int i2 = i4 - this.iDotsB;
/* 235 */       if (i2 < 0)
/*     */       {
/* 237 */         i2 = 0;
/* 238 */         i3 = this.iDotsB - i4;
/*     */       }
/*     */       else
/*     */       {
/* 242 */         i3 = 0;
/*     */       }
/*     */ 
/* 245 */       int i7 = i4 + this.iDotsB;
/* 246 */       if (i7 > j - 1)
/*     */       {
/* 248 */         i7 = j - 1;
/* 249 */         i6 = this.iDotsB + j - 1 - i4;
/*     */       }
/*     */       else
/*     */       {
/* 253 */         i6 = this.iDotsB * 2;
/*     */       }
/*     */ 
/* 256 */       if ((i3 > 0) || (i6 < this.iDotsA - 1))
/*     */       {
/* 258 */         calContribB(i3, i6);
/* 259 */        label267:
				 for (i5 = 0; ; ++i5) { 
						if (i5 >= k)
/*     */             break label267;
/* 261 */           l = horizontalFilter(localBufferedImage1, i2, i7, i3, i6, i5, this.iContribC);
/* 262 */           localBufferedImage2.setRGB(i1, i5, l);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 267 */       for (i5 = 0; i5 < k; ++i5)
/*     */       {
/* 269 */         l = horizontalFilter(localBufferedImage1, i2, i7, i3, i6, i5, this.iContribB);
/* 270 */         localBufferedImage2.setRGB(i1, i5, l);
/*     */       }
/*     */     }
/*     */ 
/* 274 */     return localBufferedImage2;
/*     */   }
/*     */ 
/*     */   private int verticalFilter(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, double[] paramArrayOfDouble)
/*     */   {
/* 279 */     int i = paramInt5;
/* 280 */     int j = 0;
/* 281 */     int k = paramInt1;
/* 282 */     int l = paramInt2;
/* 283 */     int i1 = paramInt3;
/* 284 */     int i2 = paramInt4;
/* 285 */     double[] arrayOfDouble = paramArrayOfDouble;
/* 286 */     BufferedImage localBufferedImage = paramBufferedImage;
/* 287 */     double d1 = 0.0D;
/* 288 */     double d2 = 0.0D;
/* 289 */     double d3 = 0.0D;
/*     */ 
/* 291 */     int i3 = k; for (int i4 = i1; i3 <= l; ++i4)
/*     */     {
/* 293 */       j = localBufferedImage.getRGB(i, i3);
/* 294 */       d1 += getRedValue(j) * arrayOfDouble[i4];
/* 295 */       d2 += getGreenValue(j) * arrayOfDouble[i4];
/* 296 */       d3 += getBlueValue(j) * arrayOfDouble[i4];
/*     */ 
/* 291 */       ++i3;
/*     */     }
/*     */ 
/* 298 */     j = comRGB(clip((int)d1), clip((int)d2), clip((int)d3));
/* 299 */     return j;
/*     */   }
/*     */ 
/*     */   private BufferedImage verticalFiltering(BufferedImage paramBufferedImage, int paramInt)
/*     */   {
/* 304 */     int i = paramInt;
/* 305 */     BufferedImage localBufferedImage1 = paramBufferedImage;
/* 306 */     int j = localBufferedImage1.getWidth();
/* 307 */     int k = localBufferedImage1.getHeight();
/* 308 */     int l = 0;
/* 309 */     BufferedImage localBufferedImage2 = new BufferedImage(j, i, 1);
/* 310 */     for (int i1 = 0; i1 < i; ++i1)
/*     */     {
/*     */       int i3;
/*     */       int i5;
/*     */       int i7;
/* 314 */       int i4 = (int)(i1 * k / i + 0.5D);
/* 315 */       int i2 = i4 - this.iDotsB;
/* 316 */       if (i2 < 0)
/*     */       {
/* 318 */         i2 = 0;
/* 319 */         i3 = this.iDotsB - i4;
/*     */       }
/*     */       else
/*     */       {
/* 323 */         i3 = 0;
/*     */       }
/*     */ 
/* 326 */       int i6 = i4 + this.iDotsB;
/* 327 */       if (i6 > k - 1)
/*     */       {
/* 329 */         i6 = k - 1;
/* 330 */         i5 = this.iDotsB + k - 1 - i4;
/*     */       }
/*     */       else
/*     */       {
/* 334 */         i5 = this.iDotsB * 2;
/*     */       }
/*     */ 
/* 337 */       if ((i3 > 0) || (i5 < this.iDotsA - 1))
/*     */       {
/* 339 */         calContribB(i3, i5);
/* 340 */         for (i7 = 0; i7 < j; ++i7)
/*     */         {
/* 342 */           l = verticalFilter(localBufferedImage1, i2, i6, i3, i5, i7, this.iContribC);
/* 343 */           localBufferedImage2.setRGB(i7, i1, l);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 348 */         for (i7 = 0; i7 < j; ++i7)
/*     */         {
/* 350 */           l = verticalFilter(localBufferedImage1, i2, i6, i3, i5, i7, this.iContribB);
/* 351 */           localBufferedImage2.setRGB(i7, i1, l);
/*     */         }
/*     */       }
/*     */     }
/* 355 */     return localBufferedImage2;
/*     */   }
/*     */ 
/*     */   private int clip(int paramInt)
/*     */   {
/* 360 */     int i = paramInt;
/* 361 */     int j = 220;
/* 362 */     if (i < 0) j = 0;
/* 363 */     else if (i > 255) j = 255;
/*     */     else j = i;
/* 365 */     return j;
/*     */   }
/*     */ }