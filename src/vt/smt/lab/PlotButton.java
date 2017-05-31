/**
 * 
 */
package vt.smt.lab;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Painter;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;


/**
 * @author asus
 *
 */
class DateForPlot{
	public Float x1,x2,t1,t2;
	DateForPlot(float x1, float x2, float t1, float t2){
		this.x1 = x1;
		this.x2 = x2;
		this.t1 = t1;
		this.t2 = t2;
	}
}

public class PlotButton extends vt.smt.lab.Button{
	private List data = new LinkedList<DateForPlot>();
	private JTable table;
	public LinkedList<DateForPlot> getListOfValues(){
		return (LinkedList<DateForPlot>) data;
	}
	public void call(){
	 frame.setVisible(true);
	 
	}
	public void addValue(DateForPlot date){
		data.add(date);
	}
	public PlotButton(Vector2f position){
		super(position,"img/PlotButton.png");
	    frame.setTitle("Значения для графика");
	    frame.remove(this.dataTextArea);
        frame.setLocation(Mouse.getPosition().x, Mouse.getPosition().y);
      //  frame.add(new BorderLayout())
    	
    	acselleration.setVisible(true);

        frame.toFront();
        String[] columnNames = {
                "x1",
                "x2",
                "t1",
                "t2"
      };
        table = new JTable(getArray(),columnNames);
        table.setEnabled(true);
        TableModel tm = new DefaultTableModel(columnNames, 5) {
        	public boolean isCellEditable(int row, int column) {
        		return true;
        	};
        };
       table.setModel(tm);
       JScrollPane scrollPane = new JScrollPane(table);
       commitButton.setPreferredSize(new Dimension(150,100));
       commitButton.setText("Построить график");
       frame.setSize(700, 100);
        
       FlowLayout flowLay = new FlowLayout();

       JPanel panel= new  JPanel();
 
       scrollPane.setPreferredSize(new Dimension(300,103));
       acselleration.setVisible(true);
     
       panel.add(scrollPane);
       acselleration.setText("  ");
       panel.add(acselleration);
       panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

       this.frame.add(panel);
       this.frame.setLayout(flowLay);
       frame.pack();
       frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
        PlotWindow plotWindow = new PlotWindow();
        this.commitButton.addActionListener(e->{
        		
        	float x11 = Float.parseFloat(table.getModel().getValueAt(0, 0).toString());
        	float x12 = Float.parseFloat(table.getModel().getValueAt(1, 0).toString());
        	float x13 = Float.parseFloat(table.getModel().getValueAt(2, 0).toString());
        	float x14 = Float.parseFloat(table.getModel().getValueAt(3, 0).toString());
        	float x15 = Float.parseFloat(table.getModel().getValueAt(4, 0).toString());

        	float x21 = Float.parseFloat(table.getModel().getValueAt(0, 1).toString());
        	float x22 = Float.parseFloat(table.getModel().getValueAt(1, 1).toString());
        	float x23 = Float.parseFloat(table.getModel().getValueAt(2, 1).toString());
        	float x24 = Float.parseFloat(table.getModel().getValueAt(3, 1).toString());
        	float x25 = Float.parseFloat(table.getModel().getValueAt(4, 1).toString());

        	float t11 = Float.parseFloat(table.getModel().getValueAt(0, 2).toString());
        	float t12 = Float.parseFloat(table.getModel().getValueAt(1, 2).toString());
        	float t13 = Float.parseFloat(table.getModel().getValueAt(2, 2).toString());
        	float t14 = Float.parseFloat(table.getModel().getValueAt(3, 2).toString());
        	float t15 = Float.parseFloat(table.getModel().getValueAt(4, 2).toString());

        	float t21 = Float.parseFloat(table.getModel().getValueAt(0, 3).toString());
        	float t22 = Float.parseFloat(table.getModel().getValueAt(1, 3).toString());
        	float t23 = Float.parseFloat(table.getModel().getValueAt(2, 3).toString());
        	float t24 = Float.parseFloat(table.getModel().getValueAt(3, 3).toString());
        	float t25 = Float.parseFloat(table.getModel().getValueAt(4, 3).toString());

        	float x1 = 0;
        	float y1 = 0;
        	float y2 = (( 2*(x21-x11)+2*(x22-x12)+2*(x23-x13)+2*(x24-x14)+2*(x25-x15) ) / 5f);
        	float x2 = (( (t21*t21-t11*t11)+(t22*t22-t12*t12)+(t23*t23-t13*t13)+(t24*t24-t14*t14)+(t25*t25-t15*t15) ) / 5f) ;
        	
        	
        	
        	float a = y2/x2;
            try{
            	
            	acselleration.setText("a: " + Float.toString(((int)(a*1000f))/1000f )+ " м/c²");
            }catch(Exception ex){
            	ex.printStackTrace();
            }
        	plotWindow.initLine(	// x1 y1 x2 y2
        			x1,y1,x2/150,y2/30);   //Float.parseFloat(table.getModel().getValueAt(2, 0).toString()),
        			        //Float.parseFloat(table.getModel().getValueAt(2, 1).toString()),
        			        //Float.parseFloat(table.getModel().getValueAt(2, 2).toString()),
        			        //Float.parseFloat(table.getModel().getValueAt(2, 3).toString()));
        		plotWindow.start();
  
        });
        
	}
	public void setValues(List<ResultCounter.Result> values){
		try{
		for(int j = 0; j < 5; j++){
			table.getModel().setValueAt(values.get(j).x1, j, 0);
			table.getModel().setValueAt(values.get(j).x2, j, 1);
			table.getModel().setValueAt(values.get(j).t1, j, 2);
			table.getModel().setValueAt(values.get(j).t2, j, 3);
		}
		}catch(Exception e){
			System.out.println("Недостаточно получено данных для заполнения таблицы");
		}
	}
	private JLabel acselleration = new JLabel();
	private Object[][] getArray(){
		Float[][] answer = new Float[data.size()][4];
			for(int j=0; j< data.size();j++){
				answer[j][0] = ((DateForPlot)data.get(j)).x1;
				answer[j][1] = ((DateForPlot)data.get(j)).x2;
				answer[j][2] = ((DateForPlot)data.get(j)).t1;
				answer[j][3] = ((DateForPlot)data.get(j)).t2;
			}
		return answer;
	}
}
