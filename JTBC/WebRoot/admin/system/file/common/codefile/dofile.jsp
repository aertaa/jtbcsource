<%@ page import = "jtbc.*" %>
<%@ page import = "jtbc.dbc.*" %>
<%@ page import = "java.io.File" %>
<%
class module1 extends jpage
{	
 public void showAllFiles(File dir,String prepath,String module) throws Exception{
  File[] fs = dir.listFiles();
  String fpath ="";
  String restr = "/"+ module +"/";
  for(int i=0; i<fs.length; i++){
	if (fs[i].isFile()) {
		fpath =  (prepath+"/"+fs[i].getName()).replace(restr,"");
		dbc tDbc = db.newInstance(conf);
		String sql = "select up_id from [jtbc_sys_upload]  where up_filename ='"+fpath+"'";
		
		Object[] tArys =tDbc.getDataAry(sql);
		if (tArys == null)
		{
			System.out.println("delete:"+fs[i].getAbsolutePath()) ;
			conf.common.fileDelete(fs[i].getAbsolutePath());	
		}
	 }
   if(fs[i].isDirectory()){
    try{
     showAllFiles(fs[i],prepath+"/"+fs[i].getName(),module);
    }catch(Exception e){}
   }
  }
 }
}


%>
<%   
  module1 module= new module1();
  module.Init(request, response);
  
  conf conf = new conf();
  conf.Init(request, response);
  String moduleStr = "news";
  String moduleStr1 = conf.getActualRoute(moduleStr) +"/common/upload";
  String path = conf.application.getRealPath(conf.getMapPath(conf.getActualRoute(moduleStr1)));
  File root = new File(path);
  module.showAllFiles(root,conf.getMapPath(conf.getActualRoute(moduleStr1)),moduleStr);
%>