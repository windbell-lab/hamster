/**   
* @Title: MyApp.java 
* @Package org.windbell.lab.hamster.swt 
* @Description: SWT编程测试
* @author MN 
* @date 2015年12月9日 下午2:52:39 
* @version V1.0   
*/
package org.windbell.lab.hamster.swt;

import org.eclipse.swt.widgets.*;
/** 
 * @ClassName: MyApp 
 * @Description: SWT 程序入口
 * @author: MN
 * @date 2015年12月9日 下午2:52:39  
 */
public class MyApp {

    /**
     * 
     */
    public MyApp() {
    }

    /** 
     * @Title: main 
     * @author:MN
     * @date 2015年12月9日 下午2:52:39 
     * @Description: SWT人口方法
     * @param args    
     * @throws 
     */
    public static void main(String[] args) {
	Display display = new Display();
	Shell shell =new Shell(display);
	shell.setText("First swt application");
	shell.open();
	while (!shell.isDisposed()) {
	    if(!display.readAndDispatch()) display.sleep();
	}
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	display.dispose();
    }
}
