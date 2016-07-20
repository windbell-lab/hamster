package org.windbell.lab.hamster.swt;


import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.*;
public class MyApp2 {

    public MyApp2() {
	// TODO Auto-generated constructor stub
    }
    final static String[] ITEM_NAMES = {"first item", "second", "third", "fourth", "fifth"};
    public static void main(String[] args) {
	Display display=new Display();
	final Image checkedImage=getStateImage(display,true);
	final Image uncheckedImage=getStateImage(display,false);
	Shell shell= new Shell(display);
	shell.setLayout(new FillLayout());
	
	final Table table = new Table(shell, SWT.FULL_SELECTION | SWT.BORDER);
	for (int i = 0; i < ITEM_NAMES.length; i++) {
	    TableItem item = new TableItem(table, SWT.NONE);
	    item.setText(ITEM_NAMES[i]);
	    item.setImage(i%2==0 ? uncheckedImage : checkedImage);
	}
	table.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		TableItem item=(TableItem) e.item;
		item.setImage(item.getImage() == checkedImage ? uncheckedImage : checkedImage);
	    }
	});
	table.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
	    @Override
	    public void getRole(AccessibleControlEvent e) {
		e.detail = ACC.ROLE_CHECKBUTTON;
	    }
	    @Override
	    public void getState(AccessibleControlEvent e) {
		if(e.childID >=0&& e.childID<table.getItemCount()){
		    TableItem item = table.getItem(e.childID);
		    if(item.getImage()==checkedImage){
			e.detail|=ACC.STATE_CHECKED;
		    }
		}
	    }
	});
	shell.pack();
	shell.open();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	checkedImage.dispose ();
	uncheckedImage.dispose ();
	display.dispose ();
    }

    private static Image getStateImage(Display display, boolean check) {
	Image image = new Image(display, 16, 16);
	GC gc = new GC(image);
	gc.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
	gc.fillOval(0, 0, 16, 16);
	if (check) {
	    gc.setForeground(display.getSystemColor(SWT.COLOR_DARK_BLUE));
	    gc.drawLine(16, 0, 0, 16);
	}
	gc.dispose();
	return image;
    }
    
}
