package maseno.online.examination.system.server;

import com.alee.laf.WebLookAndFeel;
import javax.swing.UIManager;

public class MASENOOnlineExaminationSystemServer {

    public static void main(String[] args) {       
        try {
           UIManager.setLookAndFeel(new WebLookAndFeel());
//           UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
        } catch (Exception e) 
        {
            System.err.println(e.toString());
        }    
        
        new ServerLogin().setVisible(true);
    }
}
