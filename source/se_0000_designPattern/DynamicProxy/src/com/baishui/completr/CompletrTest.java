package com.baishui.completr;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.baishui.proxy.MoveAble;
import com.baishui.proxy.Trank;

/**
 * JDK 6
 * 
 * 实现MoveAble Ø接口 的代理 例程
 * 
 */
public class CompletrTest {
	public static void main(String[] args) throws Exception {

		String rt = "\r\n";
		String str = "package com.baishui.completr;  " + rt
				+ "import com.baishui.proxy.MoveAble;" + rt
				+ "public class TrankGotRunTimeProxy implements MoveAble { "
				+ rt + "   	private MoveAble trank ;  " + rt
				+ "       public TrankGotRunTimeProxy(MoveAble trank) {  " + rt
				+ "   	 	this.trank = trank; " + rt + "		}  " + rt
				+ "		@Override " + rt + "		public void move() { " + rt
				+ "			long start = System.currentTimeMillis(); " + rt
				+ "			trank.move(); " + rt
				+ "			long end = System.currentTimeMillis(); " + rt
				+ "			System.out.println(\"time:\" + (end - start)/1000); "
				+ rt + "		}" + rt + "}";
		String fileName = System.getProperty("user.dir") + // 获取项目根目录
				"/src/com/baishui/completr/TrankGotRunTimeProxy.java";
		// System.out.println( fileName);
		File f = new File(fileName);// 将代码 动态保存成 .java 文件
		FileWriter fw;

		fw = new FileWriter(f);
		fw.write(str);
		fw.flush();
		fw.close();
		System.out.println("生成成功");

		// 通过 javaCompiler 动态编译 .java 文件
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,
				null, null);
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null,
				units);
		t.call();
		fileMgr.close();

		// 载入编译后的文件 载入到内存
		// 通过类文件文件URL 载入类
		URL[] urls = new URL[] { new URL("file:/"
				+ System.getProperty("user.dir") + "/src") };
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("com.baishui.completr.TrankGotRunTimeProxy");
		System.out.println(c);

		Constructor ctr = c.getConstructor(MoveAble.class);// 当代理类 没有无参的构造方法时
		MoveAble m = (MoveAble) ctr.newInstance(new Trank());
		m.move();
	}
}
