package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;


public class test {
	
	public static void main(String[] args) {
		ActionListener time = new ActionListener() { // 监听事件,不然实现不了动态改变时间
			public void actionPerformed(ActionEvent e) {
				// date对象代表当前的系统时间(毫秒)
				Date date = new Date();
				// format对象是用来以指定的时间格式格式化时间的
				SimpleDateFormat from = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss"); // 这里的格式可以自己设置
				// format()方法是用来格式化时间的方法
				String times = from.format(date);
				System.out.println(times);
			}
		};
		Timer tim = new Timer(1000, time); // 这里表示1000毫秒更新一下时间
		tim.start(); //启动
	}
}
