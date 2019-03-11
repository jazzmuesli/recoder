package sourcerer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class MemoryDisplay extends JLabel {
   
    private DecimalFormat format = new DecimalFormat("####.###");
    private String header = "Memory Used";
    private Timer timer;
    private final static int DELAY = 3000;
    
    public MemoryDisplay() {
	timer = new Timer(DELAY, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
		    update();
		}
	    });
	addAncestorListener(new AncestorListener() {
		public void ancestorRemoved(AncestorEvent e) {
		    timer.stop();
		}
		public void ancestorMoved(AncestorEvent event) { /* ignore */ }
		public void ancestorAdded(AncestorEvent e) {
		    timer.start();
		}
	    });
	update();
    }
    
    void update() {
    	long free = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	setText(header + ": " + format.format(free / (1 << 20)) + "MB");
    }
    
}
